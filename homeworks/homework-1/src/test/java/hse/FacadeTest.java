package hse;

import hse.domains.facade.HseFacade;
import hse.domains.factory.HseAccountFactory;
import hse.domains.factory.HseCategoryFactory;
import hse.domains.factory.HseCommandBuilder;
import hse.domains.factory.HseOperationFactory;
import hse.domains.handler.StartHandler;
import hse.domains.object.HseCommandContext;
import hse.emums.CommandType;
import hse.emums.OperationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class FacadeTest {

    @Autowired
    private HseFacade hse;

    @Autowired
    private HseAccountFactory accountFactory;

    @Autowired
    private HseCategoryFactory categoryFactory;

    @Autowired
    private HseOperationFactory operationFactory;

    @Autowired
    private HseCommandBuilder commandFactory;

    @Autowired
    private StartHandler operationHandler;

    @BeforeEach
    void setUp() {
        hse = new HseFacade(accountFactory, categoryFactory, operationFactory, commandFactory, operationHandler);
    }

    @Test
    @DisplayName("Input Facade Test")
    public void inputFacadeTest() {

        hse.addBankAccount("Artem");
        hse.addBankAccount("Vanya");
        hse.addBankAccount("Mike");
        hse.addBankAccount("Kate");

        hse.addCategory(OperationType.EXPENSE, "Coffee");
        hse.addCategory(OperationType.INCOME, "Cafe");
        hse.addCategory(OperationType.EXPENSE, "Health");

        hse.addOperation(OperationType.INCOME, 1, 10, 1,"", 1);

        System.out.println(hse);
    }

    @Test
    @DisplayName("Add Command Test")
    public void addCommandTest() {

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
        commandContext6.setAccountId(0);
        commandContext6.setAmount(150);
        commandContext6.setDate(3);
        commandContext6.setDescription("test1");
        commandContext6.setCategoryId(3);
        hse.takeCommand(commandContext6);

        HseCommandContext commandContext5 = new HseCommandContext(CommandType.OPERATION);
        commandContext5.setOperationType(OperationType.EXPENSE);
        commandContext5.setAccountId(0);
        commandContext5.setAmount(100);
        commandContext5.setDate(1);
        commandContext5.setDescription("test");
        commandContext5.setCategoryId(2);
        hse.takeCommand(commandContext5);


        System.out.println(hse);

        assertThat(hse.getCategoryList().size()).isEqualTo(4);
        assertThat(hse.getOperationList().size()).isEqualTo(2);
        assertThat(hse.getAccountList().size()).isEqualTo(2);
        assertThat(hse.getAccountList().get(0).getName()).isEqualTo("Artem");
        assertThat(hse.getAccountList().get(0).getBalance()).isEqualTo(50);
        assertThat(hse.getAccountList().get(1).getBalance()).isEqualTo(0);
    }

    @Test
    @DisplayName("Analyse Test")
    public void analyseTest() {
        HseCommandContext commandContext1 = new HseCommandContext(CommandType.ACCOUNT);
        commandContext1.setName("Artem");
        hse.takeCommand(commandContext1);

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
        commandContext6.setAccountId(0);
        commandContext6.setAmount(250);
        commandContext6.setDate(0);
        commandContext6.setDescription("test1");
        commandContext6.setCategoryId(3);
        hse.takeCommand(commandContext6);

        HseCommandContext commandContext5 = new HseCommandContext(CommandType.OPERATION);
        commandContext5.setOperationType(OperationType.EXPENSE);
        commandContext5.setAccountId(0);
        commandContext5.setAmount(100);
        commandContext5.setDate(1);
        commandContext5.setDescription("test");
        commandContext5.setCategoryId(2);
        hse.takeCommand(commandContext5);

        HseCommandContext commandContext7 = new HseCommandContext(CommandType.OPERATION);
        commandContext7.setOperationType(OperationType.EXPENSE);
        commandContext7.setAccountId(0);
        commandContext7.setAmount(100);
        commandContext7.setDate(2);
        commandContext7.setDescription("test");
        commandContext7.setCategoryId(2);
        hse.takeCommand(commandContext7);

        hse.printAnaliticByAccountByDate(0, 0, 2);
        hse.printAnaliticByAccountIncome(0, 0, 1);
        hse.printAnaliticByAccountExpense(0, 0, 1);
        hse.printAnaliticByAccountExpense(0, 0, 2);
        hse.printAnaliticByAccountByCategory(0, 0, 0, 4);

        hse.repeatOperations(0);
        assertThat(hse.getAccountList().get(0).getBalance()).isEqualTo(50);
    }

    @Test
    @DisplayName("Delete Account Test")
    public void deleteAccountTest() {
        HseCommandContext commandContext1 = new HseCommandContext(CommandType.ACCOUNT);
        commandContext1.setName("Artem");
        hse.takeCommand(commandContext1);

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
        commandContext6.setAccountId(0);
        commandContext6.setAmount(250);
        commandContext6.setDate(0);
        commandContext6.setDescription("test1");
        commandContext6.setCategoryId(3);
        hse.takeCommand(commandContext6);

        HseCommandContext commandContext5 = new HseCommandContext(CommandType.OPERATION);
        commandContext5.setOperationType(OperationType.EXPENSE);
        commandContext5.setAccountId(0);
        commandContext5.setAmount(100);
        commandContext5.setDate(1);
        commandContext5.setDescription("test");
        commandContext5.setCategoryId(1);
        hse.takeCommand(commandContext5);

        HseCommandContext commandContext7 = new HseCommandContext(CommandType.OPERATION);
        commandContext7.setOperationType(OperationType.EXPENSE);
        commandContext7.setAccountId(0);
        commandContext7.setAmount(100);
        commandContext7.setDate(2);
        commandContext7.setDescription("test");
        commandContext7.setCategoryId(2);
        hse.takeCommand(commandContext7);

        hse.printAnaliticByAccountByDate(0, 0, 2);
        hse.printAnaliticByAccountIncome(0, 0, 1);
        hse.printAnaliticByAccountExpense(0, 0, 1);
        hse.printAnaliticByAccountExpense(0, 0, 2);
        hse.printAnaliticByAccountByCategory(0, 0, 0, 4);

        assertThat(hse.getAccountList().size()).isEqualTo(1);
        assertThat(hse.getCategoryList().size()).isEqualTo(4);
        assertThat(hse.getOperationList().size()).isEqualTo(3);

        hse.deleteAccount(0);
        assertThat(hse.getAccountList().size()).isEqualTo(0);
        assertThat(hse.getCategoryList().size()).isEqualTo(4);
        assertThat(hse.getOperationList().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Delete category Test")
    public void deleteCategoryTest() {
        HseCommandContext commandContext1 = new HseCommandContext(CommandType.ACCOUNT);
        commandContext1.setName("Artem");
        hse.takeCommand(commandContext1);

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
        commandContext6.setAccountId(0);
        commandContext6.setAmount(250);
        commandContext6.setDate(0);
        commandContext6.setDescription("test1");
        commandContext6.setCategoryId(3);
        hse.takeCommand(commandContext6);

        HseCommandContext commandContext5 = new HseCommandContext(CommandType.OPERATION);
        commandContext5.setOperationType(OperationType.EXPENSE);
        commandContext5.setAccountId(0);
        commandContext5.setAmount(100);
        commandContext5.setDate(1);
        commandContext5.setDescription("test");
        commandContext5.setCategoryId(2);
        hse.takeCommand(commandContext5);

        HseCommandContext commandContext7 = new HseCommandContext(CommandType.OPERATION);
        commandContext7.setOperationType(OperationType.EXPENSE);
        commandContext7.setAccountId(0);
        commandContext7.setAmount(100);
        commandContext7.setDate(2);
        commandContext7.setDescription("test");
        commandContext7.setCategoryId(2);
        hse.takeCommand(commandContext7);

        hse.printAnaliticByAccountByDate(0, 0, 2);
        hse.printAnaliticByAccountIncome(0, 0, 1);
        hse.printAnaliticByAccountExpense(0, 0, 1);
        hse.printAnaliticByAccountExpense(0, 0, 2);
        hse.printAnaliticByAccountByCategory(0, 0, 0, 4);

        assertThat(hse.getAccountList().size()).isEqualTo(1);
        assertThat(hse.getCategoryList().size()).isEqualTo(4);
        assertThat(hse.getOperationList().size()).isEqualTo(3);

        hse.deleteCategory(2);
        assertThat(hse.getAccountList().size()).isEqualTo(1);
        assertThat(hse.getCategoryList().size()).isEqualTo(3);
        assertThat(hse.getOperationList().size()).isEqualTo(3);
        assertThat(hse.getOperationList().get(1).getCategoryId()).isEqualTo(1);
        assertThat(hse.getOperationList().get(2).getCategoryId()).isEqualTo(1);
    }
}
