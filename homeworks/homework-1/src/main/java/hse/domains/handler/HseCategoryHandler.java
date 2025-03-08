package hse.domains.handler;

import hse.domains.command.HseAddCategoryCommand;
import hse.interfaces.object.Command;
import hse.interfaces.object.Facade;
import hse.interfaces.object.OperationHandler;
import org.springframework.stereotype.Component;


public class HseCategoryHandler implements OperationHandler {
    private OperationHandler operationHandler;

    @Override
    public void setNext(OperationHandler next) {
        operationHandler = next;
    }

    @Override
    public boolean handle(Command command, Facade facade) {
        if (command instanceof HseAddCategoryCommand){
            if (command.getContext().getName() == null || command.getContext().getOperationType() == null){
                return false;
            }
            if (facade.getCategoryList().stream().noneMatch(c -> c.getName().equals(command.getContext().getName()))){
                return true;
            }
            return false;
        }
        return operationHandler.handle(command, facade);
    }
}
