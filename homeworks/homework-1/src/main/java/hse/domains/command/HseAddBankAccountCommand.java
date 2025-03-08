package hse.domains.command;

import hse.interfaces.CommandContext;
import hse.interfaces.object.Command;
import hse.interfaces.object.Facade;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HseAddBankAccountCommand implements Command {
    private final CommandContext context;

    @Override
    public void execute(Facade facade) {
        facade.addBankAccount(context.getName());
    }
}
