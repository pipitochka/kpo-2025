package hse.domains.command;

import hse.interfaces.object.Command;
import hse.interfaces.object.CommandContext;
import hse.interfaces.object.Facade;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Class to make add account command.
 */
@RequiredArgsConstructor
public class HseAddBankAccountCommand implements Command {
    @Getter
    private final CommandContext context;

    @Override
    public void execute(Facade facade) {
        facade.addBankAccount(context.getName());
    }
}
