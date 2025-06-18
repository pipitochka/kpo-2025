package hse.kpo.controllers;

import hse.kpo.domain.enums.OrderType;
import hse.kpo.dto.OrderDto;
import hse.kpo.services.interfaces.IOrdersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Заказы", description = "Операции с зарплатами")
public class OrdersServiceController {

    private final IOrdersService incomeService;

    @PostMapping("/{id}")
    @Operation(summary = "Сделать заказ")
    public ResponseEntity<OrderDto> addIncome(@PathVariable Long id, @RequestParam double amount, @RequestParam
        OrderType orderType) {
        return ResponseEntity.ok(incomeService.createOrder(id, amount, orderType));
    }

    @GetMapping
    @Operation(summary = "Получить все заказы")
    public ResponseEntity<List<OrderDto>> getIncomes() {
        return ResponseEntity.ok(incomeService.getAllOrders());
    }

}
