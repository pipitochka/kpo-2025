package hse.domains.command;

import hse.interfaces.object.Command;
import hse.interfaces.object.CommandContext;
import hse.interfaces.object.Facade;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Class to make add operation command.
 */
public class HseAddOperationCommand implements Command {

    @Getter
    @Setter
    private CommandContext context;

    public HseAddOperationCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public void execute(Facade facade) {
        facade.addOperation(context.getOperationType(), context.getAccount(), context.getAmount(),
                context.getDate(), context.getDescription(), context.getCategory());
    }
}
