package hse.kpo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.dto.CarRequest;
import hse.kpo.dto.CustomerRequest;
import hse.kpo.interfaces.FacadeIterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class CustomerControllerTest {
    @Autowired
    private FacadeIterface facade;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        facade.getCustomers().clear();
    }

    @Test
    public void testAddCustomer() throws Exception {
        CustomerRequest customerRequest = new CustomerRequest("Artem", 10, 12, 300);
        String json = objectMapper.writeValueAsString(customerRequest);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Artem"))
                .andExpect(jsonPath("$.legPower").value(10))
                .andExpect(jsonPath("$.handPower").value(12))
                .andExpect(jsonPath("$.iq").value(300));
    }

    @Test
    public void testTakeCutomerByName() throws Exception {
        CustomerRequest customerRequest = new CustomerRequest("Artem", 10, 12, 300);
        String json = objectMapper.writeValueAsString(customerRequest);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/customers/Artem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Artem"))
                .andExpect(jsonPath("$.legPower").value(10))
                .andExpect(jsonPath("$.handPower").value(12))
                .andExpect(jsonPath("$.iq").value(300));
    }

    @Test
    public void testTakeCutomers() throws Exception {
        CustomerRequest customerRequest = new CustomerRequest("Artem", 10, 12, 300);
        String json = objectMapper.writeValueAsString(customerRequest);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        CustomerRequest customerRequest1 = new CustomerRequest("Vanya", 11, 13, 200);
        String json1 = objectMapper.writeValueAsString(customerRequest1);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json1))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.name == 'Artem')].legPower").value(10))
                .andExpect(jsonPath("$[?(@.name == 'Artem')].handPower").value(12))
                .andExpect(jsonPath("$[?(@.name == 'Artem')].iq").value(300))
                .andExpect(jsonPath("$[?(@.name == 'Vanya')].legPower").value(11))
                .andExpect(jsonPath("$[?(@.name == 'Vanya')].handPower").value(13))
                .andExpect(jsonPath("$[?(@.name == 'Vanya')].iq").value(200));
    }
}
