package hse.domains;

import hse.interfaces.BankAccount;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HseBankAccount implements BankAccount {

    @Getter
    private int id;

    @Getter
    private double balance;

    @Getter
    private String name;


}
