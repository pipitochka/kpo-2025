package hse.kpo.controllers;

import hse.kpo.dto.IncomeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/incomes")
@RequiredArgsConstructor
@Tag(name = "Зарплаты", description = "Операции с зарплатами")
public class GatewayIncomeController {

    @Getter
    private RestTemplate restTemplate = new RestTemplate();

    @Setter
    @Getter
    @Value("${remote.income-service.base-url}")
    private String accountsUrl;

    @PostMapping("/{id}")
    @Operation(summary = "Увеличить баланс по id")
    public ResponseEntity<IncomeDto> addIncome(@PathVariable Long id, @RequestParam double amount) {
        String url = accountsUrl + "/api/incomes/" + id + "?amount=" + amount;

        ResponseEntity<IncomeDto> response = restTemplate.postForEntity(url, null, IncomeDto.class);

        return response;
    }

    @GetMapping
    @Operation(summary = "Получить все увеличения счетов")
    public List<IncomeDto> getIncomes() {
        String url = accountsUrl + "/api/incomes";
        IncomeDto[] orders = restTemplate.getForObject(url, IncomeDto[].class);
        return orders != null ? List.of(orders) : List.of();
    }
}
