package hse.kpo;

import hse.kpo.domain.MultipartFileResource;
import hse.kpo.dto.FileAnalysisDto;
import hse.kpo.dto.FileStorageDto;
import hse.kpo.services.realizations.GatewayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class GatewayServiceTest {

    @InjectMocks
    private GatewayService gatewayService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        setField(gatewayService, "fileStorageBaseUrl", "http://filestorage");
        setField(gatewayService, "fileAnalysisBaseUrl", "http://analysis");
    }

    private static void setField(Object target, String fieldName, Object value) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetFileAnalysisByFileId_ReturnsDto() {
        FileAnalysisDto dto = new FileAnalysisDto(1, 1, 10, 2, 100);
        when(restTemplate.getForObject("http://analysis/api/analysis/1", FileAnalysisDto.class)).thenReturn(dto);

        Optional<FileAnalysisDto> result = gatewayService.getFileAnalysisByFileId(1);

        assertThat(result).isPresent();
        assertThat(result.get().getFileId()).isEqualTo(1);
    }

    @Test
    void testGetFileAnalysisByFileId_ReturnsEmptyOnException() {
        when(restTemplate.getForObject(anyString(), eq(FileAnalysisDto.class))).thenThrow(new RuntimeException("Error"));

        Optional<FileAnalysisDto> result = gatewayService.getFileAnalysisByFileId(999);

        assertThat(result).isEmpty();
    }

    @Test
    void testGetAllFileAnalysis_ReturnsList() {
        FileAnalysisDto[] arr = {
                new FileAnalysisDto(1, 1, 5, 1, 50),
                new FileAnalysisDto(2, 2, 10, 3, 120)
        };
        when(restTemplate.getForObject("http://analysis/api/analysis", FileAnalysisDto[].class)).thenReturn(arr);

        List<FileAnalysisDto> list = gatewayService.getAllFileAnalysis();

        assertThat(list).hasSize(2);
        assertThat(list.get(0).getId()).isEqualTo(1);
    }

    @Test
    void testGetAllFileAnalysis_ReturnsEmptyListOnException() {
        when(restTemplate.getForObject(anyString(), eq(FileAnalysisDto[].class))).thenThrow(new RuntimeException("fail"));

        List<FileAnalysisDto> list = gatewayService.getAllFileAnalysis();

        assertThat(list).isEmpty();
    }

    @Test
    void testDeleteFileAnalysis_ReturnsTrueOnSuccess() {
        doNothing().when(restTemplate).delete("http://analysis/api/analysis/1");

        boolean deleted = gatewayService.deleteFileAnalysis(1);

        assertThat(deleted).isTrue();
    }

    @Test
    void testDeleteFileAnalysis_ReturnsFalseOnNotFound() {
        doThrow(HttpClientErrorException.NotFound.class).when(restTemplate).delete(anyString());

        boolean deleted = gatewayService.deleteFileAnalysis(123);

        assertThat(deleted).isFalse();
    }

    @Test
    void testDeleteFileAnalysis_ReturnsFalseOnOtherException() {
        doThrow(RuntimeException.class).when(restTemplate).delete(anyString());

        boolean deleted = gatewayService.deleteFileAnalysis(123);

        assertThat(deleted).isFalse();
    }

    @Test
    void testSaveFile_ReturnsNullOnException() throws Exception {
        MultipartFile mockFile = mock(MultipartFile.class);

        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(FileStorageDto.class)))
                .thenThrow(new RuntimeException("fail"));

        FileStorageDto result = gatewayService.saveFile(mockFile);

        assertThat(result).isNull();
    }

    @Test
    void testGetFileDescriptionById_ReturnsDto() {
        FileStorageDto dto = new FileStorageDto(1, "file", "type", "path");
        when(restTemplate.getForObject("http://filestorage/api/files/1", FileStorageDto.class)).thenReturn(dto);

        Optional<FileStorageDto> result = gatewayService.getFileDescriptionById(1);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(1);
    }

    @Test
    void testGetFileDescriptionById_ReturnsEmptyOnException() {
        when(restTemplate.getForObject(anyString(), eq(FileStorageDto.class))).thenThrow(new RuntimeException("fail"));

        Optional<FileStorageDto> result = gatewayService.getFileDescriptionById(42);

        assertThat(result).isEmpty();
    }

    @Test
    void testGetAllFiles_ReturnsList() {
        FileStorageDto[] arr = {
                new FileStorageDto(1, "file1", "type", "path"),
                new FileStorageDto(2, "file2", "type", "path")
        };
        when(restTemplate.getForObject("http://filestorage/api/files", FileStorageDto[].class)).thenReturn(arr);

        List<FileStorageDto> result = gatewayService.getAllFiles();

        assertThat(result).hasSize(2);
        assertThat(result.get(1).getId()).isEqualTo(2);
    }

    @Test
    void testGetAllFiles_ReturnsEmptyOnException() {
        when(restTemplate.getForObject(anyString(), eq(FileStorageDto[].class))).thenThrow(new RuntimeException("fail"));

        List<FileStorageDto> result = gatewayService.getAllFiles();

        assertThat(result).isEmpty();
    }

    @Test
    void testDeleteFile_ReturnsTrueOnSuccess() {
        doNothing().when(restTemplate).delete("http://filestorage/api/files/1");

        boolean deleted = gatewayService.deleteFile(1);

        assertThat(deleted).isTrue();
    }

    @Test
    void testDeleteFile_ReturnsFalseOnNotFound() {
        doThrow(HttpClientErrorException.NotFound.class).when(restTemplate).delete(anyString());

        boolean deleted = gatewayService.deleteFile(99);

        assertThat(deleted).isFalse();
    }

    @Test
    void testDeleteFile_ReturnsFalseOnOtherException() {
        doThrow(RuntimeException.class).when(restTemplate).delete(anyString());

        boolean deleted = gatewayService.deleteFile(99);

        assertThat(deleted).isFalse();
    }

    @Test
    void testGetFileContentById_ReturnsContent() {
        when(restTemplate.getForObject("http://filestorage/api/files/1", String.class)).thenReturn("file content");

        Optional<String> content = gatewayService.getFileContentById(1);

        assertThat(content).isPresent();
        assertThat(content.get()).isEqualTo("file content");
    }

    @Test
    void testGetFileContentById_ReturnsEmptyOnException() {
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenThrow(new RuntimeException("fail"));

        Optional<String> content = gatewayService.getFileContentById(42);

        assertThat(content).isEmpty();
    }
}
