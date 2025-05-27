package hse.kpo;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.controllers.FileAnalysisController;
import hse.kpo.dto.FileAnalysisDto;
import hse.kpo.services.interfaces.FileAnalysisServiceInterface;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FileAnalysisController.class)
class FileAnalysisServiceControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileAnalysisServiceInterface service;

    @Test
    void getAnalysisByFileId_shouldReturnDto_whenFound() throws Exception {
        FileAnalysisDto dto = new FileAnalysisDto(1, 1, 1, 1, 1);
        when(service.getFileAnalysisByFileId(1)).thenReturn(Optional.of(dto));

        mockMvc.perform(get("/api/analysis/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getAnalysisByFileId_shouldReturnNotFound_whenNotFound() throws Exception {
        when(service.getFileAnalysisByFileId(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/analysis/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAnalysis_shouldReturnList() throws Exception {
        when(service.getAllFileAnalysis()).thenReturn(List.of(
                new FileAnalysisDto(1, 1, 1, 1, 1)));

        mockMvc.perform(get("/api/analysis"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAnalysis_shouldReturnNoContent_whenDeleted() throws Exception {
        when(service.deleteFileAnalysis(1)).thenReturn(true);

        mockMvc.perform(delete("/api/analysis/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteAnalysis_shouldReturnNotFound_whenNotFound() throws Exception {
        when(service.deleteFileAnalysis(1)).thenReturn(false);

        mockMvc.perform(delete("/api/analysis/1"))
                .andExpect(status().isNotFound());
    }
}