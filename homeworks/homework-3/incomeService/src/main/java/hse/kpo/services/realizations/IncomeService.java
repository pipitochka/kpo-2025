package hse.kpo.services.realizations;

import hse.kpo.domain.enums.IncomeStatus;
import hse.kpo.domain.objects.Income;
import hse.kpo.dto.Request;
import hse.kpo.dto.Responce;
import hse.kpo.kafka.producers.IncomeProducer;
import hse.kpo.repositories.IIncomeRepository;
import hse.kpo.services.interfaces.IIncomeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
@Log4j2
@Getter
public class IncomeService implements IIncomeService {

    private final IIncomeRepository incomeRepository;

    private final IncomeProducer incomeProducer;

    @Transactional
    @Override
    public Income createIncome(Long accountId, double amount) {
        Income income = new Income();
        income.setAmount(amount);
        income.setStatus(IncomeStatus.PENDING);
        income.setUserId(accountId);
        incomeRepository.save(income);
        Request request = new Request();
        request.setAmount(amount);
        request.setAccountId(accountId);
        request.setId(income.getId());
        incomeProducer.sendOperation("input-operations", request);
        return income;
    }

    @Transactional
    @Override
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    @Transactional
    @Override
    public void update(Responce responce) {
        var possibleIncome = incomeRepository.findById(responce.getId());
        if (possibleIncome.isPresent()) {
            Income income = possibleIncome.get();
            income.setStatus(responce.getResult());
            incomeRepository.save(income);
        }
    }
}
