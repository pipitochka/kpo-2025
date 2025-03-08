package hse.domains;

import hse.interfaces.BankAccount;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@ToString
public class HseBankAccount implements BankAccount {

    @Getter
    private final int id;

    @Getter
    private double balance = 0;

    @Getter
    private final String name;

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        balance -= amount;
    }
}
