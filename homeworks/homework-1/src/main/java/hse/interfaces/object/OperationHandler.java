package hse.interfaces.object;

public interface OperationHandler {
    void setNext(OperationHandler next);
    public boolean handle(Command command, Facade facade);
}
