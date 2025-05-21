package hse.kpo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.controllers.car.CarController;
import hse.kpo.domains.objects.Car;
import hse.kpo.dto.CarRequest;
import hse.kpo.interfaces.FacadeIterface;
import hse.kpo.services.HseCarService;
import hse.kpo.storages.CarStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BeanPropertyBindingResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class CarControllerTest {

    @Autowired
    private FacadeIterface facade;

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
                .andExpect(jsonPath("$.engineType").value("PedalEngine(size=15)"));
    }

    @Test
    public void testGetCarByVin() throws Exception {
        CarRequest carRequest = new CarRequest("PEDAL", 15);
        String json = objectMapper.writeValueAsString(carRequest);

        mockMvc.perform(post("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        int vin = facade.getCars().get(0).getVin();

        mockMvc.perform(get("/api/cars/" + vin))
                .andExpect(status().isOk());
    }

}
