package hse.interfaces.factory;

import hse.emums.OperationType;
import hse.interfaces.CommandContext;
import hse.interfaces.object.Command;

public interface CommandFactory {
    public Command createCommand(CommandContext context);

}
