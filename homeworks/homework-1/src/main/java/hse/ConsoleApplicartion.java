package hse;

import hse.domains.facade.HseFacade;
import hse.domains.object.HseCommandContext;
import hse.emums.CommandType;
import hse.emums.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.stream.IntStream;

@Component
public class ConsoleApplicartion {
    @Autowired
    HseFacade hseFacade;


    public void run(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            String line = scanner.nextLine();
            if (line.equals("exit")) {
                break;
            }
            String[] split = line.split(" ");
            switch (split[0]){
                case "add":{
                    switch (split[1]){
                        case "account":{
                            if (split.length == 3) {
                                HseCommandContext context = new HseCommandContext(CommandType.ACCOUNT);
                                context.setName(split[2]);
                                hseFacade.takeCommand(context);
                                break;
                            }
                            System.out.println("Invalid input");
                            break;
                        }
                        case "category":{
                            if (split.length == 4) {
                                HseCommandContext context = new HseCommandContext(CommandType.CATEGORY);
                                switch (split[2]){
                                    case "income":
                                        context.setOperationType(OperationType.INCOME);
                                        break;
                                    case "expense":
                                        context.setOperationType(OperationType.EXPENSE);
                                        break;
                                }
                                context.setName(split[3]);
                                hseFacade.takeCommand(context);
                                break;
                            }
                            System.out.println("Invalid input");
                            break;
                        }
                        case "operation":{
                            if (split.length == 8) {
                                HseCommandContext context = new HseCommandContext(CommandType.OPERATION);
                                switch (split[2]){
                                    case "income":
                                        context.setOperationType(OperationType.INCOME);
                                        break;
                                    case "expense":
                                        context.setOperationType(OperationType.EXPENSE);
                                        break;
                                }
                                try{
                                    int accountId = Integer.parseInt(split[3]);
                                    context.setAccountId(accountId);
                                }
                                catch (NumberFormatException e){
                                    int index = IntStream.range(0, hseFacade.getAccountList().size())
                                            .filter(i -> hseFacade.getAccountList().get(i).getName().equals(split[3]))
                                            .findFirst()
                                            .orElse(-1);
                                    context.setAccountId(index);
                                }
                                context.setAmount(Double.parseDouble(split[4]));
                                context.setDate(Integer.parseInt(split[5]));
                                context.setDescription(split[6]);
                                try{
                                    int setCategoryId = Integer.parseInt(split[7]);
                                    context.setCategoryId(setCategoryId);
                                }
                                catch (NumberFormatException e){
                                    int index = IntStream.range(0, hseFacade.getCategoryList().size())
                                            .filter(i -> hseFacade.getCategoryList().get(i).getName().equals(split[7]))
                                            .findFirst()
                                            .orElse(-1);
                                    context.setCategoryId(index);
                                }
                                hseFacade.takeCommand(context);
                                break;
                            }
                            if (split.length == 7) {
                                HseCommandContext context = new HseCommandContext(CommandType.OPERATION);
                                switch (split[2]){
                                    case "income":
                                        context.setOperationType(OperationType.INCOME);
                                        break;
                                    case "expense":
                                        context.setOperationType(OperationType.EXPENSE);
                                        break;
                                }
                                try{
                                    int accountId = Integer.parseInt(split[3]);
                                    context.setAccountId(accountId);
                                }
                                catch (NumberFormatException e){
                                    int index = IntStream.range(0, hseFacade.getAccountList().size())
                                            .filter(i -> hseFacade.getAccountList().get(i).getName().equals(split[3]))
                                            .findFirst()
                                            .orElse(-1);
                                    context.setAccountId(index);
                                }
                                context.setAmount(Double.parseDouble(split[4]));
                                context.setDate(Integer.parseInt(split[5]));
                                try{
                                    int setCategoryId = Integer.parseInt(split[6]);
                                    context.setCategoryId(setCategoryId);
                                }
                                catch (NumberFormatException e){
                                    int index = IntStream.range(0, hseFacade.getCategoryList().size())
                                            .filter(i -> hseFacade.getCategoryList().get(i).getName().equals(split[6]))
                                            .findFirst()
                                            .orElse(-1);
                                    context.setCategoryId(index);
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
                case "show":{
                    switch (split[1]){
                        case "accounts":{
                            hseFacade.getAccountList().forEach(System.out::println);
                            break;
                        }
                        case "categories":{
                            hseFacade.getCategoryList().forEach(System.out::println);
                            break;
                        }
                        case "operations":{
                            hseFacade.getOperationList().forEach(System.out::println);
                            break;
                        }
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                    break;
                }
            }
        }
    }

}
