package hse.domains.command;

import hse.domains.object.HseCommandContext;
import hse.emums.CommandType;
import hse.emums.OperationType;
import hse.interfaces.object.Command;
import hse.interfaces.object.CommandContext;
import hse.interfaces.object.Facade;
import hse.interfaces.object.Operation;
import lombok.RequiredArgsConstructor;

/**
 * Adapter class to make a HseAddOperationCommand from Operation.
 */
public class CommandFromOpperation  extends HseAddOperationCommand {
    private Operation op;

    public CommandFromOpperation(CommandContext context) {
        super(context);
    }

    /**
     * Constructor from Operation.
     *
     * @param op Operation
     */
    public CommandFromOpperation(Operation op) {
        super(null);
        this.op = op;
        super.setContext(makeContext(op));
    }

    /**
     * function to make ComandContent from Operation.
     *
     * @param op Operation.
     * @return ComandContent.
     */
    public CommandContext makeContext(Operation op) {
        HseCommandContext commandContext = new HseCommandContext(CommandType.OPERATION);
        commandContext.setOperationType(op.getOperationType());
        commandContext.setAccount(op.getAccount());
        commandContext.setAmount(op.getAmount());
        commandContext.setDate(op.getDate());
        commandContext.setDescription(op.getDescription());
        commandContext.setCategory(op.getCategory());
        return commandContext;
    }

    @Override
    public void execute(Facade facade) {
        return;
    }
}
