package hse.kpo;

import hse.kpo.controllers.GatewayFileAnalysisController;
import hse.kpo.dto.FileAnalysisDto;
import hse.kpo.services.interfaces.GatewayServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GatewayFileAnalysisControllerTest {

    @Mock
    private GatewayServiceInterface gatewayService;

    @InjectMocks
    private GatewayFileAnalysisController controller;

    private FileAnalysisDto sampleDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleDto = new FileAnalysisDto(1, 10, 100, 5, 1000);
    }

    @Test
    void getAnalysisByFileId_whenFound_returnsOk() {
        when(gatewayService.getFileAnalysisByFileId(10)).thenReturn(Optional.of(sampleDto));

        ResponseEntity<FileAnalysisDto> response = controller.getAnalysisByFileId(10);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(sampleDto);
        verify(gatewayService).getFileAnalysisByFileId(10);
    }

    @Test
    void getAnalysisByFileId_whenNotFound_returnsNotFound() {
        when(gatewayService.getFileAnalysisByFileId(10)).thenReturn(Optional.empty());

        ResponseEntity<FileAnalysisDto> response = controller.getAnalysisByFileId(10);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
        verify(gatewayService).getFileAnalysisByFileId(10);
    }

    @Test
    void getAnalysis_returnsListOfDtos() {
        List<FileAnalysisDto> list = List.of(
                sampleDto,
                new FileAnalysisDto(2, 20, 200, 10, 2000)
        );
        when(gatewayService.getAllFileAnalysis()).thenReturn(list);

        ResponseEntity<List<FileAnalysisDto>> response = controller.getAnalysis();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(list);
        verify(gatewayService).getAllFileAnalysis();
    }

    @Test
    void deleteAnalysis_whenDeleted_returnsNoContent() {
        when(gatewayService.deleteFileAnalysis(10)).thenReturn(true);

        ResponseEntity<Void> response = controller.deleteAnalysis(10);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(gatewayService).deleteFileAnalysis(10);
    }

    @Test
    void deleteAnalysis_whenNotFound_returnsNotFound() {
        when(gatewayService.deleteFileAnalysis(10)).thenReturn(false);

        ResponseEntity<Void> response = controller.deleteAnalysis(10);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(gatewayService).deleteFileAnalysis(10);
    }
}
