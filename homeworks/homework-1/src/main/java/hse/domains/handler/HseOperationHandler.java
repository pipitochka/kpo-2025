package hse.domains.handler;

import hse.domains.command.HseAddOperationCommand;
import hse.interfaces.object.Command;
import hse.interfaces.object.Facade;
import hse.interfaces.object.OperationHandler;
import org.springframework.stereotype.Component;

public class HseOperationHandler implements OperationHandler {
    private OperationHandler operationHandler;

    @Override
    public void setNext(OperationHandler next) {
        operationHandler = next;
    }

    @Override
    public boolean handle(Command command, Facade facade) {
        if (command instanceof HseAddOperationCommand){
            if (command.getContext().getOperationType() == null) {
                return false;
            }
            if (command.getContext().getAccountId() > facade.getCategoryList().size() ) {
                return false;
            }
            if (command.getContext().getCategoryId() > facade.getCategoryList().size()) {
                return false;
            }
            if (command.getContext().getOperationType() != facade.getCategoryList().get(command.getContext().getCategoryId()).getOperationType()){
                return false;
            }
            return operationHandler.handle(command, facade);
        }
        return operationHandler.handle(command, facade);
    }
}
