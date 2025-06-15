package hse.kpo.services.interfaces;

import hse.kpo.domain.objects.Income;
import hse.kpo.dto.Responce;


import java.util.List;

public interface IIncomeService {

    public Income createIncome(Long accountId, double amount);

    public List<Income> getAllIncomes();

    public void update(Responce responce);
}
