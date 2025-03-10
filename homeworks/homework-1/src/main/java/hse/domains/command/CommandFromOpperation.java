package hse.domains.command;

import hse.domains.object.HseCommandContext;
import hse.emums.CommandType;
import hse.emums.OperationType;
import hse.interfaces.object.Command;
import hse.interfaces.object.CommandContext;
import hse.interfaces.object.Facade;
import hse.interfaces.object.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommandFromOpperation implements Command {
    private final Operation op;

    @Override
    public CommandContext getContext() {
        HseCommandContext commandContext = new HseCommandContext(CommandType.OPERATION);
        commandContext.setOperationType(op.getOperationType());
        commandContext.setAccountId(op.getBankAccountId());
        commandContext.setAmount(op.getAmount());
        commandContext.setDate(op.getDate());
        commandContext.setDescription(op.getDescription());
        commandContext.setCategoryId(op.getCategoryId());
        return commandContext;
    }

    @Override
    public void execute(Facade facade) {
        return;
    }
}
