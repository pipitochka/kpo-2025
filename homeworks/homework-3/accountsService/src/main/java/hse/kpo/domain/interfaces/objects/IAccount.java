package hse.kpo.domain.interfaces.objects;

import hse.kpo.domain.realizations.objects.Operation;

public interface IAccount {

    public String getName();

    public double getBalance();

    public Long getId();

    public void addOperation(Operation operation);
}
