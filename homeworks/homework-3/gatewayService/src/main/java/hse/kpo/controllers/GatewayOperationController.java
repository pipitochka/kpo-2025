package hse.kpo.controllers;

import hse.kpo.dto.AccountDto;
import hse.kpo.dto.OperationDto;
import hse.kpo.dto.responses.AccountResponse;
import hse.kpo.dto.responses.OperationListResponse;
import hse.kpo.dto.responses.OperationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/operations")
@RequiredArgsConstructor
@Tag(name = "Операции", description = "Операции с удалением/получением информации")
public class GatewayOperationController {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${remote.account-service.base-url}")
    private String accountsUrl;

    @GetMapping("/{id}")
    @Operation(summary = "Получить информацию по id об операции")
    public ResponseEntity<OperationResponse> getOperation(@PathVariable Long id){
        String url = accountsUrl + "/api/operations/" + id;
        OperationResponse account = restTemplate.getForObject(url, OperationResponse.class);
        return ResponseEntity.ok(account);
    }

    @GetMapping()
    @Operation(summary = "Получить информацию по о всех операциях")
    public List<OperationDto> getAllOperations(){
        String url = accountsUrl + "/api/operations";
        OperationDto[] response = restTemplate.getForObject(url, OperationDto[].class);
        return response != null ? List.of(response) : List.of();
    }

    @GetMapping("/account/{id}")
    @Operation(summary = "Получить все операции аккаунта с id")
    public ResponseEntity<OperationListResponse> getAllOperationsByAccount(@PathVariable Long id){
        String url = accountsUrl + "/api/operations/account/" + id;
        OperationListResponse response = restTemplate.getForObject(url, OperationListResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить информациюю по id об операции")
    public ResponseEntity<Void> deleteOperation(@PathVariable Long id){
        String url = accountsUrl + "/api/operations/" + id;
        restTemplate.delete(url);
        return ResponseEntity.noContent().build();
    }
}
