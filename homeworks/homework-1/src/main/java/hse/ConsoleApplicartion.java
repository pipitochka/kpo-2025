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
import hse.interfaces.object.Account;
import hse.interfaces.object.Category;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class to make runtime console application.
 */
@Component
public class ConsoleApplicartion {
    @Autowired
    HseFacade hseFacade;

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


    /**
     * function to runtime console app.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("exit")) {
                break;
            }
            String[] split = line.split(" ");
            switch (split[0]) {
                case "add": {
                    switch (split[1]) {
                        case "account": {
                            if (split.length == 3) {
                                HseCommandContext context = new HseCommandContext(CommandType.ACCOUNT);
                                context.setName(split[2]);
                                hseFacade.takeCommand(context);
                                break;
                            }
                            System.out.println("Invalid input");
                            break;
                        }
                        case "category": {
                            if (split.length == 4) {
                                HseCommandContext context = new HseCommandContext(CommandType.CATEGORY);
                                switch (split[2]) {
                                    case "income":
                                        context.setOperationType(OperationType.INCOME);
                                        break;
                                    case "expense":
                                        context.setOperationType(OperationType.EXPENSE);
                                        break;
                                    default:
                                        System.out.println("Invalid input");
                                        break;
                                }
                                context.setName(split[3]);
                                hseFacade.takeCommand(context);
                                break;
                            }
                            System.out.println("Invalid input");
                            break;
                        }
                        case "operation": {
                            if (split.length == 8) {
                                HseCommandContext context = new HseCommandContext(CommandType.OPERATION);
                                switch (split[2]) {
                                    case "income":
                                        context.setOperationType(OperationType.INCOME);
                                        break;
                                    case "expense":
                                        context.setOperationType(OperationType.EXPENSE);
                                        break;
                                    default:
                                        System.out.println("Invalid input");
                                        break;
                                }
                                try {
                                    int accountId = Integer.parseInt(split[3]);
                                    context.setAccount(hseFacade.getAccount(accountId));
                                } catch (NumberFormatException e) {
                                    context.setAccount(hseFacade.getAccount(split[3]));
                                }
                                context.setAmount(Double.parseDouble(split[4]));
                                context.setDate(Integer.parseInt(split[5]));
                                context.setDescription(split[6]);
                                try {
                                    int setCategoryId = Integer.parseInt(split[7]);
                                    context.setCategory(hseFacade.getCategoryById(setCategoryId));
                                } catch (NumberFormatException e) {
                                    context.setCategory(hseFacade.getCategory(split[7]));
                                }
                                hseFacade.takeCommand(context);
                                break;
                            }
                            if (split.length == 7) {
                                HseCommandContext context = new HseCommandContext(CommandType.OPERATION);
                                switch (split[2]) {
                                    case "income":
                                        context.setOperationType(OperationType.INCOME);
                                        break;
                                    case "expense":
                                        context.setOperationType(OperationType.EXPENSE);
                                        break;
                                    default:
                                        System.out.println("Invalid input");
                                        break;
                                }
                                try {
                                    int accountId = Integer.parseInt(split[3]);
                                    context.setAccount(hseFacade.getAccount(accountId));
                                } catch (NumberFormatException e) {
                                    context.setAccount(hseFacade.getAccount(split[3]));
                                }
                                context.setAmount(Double.parseDouble(split[4]));
                                context.setDate(Integer.parseInt(split[5]));
                                try {
                                    int setCategoryId = Integer.parseInt(split[6]);
                                    context.setCategory(hseFacade.getCategoryById(setCategoryId));
                                } catch (NumberFormatException e) {
                                    context.setCategory(hseFacade.getCategory(split[6]));
                                }
                                hseFacade.takeCommand(context);
                            }
                            break;
                        }
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                    break;
                }
                case "show": {
                    switch (split[1]) {
                        case "accounts": {
                            hseFacade.getAccountList().forEach(System.out::println);
                            break;
                        }
                        case "categories": {
                            hseFacade.getCategoryList().forEach(System.out::println);
                            break;
                        }
                        case "operations": {
                            hseFacade.getOperationList().forEach(System.out::println);
                            break;
                        }
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                    break;
                }
                case "analyse": {
                    if (!split[3].equals("by")) {
                        Account account;
                        try {
                            int accountId = Integer.parseInt(split[2]);
                            account = hseFacade.getAccount(accountId);
                        } catch (NumberFormatException e) {
                            account = hseFacade.getAccount(split[2]);
                        }
                        if (!split[3].equals("from")) {
                            System.out.println("Invalid input");
                            break;
                        }
                        if (!split[5].equals("to")) {
                            System.out.println("Invalid input");
                            break;
                        }
                        int from;
                        int to;
                        try {
                            from = Integer.parseInt(split[4]);
                            to = Integer.parseInt(split[6]);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input");
                            break;
                        }
                        switch (split[1]) {
                            case "account": {
                                hseFacade.printAnaliticByAccountByDate(account, from, to);
                                break;
                            }
                            case "income": {
                                hseFacade.printAnaliticByAccountIncome(account, from, to);
                                break;
                            }
                            case "expense": {
                                hseFacade.printAnaliticByAccountExpense(account, from, to);
                                break;
                            }
                            default:
                                System.out.println("Invalid input");
                                break;
                        }
                    } else {
                        Account account;
                        try {
                            int accountId = Integer.parseInt(split[2]);
                            account = hseFacade.getAccount(accountId);
                        } catch (NumberFormatException e) {
                            account = hseFacade.getAccount(split[2]);
                        }
                        Category category;
                        try {
                            int categoryId = Integer.parseInt(split[4]);
                            category = hseFacade.getCategoryById(categoryId);
                        } catch (NumberFormatException e) {
                            category = hseFacade.getCategory(split[4]);
                        }
                        if (!split[5].equals("from")) {
                            System.out.println("Invalid input");
                            break;
                        }
                        if (!split[7].equals("to")) {
                            System.out.println("Invalid input");
                            break;
                        }
                        int from;
                        int to;
                        try {
                            from = Integer.parseInt(split[6]);
                            to = Integer.parseInt(split[8]);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input");
                            break;
                        }
                        hseFacade.printAnaliticByAccountByCategory(account, category, from, to);
                    }
                    break;
                }
                case "change": {
                    if (!split[1].equals("operation")) {
                        System.out.println("Invalid input");
                        break;
                    }
                    int operationId;
                    try {
                        operationId = Integer.parseInt(split[2]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input");
                        break;
                    }
                    if (!split[3].equals("category")) {
                        System.out.println("Invalid input");
                        break;
                    }
                    if (!split[4].equals("to")) {
                        System.out.println("Invalid input");
                        break;
                    }
                    Category category;
                    try {
                        int categoryId = Integer.parseInt(split[5]);
                        category = hseFacade.getCategoryById(categoryId);
                    } catch (NumberFormatException e) {
                        category = hseFacade.getCategory(split[5]);
                    }
                    hseFacade.changeOperationType(hseFacade.getOperation(operationId), category);
                    break;
                }
                case "delete": {
                    switch (split[1]) {
                        case "account": {
                            Account account;
                            try {
                                int accountId = Integer.parseInt(split[2]);
                                account = hseFacade.getAccount(accountId);
                            } catch (NumberFormatException e) {
                                account = hseFacade.getAccount(split[2]);
                            }
                            hseFacade.deleteAccount(account);
                            break;
                        }
                        case "category": {
                            Category category;
                            try {
                                int accountId = Integer.parseInt(split[2]);
                                category = hseFacade.getCategoryById(accountId);
                            } catch (NumberFormatException e) {
                                category = hseFacade.getCategory(split[2]);
                            }
                            hseFacade.deleteCategory(category);
                            break;
                        }
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                    break;
                }
                case "repeat": {
                    Account account;
                    try {
                        int accountId = Integer.parseInt(split[1]);
                        account = hseFacade.getAccount(accountId);
                    } catch (NumberFormatException e) {
                        account = hseFacade.getAccount(split[1]);
                    }
                    hseFacade.repeatOperations(account);
                    break;
                }
                case "reverse": {
                    if (!split[1].equals("operation")) {
                        System.out.println("Invalid input");
                        break;
                    }
                    int operationId;
                    try {
                        operationId = Integer.parseInt(split[2]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input");
                        break;
                    }
                    hseFacade.reverseOperation(hseFacade.getOperation(operationId));
                    break;
                }
                case "save": {
                    if (split.length == 1) {
                        hseFacade.export(jsonFileExporter, "homeworks/homework-1/files/export.json");
                    } else {
                        hseFacade.export(jsonFileExporter, split[1]);
                    }
                    break;
                }
                case "take": {
                    if (split.length == 1) {
                        hseFacade = jsonFileImporter.importData("homeworks/homework-1/files/export.json");
                    } else {
                        hseFacade = jsonFileImporter.importData(split[1]);
                    }
                    break;
                }
                default: {
                    System.out.println("Invalid input");
                    break;
                }
            }
        }
    }
}
