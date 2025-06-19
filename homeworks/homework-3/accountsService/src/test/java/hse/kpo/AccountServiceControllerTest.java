package hse.kpo;


import hse.kpo.dto.AccountDto;
import hse.kpo.dto.requests.CreateAccountRequest;
import hse.kpo.dto.responses.AccountResponse;
import hse.kpo.services.interfaces.IAccountService;
import hse.kpo.controllers.AccountServiceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceControllerTest {

    @Mock
    private IAccountService accountService;

    @InjectMocks
    private AccountServiceController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAccount_ShouldReturnOkWithResponse() {
        CreateAccountRequest request = new CreateAccountRequest("MyAccount", 100.0);
        AccountResponse mockResponse = new AccountResponse(true,
                new AccountDto(10L, "qq", 10), null);

        when(accountService.createAccount("MyAccount", 100.0)).thenReturn(mockResponse);

        ResponseEntity<AccountResponse> response = controller.createAccount(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
        verify(accountService).createAccount("MyAccount", 100.0);
    }

    @Test
    void getAccountById_WhenFound_ShouldReturnOk() {
        Long accountId = 1L;
        AccountResponse mockResponse = new AccountResponse(true,
                new AccountDto(10L, "qq", 10), null);

        when(accountService.getAccount(accountId)).thenReturn(mockResponse);

        ResponseEntity<AccountResponse> response = controller.getAccountById(accountId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    void getAccountById_WhenNotFound_ShouldReturn404() {
        Long accountId = 1L;
        AccountResponse mockResponse = new AccountResponse(false, null, null);

        when(accountService.getAccount(accountId)).thenReturn(mockResponse);

        ResponseEntity<AccountResponse> response = controller.getAccountById(accountId);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void getAll_ShouldReturnListOfAccounts() {
        List<AccountDto> mockList = List.of(new AccountDto(1L, "Account1", 100.0));
        when(accountService.getAllAccounts()).thenReturn(mockList);

        ResponseEntity<List<AccountDto>> response = controller.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockList, response.getBody());
    }

    @Test
    void deleteAccount_WhenDeleted_ShouldReturnNoContent() {
        Long accountId = 1L;
        when(accountService.deleteAccount(accountId)).thenReturn(true);

        ResponseEntity<Void> response = controller.deleteAccount(accountId);

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void deleteAccount_WhenNotDeleted_ShouldReturnNotFound() {
        Long accountId = 1L;
        when(accountService.deleteAccount(accountId)).thenReturn(false);

        ResponseEntity<Void> response = controller.deleteAccount(accountId);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
