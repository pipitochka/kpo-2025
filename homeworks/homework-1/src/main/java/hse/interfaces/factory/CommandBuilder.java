package hse.interfaces.factory;

import hse.interfaces.object.CommandContext;
import hse.interfaces.object.Command;

public interface CommandBuilder {
    public Command createCommand(CommandContext context);

}
