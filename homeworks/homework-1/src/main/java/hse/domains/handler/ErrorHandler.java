package hse.domains.handler;

import hse.interfaces.object.Command;
import hse.interfaces.object.Facade;
import hse.interfaces.object.OperationHandler;
import org.springframework.stereotype.Component;

public class ErrorHandler implements OperationHandler {
    @Override
    public void setNext(OperationHandler next) {}

    @Override
    public boolean handle(Command command, Facade facade) {
        return false;
    }
}
