package hse.domains.handler;

import hse.interfaces.object.Command;
import hse.interfaces.object.Facade;
import hse.interfaces.object.OperationHandler;
import org.springframework.stereotype.Component;

@Component
public class StartHandler implements OperationHandler {

    public OperationHandler operationHandler;

    @Override
    public void setNext(OperationHandler next) {
        operationHandler = next;
    }

    @Override
    public boolean handle(Command command, Facade facade) {
        return operationHandler.handle(command, facade);
    }

    StartHandler(){
        OperationHandler op1 = new HseAccountHandler();
        OperationHandler op2 = new HseCategoryHandler();
        OperationHandler op3 = new HseOperationHandler();
        OperationHandler op4 = new HseAccountSumHandler();
        OperationHandler op5 = new ErrorHandler();
        op4.setNext(op5);
        op3.setNext(op4);
        op2.setNext(op3);
        op1.setNext(op2);
        this.setNext(op1);
    }
}
