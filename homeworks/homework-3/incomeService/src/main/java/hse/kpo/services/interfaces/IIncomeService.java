package hse.kpo.services.interfaces;

import hse.kpo.domain.objects.Income;
import hse.kpo.dto.IncomeDto;
import hse.kpo.dto.Responce;


import java.util.List;

public interface IIncomeService {

    public IncomeDto createIncome(Long accountId, double amount);

    public List<IncomeDto> getAllIncomes();

    public void update(Responce responce);
}
