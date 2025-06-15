package hse.kpo.controllers;

import hse.kpo.domain.objects.Income;
import hse.kpo.services.interfaces.IIncomeService;
import hse.kpo.services.realizations.IncomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incomes")
@RequiredArgsConstructor
@Tag(name = "Зарплаты", description = "Операции с зарплатами")
public class IncomeServiceController {

    private final IIncomeService incomeService;

    @PostMapping("/{id}")
    @Operation(summary = "Увеличить баланс по id")
    public ResponseEntity<Income> addIncome(@PathVariable Long id, @RequestParam double amount) {
        return ResponseEntity.ok(incomeService.createIncome(id, amount));
    }

    @GetMapping
    @Operation(summary = "Получить все увеличения счетов")
    public ResponseEntity<List<Income>> getIncomes() {
        return ResponseEntity.ok(incomeService.getAllIncomes());
    }
}
