package hse.kpo;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.controllers.FileStorageServiceController;
import hse.kpo.dto.FileDto;
import hse.kpo.services.realizations.FileStorageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FileStorageServiceController.class)
class FileStorageServiceControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FileStorageService fileStorageService;

    @Test
    void testGetFileById_Found() throws Exception {
        FileDto fileDto = new FileDto(1, "test.txt", "text/plain", "123");
        Mockito.when(fileStorageService.getFileById(1)).thenReturn(Optional.of(fileDto));

        mockMvc.perform(get("/api/files/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("test.txt"))
                .andExpect(jsonPath("$.path").value("text/plain"))
                .andExpect(jsonPath("$.hash").value("123"));
    }

    @Test
    void testGetFileById_NotFound() throws Exception {
        Mockito.when(fileStorageService.getFileById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/files/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateFile_Success() throws Exception {
        MockMultipartFile mockFile = new MockMultipartFile(
                "file", "filename.txt", "text/plain", "file content".getBytes());

        FileDto savedFile = new FileDto(1, "filename.txt", "text/plain", "12");
        Mockito.when(fileStorageService.saveFile(any())).thenReturn(savedFile);

        mockMvc.perform(multipart("/api/files").file(mockFile))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("filename.txt"));
    }

    @Test
    void testGetAllFiles() throws Exception {
        List<FileDto> files = List.of(
                new FileDto(1, "file1.txt", "text/plain", "10"),
                new FileDto(2, "file2.txt", "text/plain", "20")
        );
        Mockito.when(fileStorageService.getAllFiles()).thenReturn(files);

        mockMvc.perform(get("/api/files"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("file1.txt"));
    }

    @Test
    void testDeleteFile_Success() throws Exception {
        Mockito.when(fileStorageService.deleteFile(1)).thenReturn(true);

        mockMvc.perform(delete("/api/files/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteFile_NotFound() throws Exception {
        Mockito.when(fileStorageService.deleteFile(99)).thenReturn(false);

        mockMvc.perform(delete("/api/files/99"))
                .andExpect(status().isNotFound());
    }
}