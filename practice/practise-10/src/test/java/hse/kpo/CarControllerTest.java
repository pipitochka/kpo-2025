package hse.kpo;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.dto.request.CarRequest;
import hse.kpo.dto.request.CustomerRequest;
import hse.kpo.dto.response.CarResponse;
import hse.kpo.enums.EngineTypes;
import hse.kpo.interfaces.FacadeInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * class to test car controller.
 */
@AutoConfigureMockMvc
@SpringBootTest
public class CarControllerTest {

    @Autowired
    private FacadeInterface facade;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        facade.getCustomers().clear();
        facade.getCars().clear();
    }

    @Test
    public void testAddCar() throws Exception {
        CarRequest carRequest = new CarRequest("PEDAL", 15);
        String json = objectMapper.writeValueAsString(carRequest);

        mockMvc.perform(post("/api/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.engine.size").value(15))
                .andExpect(jsonPath("$.engineType").value("PEDAL"));
    }

    @Test
    public void testGetCarByVin() throws Exception {
        CarRequest carRequest = new CarRequest("PEDAL", 15);
        String json = objectMapper.writeValueAsString(carRequest);

        mockMvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().isCreated());

        int vin = facade.getCars().get(0).getVin();

        mockMvc.perform(get("/api/cars/" + vin)).andExpect(status().isOk());
    }

    @Test
    public void testFiltredCars() throws Exception {
        CarRequest carRequest = new CarRequest("PEDAL", 10);
        String json = objectMapper.writeValueAsString(carRequest);

        mockMvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().isCreated());

        CarRequest carRequest1 = new CarRequest("PEDAL", 13);
        String json1 = objectMapper.writeValueAsString(carRequest1);

        mockMvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON)
                .content(json1)).andExpect(status().isCreated());

        CarRequest carRequest2 = new CarRequest("HAND", null);
        String json2 = objectMapper.writeValueAsString(carRequest2);

        mockMvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON)
                .content(json2)).andExpect(status().isCreated());

        mockMvc.perform(get("/api/cars")
                .param("engineType", "PEDAL")).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].engineType").value("PEDAL"))
                .andExpect(jsonPath("$[1].engineType").value("PEDAL"));

    }

    @Test
    public void testSellCars() throws Exception {
        CarRequest carRequest = new CarRequest("PEDAL", 13);
        String json = objectMapper.writeValueAsString(carRequest);

        mockMvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().isCreated());

        CarRequest carRequest1 = new CarRequest("PEDAL", 9);
        String json1 = objectMapper.writeValueAsString(carRequest1);

        mockMvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON)
                .content(json1)).andExpect(status().isCreated());

        CustomerRequest customerRequest = new CustomerRequest("Artem", 15, 12, 300);
        String json3 = objectMapper.writeValueAsString(customerRequest);

        mockMvc.perform(post("/api/customers").contentType(MediaType.APPLICATION_JSON)
                .content(json3)).andExpect(status().isCreated());

        CustomerRequest customerRequest1 = new CustomerRequest("Vanya", 10, 13, 200);
        String json4 = objectMapper.writeValueAsString(customerRequest1);

        mockMvc.perform(post("/api/customers").contentType(MediaType.APPLICATION_JSON)
                .content(json4)).andExpect(status().isCreated());


        mockMvc.perform(post("/api/cars/sell")).andExpect(status().isOk());

        mockMvc.perform(get("/api/customers/Artem")).andExpect(status().isOk())
                .andExpect(jsonPath("$.car").isNotEmpty());

        mockMvc.perform(get("/api/customers/Vanya")).andExpect(status().isOk())
                .andExpect(jsonPath("$.car").isNotEmpty());
    }

    @Test
    public void updateAndDeleteCar() throws Exception {
        CarRequest carRequest = new CarRequest("PEDAL", 13);
        String json = objectMapper.writeValueAsString(carRequest);

        mockMvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().isCreated());

        mockMvc.perform(get("/api/cars")).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].engineType").value("PEDAL"));


        int vin = facade.getCars().get(0).getVin();
        CarRequest newCarRequest = new CarRequest("HAND", 13);
        String newJson = objectMapper.writeValueAsString(newCarRequest);

        mockMvc.perform(put("/api/cars/" + vin).contentType(MediaType.APPLICATION_JSON)
                .content(newJson)).andExpect(status().isOk());

        mockMvc.perform(get("/api/cars")).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].engineType").value("HAND"));

        mockMvc.perform(delete("/api/cars/" + vin)).andExpect(status().isNoContent());

        mockMvc.perform(get("/api/cars")).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DisplayName("Создание педального автомобиля с валидными параметрами")
    void createPedalCar_ValidData_Returns201() throws Exception {
        CarRequest request =new CarRequest("PEDAL", 10);

        String responseJson = mockMvc.perform(post("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        CarResponse response = objectMapper.readValue(responseJson, CarResponse.class);
        assertAll(
                () -> assertNotNull(response.vin(), "VIN должен быть присвоен"),
                () -> assertEquals(EngineTypes.PEDAL.name(), response.engineType(), "Тип двигателя должен быть PEDAL")
        );
    }
}
