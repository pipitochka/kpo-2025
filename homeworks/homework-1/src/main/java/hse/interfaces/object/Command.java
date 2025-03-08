package hse.interfaces.object;

import hse.interfaces.CommandContext;

public interface Command {

    void execute(Facade facade);
}
