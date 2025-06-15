package hse.kpo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.dto.requests.CarRequest;
import hse.kpo.dto.requests.CustomerRequest;
import hse.kpo.dto.requests.ShipRequest;
import hse.kpo.interfaces.FacadeInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * class to test ship controller.
 */
@AutoConfigureMockMvc
@SpringBootTest
public class ShipControllerTest {

    @Autowired
    private FacadeInterface facade;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        facade.getCustomers().clear();
        facade.getShips().clear();
    }

    @Test
    public void testAddShip() throws Exception {
        ShipRequest shipRequest = new ShipRequest("PEDAL", 15);
        String json = objectMapper.writeValueAsString(shipRequest);

        mockMvc.perform(post("/api/ships")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.engine.size").value(15))
                .andExpect(jsonPath("$.engineType").value("PEDAL"));
    }

    @Test
    public void testShipCarByVin() throws Exception {
        ShipRequest shipRequest = new ShipRequest("PEDAL", 15);
        String json = objectMapper.writeValueAsString(shipRequest);

        mockMvc.perform(post("/api/ships")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        int vin = facade.getShips().get(0).getVin();

        mockMvc.perform(get("/api/ships/" + vin))
                .andExpect(status().isOk());
    }

    @Test
    public void testFiltredShips() throws Exception {
        ShipRequest shipRequest = new ShipRequest("PEDAL", 15);
        String json = objectMapper.writeValueAsString(shipRequest);

        mockMvc.perform(post("/api/ships")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        ShipRequest shipRequest1 = new ShipRequest("PEDAL", 13);
        String json1 = objectMapper.writeValueAsString(shipRequest1);

        mockMvc.perform(post("/api/ships")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json1))
                .andExpect(status().isCreated());

        ShipRequest shipRequest2 = new ShipRequest("HAND", null);
        String json2 = objectMapper.writeValueAsString(shipRequest2);

        mockMvc.perform(post("/api/ships")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json2))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/ships")
                        .param("engineType", "PEDAL"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].engineType").value("PEDAL"))
                .andExpect(jsonPath("$[1].engineType").value("PEDAL"));

    }

    @Test
    public void testSellShips() throws Exception {
        ShipRequest shipRequest = new ShipRequest("PEDAL", 13);
        String json = objectMapper.writeValueAsString(shipRequest);

        mockMvc.perform(post("/api/ships")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        ShipRequest shipRequest1 = new ShipRequest("PEDAL", 9);
        String json1 = objectMapper.writeValueAsString(shipRequest1);

        mockMvc.perform(post("/api/ships")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json1))
                .andExpect(status().isCreated());

        CustomerRequest customerRequest = new CustomerRequest("Artem", 15, 12, 300);
        String json3 = objectMapper.writeValueAsString(customerRequest);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json3))
                .andExpect(status().isCreated());

        CustomerRequest customerRequest1 = new CustomerRequest("Vanya", 10, 13, 200);
        String json4 = objectMapper.writeValueAsString(customerRequest1);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json4))
                .andExpect(status().isCreated());



        mockMvc.perform(post("/api/ships/sell"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/customers/Artem"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ship").isNotEmpty());

        mockMvc.perform(get("/api/customers/Vanya"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ship").isNotEmpty());
    }

    @Test
    public void updateAndDeleteShips() throws Exception {
        ShipRequest shipRequest = new ShipRequest("PEDAL", 13);
        String json = objectMapper.writeValueAsString(shipRequest);

        mockMvc.perform(post("/api/ships")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/ships"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].engineType").value("PEDAL"));


        int vin = facade.getShips().get(0).getVin();
        CarRequest newShipRequest = new CarRequest("HAND", 13);
        String newJson = objectMapper.writeValueAsString(newShipRequest);

        mockMvc.perform(put("/api/ships/" + vin)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newJson))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/ships"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].engineType").value("HAND"));

        mockMvc.perform(delete("/api/ships/" + vin))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/ships"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}
