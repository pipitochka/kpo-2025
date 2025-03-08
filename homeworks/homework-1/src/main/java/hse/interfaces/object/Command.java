package hse.interfaces.object;

public interface Command {

    public CommandContext getContext();

    void execute(Facade facade);

}
