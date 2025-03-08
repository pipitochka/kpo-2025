package hse.domains.command;

import hse.emums.OperationType;
import hse.interfaces.CommandContext;
import hse.interfaces.object.Command;
import hse.interfaces.object.Facade;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HseAddOperationCommand implements Command {
    private final CommandContext context;


    @Override
    public void execute(Facade facade) {
        facade.addOperation(context.getOperationType(), context.getAccountId(), context.getAmount(),
                context.getDate(), context.getDescription(), context.getCategoryId());
    }
}
