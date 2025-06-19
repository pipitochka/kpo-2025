package hse.kpo.controllers;

import hse.kpo.dto.AccountDto;
import hse.kpo.dto.OperationDto;
import hse.kpo.dto.requests.CreateAccountRequest;
import hse.kpo.dto.responses.AccountResponse;
import hse.kpo.dto.responses.OperationListResponse;
import hse.kpo.dto.responses.OperationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@Tag(name = "Аккаунты", description = "Операции с созданием/удалением/получением информации")
public class GatewayAccountController {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${remote.account-service.base-url}")
    private String accountsUrl;


    @PostMapping()
    @Operation(summary = "Создать аккаунт с балансом")
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountRequest request){
        String url = accountsUrl + "/api/accounts";
        AccountResponse account = restTemplate.getForObject(url, AccountResponse.class);
        if (account == null || !account.isSuccess()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account.getOperation());

    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить информацию по id об аккаунте")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        String url = accountsUrl + "/api/accounts/" + id;
        try {
            AccountResponse account = restTemplate.getForObject(url, AccountResponse.class);
            if (account == null || !account.isSuccess()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(account.getOperation());
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    @Operation(summary = "Получить все аккаунты")
    public List<AccountDto> getAllAccounte(){
        String url = accountsUrl + "/api/accounts";
        AccountDto[] response = restTemplate.getForObject(url, AccountDto[].class);
        return response != null ? List.of(response) : List.of();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить аккаунт по id")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
        String url = accountsUrl + "/api/accounts/" + id;
        restTemplate.delete(url);
        return ResponseEntity.ok().build();
    }


}
