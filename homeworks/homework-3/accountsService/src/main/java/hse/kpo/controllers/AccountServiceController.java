package hse.kpo.controllers;

import hse.kpo.domain.realizations.objects.Account;
import hse.kpo.dto.AccountDto;
import hse.kpo.dto.requests.CreateAccountRequest;
import hse.kpo.dto.responses.AccountResponse;
import hse.kpo.dto.responses.OperationListResponse;
import hse.kpo.services.interfaces.IAccountService;
import hse.kpo.services.realizations.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@Tag(name = "Аккаунты", description = "Операции с созданием/удалением/получением информации")
public class AccountServiceController {

    private final IAccountService accountService;

    @PostMapping()
    @Operation(summary = "Создать аккаунт с балансом")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody CreateAccountRequest request) {
        AccountResponse response = accountService.createAccount(request.getAccountName(), request.getAccountBalance());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить информацию по id об аккаунте")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id) {
        AccountResponse response = accountService.getAccount(id);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    @Operation(summary = "Получить все аккаунты")
    public ResponseEntity<List<AccountDto>> getAll(){
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить аккаунт по id")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        boolean deleted = accountService.deleteAccount(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }
}
