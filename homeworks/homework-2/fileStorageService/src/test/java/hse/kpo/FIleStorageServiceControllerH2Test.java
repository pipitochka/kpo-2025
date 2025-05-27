package hse.kpo;

import com.jayway.jsonpath.JsonPath;
import hse.kpo.controllers.FileStorageServiceController;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FIleStorageServiceControllerH2Test {
    @Autowired
    private MockMvc mockMvc;

    private static final Path storagePath = Paths.get("test-storage");

    @BeforeEach
    void setup() throws Exception {
        if (!Files.exists(storagePath)) {
            Files.createDirectories(storagePath);
        }
        Files.list(storagePath).forEach(path -> path.toFile().delete());
    }


    @Test
    public void testUploadFile_andGetById_andGetAll_andDelete() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, world!".getBytes()
        );

        String response = mockMvc.perform(multipart("/api/files").file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("test.txt"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        int id = JsonPath.read(response, "$.id");

        mockMvc.perform(get("/api/files/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("test.txt"));

        mockMvc.perform(get("/api/files"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(id))
                .andExpect(jsonPath("$[0].name").value("test.txt"));

        mockMvc.perform(delete("/api/files/{id}", id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/files/{id}", id))
                .andExpect(status().isNotFound());
    }

    @AfterAll
    static void cleanup() throws IOException {
        if (Files.exists(storagePath)) {
            Files.walk(storagePath)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(java.io.File::delete);
        }
    }
}
