package hse.kpo;

import hse.kpo.controllers.OperationController;
import hse.kpo.domain.enums.OperationResult;
import hse.kpo.domain.enums.OperationType;
import hse.kpo.dto.OperationDto;
import hse.kpo.dto.responses.OperationListResponse;
import hse.kpo.dto.responses.OperationResponse;
import hse.kpo.services.interfaces.IAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OperationControllerTest {

    @Autowired
    private IAccountService accountService;

    @InjectMocks
    private OperationController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getOperation_WhenFound_ShouldReturnOperation() {
        Long id = 1L;
        OperationResponse mockResponse = new OperationResponse(true, new OperationDto(1L,"qq",
                1L, 100, OperationType.INCOME, OperationResult.PENDING), null);

        when(accountService.getOperation(id)).thenReturn(mockResponse);

        ResponseEntity<OperationResponse> response = controller.getOperation(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        verify(accountService, times(1)).getOperation(id);
    }

    @Test
    void getOperation_WhenNotFound_ShouldReturn404() {
        Long id = 1L;
        OperationResponse mockResponse = new OperationResponse(false, null, null);

        when(accountService.getOperation(id)).thenReturn(mockResponse);

        ResponseEntity<OperationResponse> response = controller.getOperation(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(accountService, times(1)).getOperation(id);
    }

    @Test
    void getAllOperations_ShouldReturnList() {
        List<OperationDto> operations = List.of(new OperationDto(1L,"qq",
                1L, 100, OperationType.INCOME, OperationResult.PENDING),
                new OperationDto(1L,"qq1",
                2L, 100, OperationType.INCOME, OperationResult.PENDING));

        when(accountService.getAllOperations()).thenReturn(operations);

        ResponseEntity<List<OperationDto>> response = controller.getAllOperations();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(operations, response.getBody());
        verify(accountService, times(1)).getAllOperations();
    }



    @Test
    void getAllOperationsByAccount_WhenNotFound_ShouldReturn404() {
        Long accountId = 1L;
        OperationListResponse mockResponse = new OperationListResponse(false, null, null);

        when(accountService.getOperationsByAccountId(accountId)).thenReturn(mockResponse);

        ResponseEntity<OperationListResponse> response = controller.getAllOperationsByAccount(accountId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(accountService, times(1)).getOperationsByAccountId(accountId);
    }

    @Test
    void deleteOperation_WhenDeleted_ShouldReturnNoContent() {
        Long id = 1L;

        when(accountService.deleteOperation(id)).thenReturn(true);

        ResponseEntity<Void> response = controller.deleteOperation(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(accountService, times(1)).deleteOperation(id);
    }

    @Test
    void deleteOperation_WhenNotFound_ShouldReturn404() {
        Long id = 1L;

        when(accountService.deleteOperation(id)).thenReturn(false);

        ResponseEntity<Void> response = controller.deleteOperation(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(accountService, times(1)).deleteOperation(id);
    }
}
