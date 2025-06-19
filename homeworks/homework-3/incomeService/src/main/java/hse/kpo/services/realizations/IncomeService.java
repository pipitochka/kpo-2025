package hse.kpo.services.realizations;

import hse.kpo.domain.enums.IncomeStatus;
import hse.kpo.domain.objects.Income;
import hse.kpo.dto.IncomeDto;
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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Log4j2
@Getter
public class IncomeService implements IIncomeService {

    private final IIncomeRepository incomeRepository;

    private final IncomeProducer incomeProducer;

    @Transactional
    @Override
    public IncomeDto createIncome(Long accountId, double amount) {
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
        return new IncomeDto(income.getId(), income.getUserId(), income.getAmount(), income.getStatus());
    }

    @Transactional
    @Override
    public List<IncomeDto> getAllIncomes() {
        return incomeRepository.findAll().stream().map(
                x -> new IncomeDto(x.getId(), x.getUserId(), x.getAmount(), x.getStatus()))
                .collect(Collectors.toList());
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
