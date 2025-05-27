package hse.kpo;

import com.jayway.jsonpath.JsonPath;
import hse.kpo.domains.realizations.AnalysisRealization;
import hse.kpo.dto.FileAnalysisDto;
import hse.kpo.mappers.interfaces.FileAnalysisMapperInterface;
import hse.kpo.repositories.FileAnalysisRepositoryInterface;
import hse.kpo.services.interfaces.FileAnalysisServiceInterface;
import hse.kpo.services.realizations.FileAnalysisService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FIleAnalysisServiceControllerH2Test {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private FileAnalysisService fileAnalysisService;

    @Autowired
    private FileAnalysisRepositoryInterface repository;

    @MockBean
    private FileAnalysisMapperInterface mapper;

    @BeforeEach
    void setup() {
        repository.deleteAll();

        when(mapper.toDto(Mockito.any())).thenAnswer(invocation -> {
            AnalysisRealization analysis = invocation.getArgument(0);
            return new FileAnalysisDto(
                    analysis.getId(),
                    analysis.getFileId(),
                    analysis.getWordCount(),
                    analysis.getParagraphCount(),
                    analysis.getCharacterCount()
            );
        });
    }

    @Test
    void whenContentReturned_thenAnalysisIsCreated() {
        int fileId = 123;
        String fileContent = "Hello world.\n\nThis is a test paragraph.";

        assertThat(repository.findByFileId(fileId)).isEmpty();


        doReturn(Optional.of(fileContent))
                .when(fileAnalysisService)
                .getFileContentFromFileStorage(fileId);


        var result = fileAnalysisService.getFileAnalysisByFileId(fileId);

        assertThat(result).isPresent();

        var saved = repository.findByFileId(fileId);
        assertThat(saved).isPresent();
        assertThat(saved.get().getCharacterCount()).isEqualTo(fileContent.length());
        assertThat(saved.get().getWordCount()).isEqualTo(7);
        assertThat(saved.get().getParagraphCount()).isEqualTo(2);
    }

    @Test
    void testGetAnalysisByFileId_NotFound() throws Exception {
        mockMvc.perform(get("/api/analysis/99999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllAnalysis() throws Exception {
        AnalysisRealization a1 = new AnalysisRealization();
        a1.setFileId(1);
        a1.setWordCount(5);
        a1.setParagraphCount(1);
        a1.setCharacterCount(25);

        AnalysisRealization a2 = new AnalysisRealization();
        a2.setFileId(2);
        a2.setWordCount(3);
        a2.setParagraphCount(1);
        a2.setCharacterCount(15);

        repository.save(a1);
        repository.save(a2);

        mockMvc.perform(get("/api/analysis"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].fileId").value(1))
                .andExpect(jsonPath("$[1].fileId").value(2));
    }

    @Test
    void testDeleteAnalysis_Found() throws Exception {
        AnalysisRealization a = new AnalysisRealization();
        a.setFileId(1);
        a.setWordCount(7);
        a.setParagraphCount(2);
        a.setCharacterCount(10);

        repository.save(a);

        mockMvc.perform(delete("/api/analysis/" + 1))
                .andExpect(status().isNoContent());

        assertThat(repository.findByFileId(1)).isEmpty();
    }

    @Test
    void testDeleteAnalysis_NotFound() throws Exception {
        mockMvc.perform(delete("/api/analysis/99999"))
                .andExpect(status().isNotFound());
    }
}
