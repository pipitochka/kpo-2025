package hse.interfaces.factory;

import hse.interfaces.object.CommandContext;
import hse.interfaces.object.Command;

public interface CommandFactory {
    public Command createCommand(CommandContext context);

}
