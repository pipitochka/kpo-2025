package hse.kpo.controllers;

import hse.kpo.dto.OperationDto;
import hse.kpo.dto.responses.AccountResponse;
import hse.kpo.dto.responses.OperationListResponse;
import hse.kpo.dto.responses.OperationResponse;
import hse.kpo.services.interfaces.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.service.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operations")
@RequiredArgsConstructor
@Tag(name = "Операции", description = "Операции с удалением/получением информации")
public class OperationController {
    private final IAccountService accountService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить информацию по id об операции")
    public ResponseEntity<OperationResponse> getOperation(@PathVariable Long id) {
        OperationResponse response = accountService.getOperation(id);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    @Operation(summary = "Получить информацию по о всех операциях")
    public ResponseEntity<List<OperationDto>> getAllOperations() {
        return ResponseEntity.ok(accountService.getAllOperations());
    }

    @GetMapping("/account/{id}")
    @Operation(summary = "Получить все операции аккаунта с id")
    public ResponseEntity<OperationListResponse> getAllOperationsByAccount(@PathVariable Long id){
        OperationListResponse operationListResponse = accountService.getOperationsByAccountId(id);
        if (operationListResponse.isSuccess()) {
            return ResponseEntity.ok(operationListResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить информациюю по id об операции")
    public ResponseEntity<Void> deleteOperation(@PathVariable Long id) {
        boolean deleted = accountService.deleteOperation(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
