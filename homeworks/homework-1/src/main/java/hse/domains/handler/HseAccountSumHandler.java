package hse.domains.handler;

import hse.domains.command.HseAddOperationCommand;
import hse.emums.OperationType;
import hse.interfaces.object.BankAccount;
import hse.interfaces.object.Command;
import hse.interfaces.object.Facade;
import hse.interfaces.object.OperationHandler;

public class HseAccountSumHandler implements OperationHandler {
    private OperationHandler operationHandler;

    @Override
    public void setNext(OperationHandler next) {
        operationHandler = next;
    }

    @Override
    public boolean handle(Command command, Facade facade) {
        if (command instanceof HseAddOperationCommand){
            switch (command.getContext().getOperationType()){
                case INCOME -> {
                    BankAccount bankAccount = facade.getBankAccountList().get(command.getContext().getAccountId());
                    bankAccount.setBalance(bankAccount.getBalance() + command.getContext().getAmount());
                    return true;
                }
                case EXPENSE -> {
                    BankAccount bankAccount = facade.getBankAccountList().get(command.getContext().getAccountId());
                    if (bankAccount.getBalance() >= command.getContext().getAmount()) {
                        bankAccount.setBalance(bankAccount.getBalance() - command.getContext().getAmount());
                        return true;
                    }
                    return false;
                }
            }
        }
        return operationHandler.handle(command, facade);
    }
}
