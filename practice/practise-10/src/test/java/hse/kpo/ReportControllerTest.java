package hse.kpo;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.dto.request.CarRequest;
import hse.kpo.dto.request.ReportRequest;
import hse.kpo.enums.ReportFormat;
import hse.kpo.interfaces.FacadeInterface;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * class to test report controller.
 */
@AutoConfigureMockMvc
@SpringBootTest
public class ReportControllerTest {

    @Autowired
    private FacadeInterface facade;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testMakingReports() throws Exception {
        CarRequest carRequest = new CarRequest("PEDAL", 13);
        String json = objectMapper.writeValueAsString(carRequest);

        mockMvc.perform(post("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        CarRequest carRequest1 = new CarRequest("PEDAL", 9);
        String json1 = objectMapper.writeValueAsString(carRequest1);

        mockMvc.perform(post("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json1))
                .andExpect(status().isCreated());

        ReportRequest reportRequest = new ReportRequest(ReportFormat.JSON, "data.json");
        String jsonReport = objectMapper.writeValueAsString(reportRequest);

        mockMvc.perform(post("/api/reports")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonReport))
                .andExpect(status().isOk());

        String filePath = "reports/data.json";
        assertThat(Files.exists(Paths.get(filePath))).isTrue();

        String fileContent = Files.readString(Paths.get(filePath));

        CarRequest[] carsFromFile = objectMapper.readValue(fileContent, CarRequest[].class);

        assertThat(carsFromFile).hasSize(2);
        assertThat(carsFromFile[0].engineType()).isEqualTo("PEDAL");
    }
}
