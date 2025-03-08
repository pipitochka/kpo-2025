package hse.domains.command;

import hse.emums.OperationType;
import hse.interfaces.CommandContext;
import hse.interfaces.object.Command;
import hse.interfaces.object.Facade;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HseAddCategoryCommand implements Command {
    private final CommandContext context;


    @Override
    public void execute(Facade facade) {
        facade.addCategory(context.getOperationType(), context.getName());
    }
}
