package hse.interfaces.factory;

import hse.interfaces.object.Command;
import hse.interfaces.object.CommandContext;

/**
 * interface of command factory.
 */
public interface CommandFactory {
    public Command createCommand(CommandContext context);

}
