package hse.kpo.controllers;

import hse.kpo.domain.enums.OrderType;
import hse.kpo.dto.OrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Заказы", description = "Операции с зарплатами")
public class GatewayOrdersController {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${remote.orders-service.base-url}")
    private String accountsUrl;

    @PostMapping("/{id}")
    @Operation(summary = "Сделать заказ")
    public ResponseEntity<OrderDto> addIncome(@PathVariable Long id, @RequestParam double amount, @RequestParam
    OrderType orderType){
        String url = accountsUrl + "/api/orders/" + id + "?amount=" + amount + "&orderType=" + orderType;
        ResponseEntity<OrderDto> response = restTemplate.postForEntity(url, null, OrderDto.class);
        return response;
    }

    @GetMapping
    @Operation(summary = "Получить все заказы")
    public List<OrderDto> getIncomes(){
        String url = accountsUrl + "/api/orders";
        OrderDto[] orders = restTemplate.getForObject(url, OrderDto[].class);
        return orders != null ? List.of(orders) : List.of();
    }
}
