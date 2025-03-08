package hse.domains.object;

import hse.interfaces.object.BankAccount;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class HseBankAccount implements BankAccount {

    @Getter
    private final int id;

    @Getter
    @Setter
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
