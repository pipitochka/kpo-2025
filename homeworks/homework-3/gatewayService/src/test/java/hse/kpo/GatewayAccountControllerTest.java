package hse.kpo;

import hse.kpo.controllers.GatewayAccountController;
import hse.kpo.dto.AccountDto;
import hse.kpo.dto.requests.CreateAccountRequest;
import hse.kpo.dto.responses.AccountResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GatewayAccountControllerTest {

    @InjectMocks
    private GatewayAccountController controller;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller.accountsUrl = "http://mock-url";
    }

    @Test
    void createAccount_Success() {
        CreateAccountRequest request = new CreateAccountRequest("QQ", 100);
        AccountResponse accountResponse = mock(AccountResponse.class);
        AccountDto accountDto = new AccountDto(10L, "QQ", 100);
        when(accountResponse.isSuccess()).thenReturn(true);
        when(accountResponse.getOperation()).thenReturn(accountDto);

        when(restTemplate.getForObject("http://mock-url/api/accounts", AccountResponse.class))
                .thenReturn(accountResponse);

        ResponseEntity<AccountDto> response = controller.createAccount(request);
        assertEquals(ResponseEntity.ok(accountDto), response);
    }

    @Test
    void getAccountById_Success() {
        Long id = 1L;
        AccountResponse accountResponse = mock(AccountResponse.class);
        AccountDto accountDto = new AccountDto();
        when(accountResponse.isSuccess()).thenReturn(true);
        when(accountResponse.getOperation()).thenReturn(accountDto);

        when(restTemplate.getForObject("http://mock-url/api/accounts/" + id, AccountResponse.class))
                .thenReturn(accountResponse);

        ResponseEntity<AccountDto> response = controller.getAccountById(id);
        assertEquals(ResponseEntity.ok(accountDto), response);
    }

    @Test
    void getAllAccounts_ReturnsList() {
        AccountDto[] accounts = new AccountDto[]{new AccountDto(), new AccountDto()};
        when(restTemplate.getForObject("http://mock-url/api/accounts", AccountDto[].class))
                .thenReturn(accounts);

        List<AccountDto> result = controller.getAllAccounte();
        assertEquals(2, result.size());
    }

    @Test
    void getAllAccounts_ReturnsEmptyListOnNull() {
        when(restTemplate.getForObject("http://mock-url/api/accounts", AccountDto[].class))
                .thenReturn(null);

        List<AccountDto> result = controller.getAllAccounte();
        assertTrue(result.isEmpty());
    }

    @Test
    void deleteAccount_CallsDeleteAndReturnsOk() {
        Long id = 1L;
        doNothing().when(restTemplate).delete("http://mock-url/api/accounts/" + id);

        ResponseEntity<Void> response = controller.deleteAccount(id);
        verify(restTemplate, times(1)).delete("http://mock-url/api/accounts/" + id);
        assertEquals(ResponseEntity.ok().build(), response);
    }
}
