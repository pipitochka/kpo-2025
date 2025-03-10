package hse.domains.handler;

import hse.domains.command.HseAddOperationCommand;
import hse.interfaces.object.Account;
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
                    Account account = command.getContext().getAccount();
                    account.setBalance(account.getBalance() + command.getContext().getAmount());
                    return true;
                }
                case EXPENSE -> {
                    Account account = command.getContext().getAccount();
                    if (account.getBalance() >= command.getContext().getAmount()) {
                        account.setBalance(account.getBalance() - command.getContext().getAmount());
                        return true;
                    }
                    return false;
                }
            }
        }
        return operationHandler.handle(command, facade);
    }
}
