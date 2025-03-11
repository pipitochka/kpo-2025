package hse.interfaces.object;

/**
 * interface of command.
 */
public interface Command {

    public CommandContext getContext();

    void execute(Facade facade);

}
