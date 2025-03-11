package hse;

import hse.domains.facade.HseFacade;
import hse.domains.factory.HseAccountFactory;
import hse.domains.factory.HseCategoryFactory;
import hse.domains.factory.HseCommandFactory;
import hse.domains.factory.HseOperationFactory;
import hse.domains.handler.StartHandler;
import hse.domains.object.HseCommandContext;
import hse.emums.CommandType;
import hse.emums.OperationType;
import hse.file.classes.JsonFileExporter;
import hse.file.classes.JsonFileImporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SaveTest {
    @Autowired
    private HseFacade hse;

    @Autowired
    private HseAccountFactory accountFactory;

    @Autowired
    private HseCategoryFactory categoryFactory;

    @Autowired
    private HseOperationFactory operationFactory;

    @Autowired
    private HseCommandFactory commandFactory;

    @Autowired
    private StartHandler operationHandler;

    @Autowired
    private JsonFileExporter jsonFileExporter;

    @Autowired
    private JsonFileImporter jsonFileImporter;



    @BeforeEach
    void setUp() {
        hse = new HseFacade(accountFactory, categoryFactory, operationFactory, commandFactory, operationHandler);
    }

    @Test
    @DisplayName("Exporter Test")
    public void exporterTest() {
        HseCommandContext commandContext1 = new HseCommandContext(CommandType.ACCOUNT);
        commandContext1.setName("Artem");
        hse.takeCommand(commandContext1);

        HseCommandContext commandContext2 = new HseCommandContext(CommandType.ACCOUNT);
        commandContext2.setName("Valya");
        hse.takeCommand(commandContext2);

        HseCommandContext commandContext3 = new HseCommandContext(CommandType.CATEGORY);
        commandContext3.setName("Coffee");
        commandContext3.setOperationType(OperationType.EXPENSE);
        hse.takeCommand(commandContext3);

        HseCommandContext commandContext4 = new HseCommandContext(CommandType.CATEGORY);
        commandContext4.setName("CashBack");
        commandContext4.setOperationType(OperationType.INCOME);
        hse.takeCommand(commandContext4);

        HseCommandContext commandContext6 = new HseCommandContext(CommandType.OPERATION);
        commandContext6.setOperationType(OperationType.INCOME);
        commandContext6.setAccount(hse.getAccount(0));
        commandContext6.setAmount(150);
        commandContext6.setDate(3);
        commandContext6.setDescription("test1");
        commandContext6.setCategory(hse.getCategoryById(3));
        hse.takeCommand(commandContext6);

        HseCommandContext commandContext5 = new HseCommandContext(CommandType.OPERATION);
        commandContext5.setOperationType(OperationType.EXPENSE);
        commandContext5.setAccount(hse.getAccount(0));
        commandContext5.setAmount(100);
        commandContext5.setDate(1);
        commandContext5.setDescription("test");
        commandContext5.setCategory(hse.getCategoryById(2));
        hse.takeCommand(commandContext5);

        hse.export(jsonFileExporter, "files/test/1.json");
    }

    @Test
    @DisplayName("Importer Test")
    public void importerTest() {
        HseCommandContext commandContext1 = new HseCommandContext(CommandType.ACCOUNT);
        commandContext1.setName("Artem");
        hse.takeCommand(commandContext1);

        HseCommandContext commandContext2 = new HseCommandContext(CommandType.ACCOUNT);
        commandContext2.setName("Valya");
        hse.takeCommand(commandContext2);

        HseCommandContext commandContext3 = new HseCommandContext(CommandType.CATEGORY);
        commandContext3.setName("Coffee");
        commandContext3.setOperationType(OperationType.EXPENSE);
        hse.takeCommand(commandContext3);

        HseCommandContext commandContext4 = new HseCommandContext(CommandType.CATEGORY);
        commandContext4.setName("CashBack");
        commandContext4.setOperationType(OperationType.INCOME);
        hse.takeCommand(commandContext4);

        HseCommandContext commandContext6 = new HseCommandContext(CommandType.OPERATION);
        commandContext6.setOperationType(OperationType.INCOME);
        commandContext6.setAccount(hse.getAccount(0));
        commandContext6.setAmount(150);
        commandContext6.setDate(3);
        commandContext6.setDescription("test1");
        commandContext6.setCategory(hse.getCategoryById(3));
        hse.takeCommand(commandContext6);

        HseCommandContext commandContext5 = new HseCommandContext(CommandType.OPERATION);
        commandContext5.setOperationType(OperationType.EXPENSE);
        commandContext5.setAccount(hse.getAccount(0));
        commandContext5.setAmount(100);
        commandContext5.setDate(1);
        commandContext5.setDescription("test");
        commandContext5.setCategory(hse.getCategoryById(2));
        hse.takeCommand(commandContext5);

        hse.export(jsonFileExporter, "files/test/2.json");


        HseFacade hseFacade = jsonFileImporter.importData("files/test/2.json");
        assertThat(hseFacade.getAccountList().size()).isEqualTo(2);
        assertThat(hseFacade.getCategoryList().size()).isEqualTo(4);
        assertThat(hseFacade.getOperationList().size()).isEqualTo(2);
    }

}
