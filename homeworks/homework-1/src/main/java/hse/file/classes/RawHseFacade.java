package hse.file.classes;

import hse.domains.facade.HseFacade;
import hse.domains.object.HseAccount;
import hse.domains.object.HseCategory;
import hse.domains.object.HseOperation;
import hse.interfaces.object.Account;
import hse.interfaces.object.Category;
import hse.interfaces.object.Operation;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RawHseFacade {
    @Getter
    private List<HseAccount> accountList = new ArrayList<>();
    @Getter
    private List<HseOperation> operationList = new ArrayList<>();
    @Getter
    private List<HseCategory> categoryList = new ArrayList<>();

    private int accountCounter = 0;
    private int operationCounter = 0;
    private int categoryCounter = 0;

    RawHseFacade() {}

    public HseFacade getHseFacade() {
        HseFacade hseFacade = new HseFacade(null, null, null, null, null);
        List<Account> accounts = new ArrayList<>();
        for (HseAccount account : accountList) {
            accounts.add(account);
        }
        List<Operation> operations = new ArrayList<>();
        for (HseOperation operation : operationList) {
            operations.add(operation);
        }
        List<Category> categories = new ArrayList<>();
        for (HseCategory category : categoryList) {
            categories.add(category);
        }
        hseFacade.setAccountList(accounts);
        hseFacade.setOperationList(operations);
        hseFacade.setCategoryList(categories);
        hseFacade.setAccountCounter(accountCounter);
        hseFacade.setOperationCounter(operationCounter);
        hseFacade.setCategoryCounter(categoryCounter);
        return hseFacade;
    }
}
