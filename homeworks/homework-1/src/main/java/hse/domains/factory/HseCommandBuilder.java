package hse.domains.factory;

import hse.domains.command.HseAddBankAccountCommand;
import hse.domains.command.HseAddCategoryCommand;
import hse.domains.command.HseAddOperationCommand;
import hse.interfaces.object.CommandContext;
import hse.interfaces.factory.CommandBuilder;
import hse.interfaces.object.Command;
import hse.interfaces.object.Facade;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class HseCommandBuilder implements CommandBuilder {

    @Getter
    @Setter
    private Facade facade;

    @Override
    public Command createCommand(CommandContext context) {
        switch (context.getCommandType()) {
            case ACCOUNT -> {
                return new HseAddBankAccountCommand(context);
            }
            case OPERATION -> {
                return new HseAddOperationCommand(context);
            }
            case CATEGORY -> {
                return new HseAddCategoryCommand(context);
            }
            default -> {
                throw new IllegalArgumentException("Unknown command type");
            }
        }
    }
}
