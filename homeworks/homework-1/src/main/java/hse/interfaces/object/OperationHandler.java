package hse.interfaces.object;

/**
 * interface of handler.
 */
public interface OperationHandler {

    void setNext(OperationHandler next);

    public boolean handle(Command command, Facade facade);
}
