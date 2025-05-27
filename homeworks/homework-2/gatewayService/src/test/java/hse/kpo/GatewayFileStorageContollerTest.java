package hse.kpo;

import hse.kpo.controllers.GatewayFileStorageContoller;
import hse.kpo.dto.FileStorageDto;
import hse.kpo.services.interfaces.GatewayServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GatewayFileStorageContollerTest {

    @Mock
    private GatewayServiceInterface gatewayService;

    @InjectMocks
    private GatewayFileStorageContoller controller;

    private FileStorageDto sampleDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleDto = new FileStorageDto(1, "file.txt", "text/plain", "additionalInfo");
    }

    @Test
    void getFileById_whenFound_returnsOk() {
        when(gatewayService.getFileDescriptionById(1)).thenReturn(Optional.of(sampleDto));

        ResponseEntity<FileStorageDto> response = controller.getFileById(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(sampleDto);
        verify(gatewayService).getFileDescriptionById(1);
    }

    @Test
    void getFileById_whenNotFound_returnsNotFound() {
        when(gatewayService.getFileDescriptionById(1)).thenReturn(Optional.empty());

        ResponseEntity<FileStorageDto> response = controller.getFileById(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
        verify(gatewayService).getFileDescriptionById(1);
    }

    @Test
    void createFile_returnsSavedFile() {
        MockMultipartFile mockFile = new MockMultipartFile(
                "file", "test.txt", "text/plain", "Hello World".getBytes());

        when(gatewayService.saveFile(mockFile)).thenReturn(sampleDto);

        ResponseEntity<FileStorageDto> response = controller.createFile(mockFile);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(sampleDto);
        verify(gatewayService).saveFile(mockFile);
    }

    @Test
    void getAllFiles_returnsList() {
        List<FileStorageDto> files = List.of(
                sampleDto,
                new FileStorageDto(2, "another.txt", "application/pdf", "moreInfo")
        );
        when(gatewayService.getAllFiles()).thenReturn(files);

        ResponseEntity<List<FileStorageDto>> response = controller.getAllFiles();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(files);
        verify(gatewayService).getAllFiles();
    }

    @Test
    void deleteFileById_whenDeleted_returnsNoContent() {
        when(gatewayService.deleteFile(1)).thenReturn(true);

        ResponseEntity<Void> response = controller.deleteFileById(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(gatewayService).deleteFile(1);
    }

    @Test
    void deleteFileById_whenNotFound_returnsNotFound() {
        when(gatewayService.deleteFile(1)).thenReturn(false);

        ResponseEntity<Void> response = controller.deleteFileById(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(gatewayService).deleteFile(1);
    }

    @Test
    void getFileContentById_whenFound_returnsContent() {
        String content = "File content here";
        when(gatewayService.getFileContentById(1)).thenReturn(Optional.of(content));

        ResponseEntity<String> response = controller.getFileContentById(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(content);
        verify(gatewayService).getFileContentById(1);
    }

    @Test
    void getFileContentById_whenNotFound_returnsNotFound() {
        when(gatewayService.getFileContentById(1)).thenReturn(Optional.empty());

        ResponseEntity<String> response = controller.getFileContentById(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
        verify(gatewayService).getFileContentById(1);
    }
}
