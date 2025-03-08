package hse.domains.handler;

import hse.domains.command.HseAddBankAccountCommand;
import hse.interfaces.object.Command;
import hse.interfaces.object.Facade;
import hse.interfaces.object.OperationHandler;
import org.springframework.stereotype.Component;


public class HseAccountHandler implements OperationHandler {
    private OperationHandler nextHandler;


    @Override
    public void setNext(OperationHandler next) {
        nextHandler = next;
    }

    @Override
    public boolean handle(Command command, Facade facade) {
        if (command instanceof HseAddBankAccountCommand){
            if (command.getContext().getName() == null){
                return false;
            }
            if (facade.getAccountList().stream().noneMatch(
                    bankAccount -> bankAccount.getName().equals(command.getContext().getName()))){
                return true;
            }
            return false;
        }
        return nextHandler.handle(command, facade);
    }
}
