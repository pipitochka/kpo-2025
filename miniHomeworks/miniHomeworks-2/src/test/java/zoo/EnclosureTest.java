package zoo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import zoo.application.interfaces.AnimalRepository;
import zoo.application.interfaces.EnclosureRepository;



/**
 * class to test enclosures.
 */
@SpringBootTest(classes = KpoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EnclosureTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private EnclosureRepository enclosureRepository;

    @BeforeEach
    void setUp() {
        animalRepository.clear();
        enclosureRepository.clear();
    }

    void createEnclosure() throws Exception {
        String create1 = """
                {
                    "animalType": "WOLF",
                    "maxSize": 5
                }              
                """;
        String create2 = """
                {
                    "animalType": "MONKEY",
                    "maxSize": 10
                }              
                """;

        mockMvc.perform(post("/enclosures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(create1));

        mockMvc.perform(post("/enclosures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(create2));
    }

    @Test
    @Order(1)
    void createEnclosureTest() throws Exception {
        String create1 = """
                {
                    "animalType": "WOLF",
                    "maxSize": 5
                }              
                """;
        String create2 = """
                {
                    "animalType": "MONKEY",
                    "maxSize": 10
                }              
                """;

        mockMvc.perform(post("/enclosures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(create1))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.animalType").value("WOLF"))
                .andExpect(jsonPath("$.maxSize").value(5))
                .andExpect(jsonPath("$.currentSize").value(0));

        mockMvc.perform(post("/enclosures")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(create2))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.animalType").value("MONKEY"))
                .andExpect(jsonPath("$.maxSize").value(10))
                .andExpect(jsonPath("$.currentSize").value(0));
    }

    @Test
    @Order(2)
    void getEnclosureTest() throws Exception {
        createEnclosure();
        mockMvc.perform(get("/enclosures"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[*].animalType").value(org.hamcrest.Matchers.hasItems("WOLF", "MONKEY")))
                .andExpect(jsonPath("$[*].maxSize").value(org.hamcrest.Matchers.hasItems(5, 10)));

    }

    @Test
    @Order(3)
    void deleteEnclosureTest() throws Exception {
        createEnclosure();

        assertThat(enclosureRepository.getEnclosures().size()).isEqualTo(2);

        String response = mockMvc.perform(get("/enclosures"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JSONArray jsonArray = new JSONArray(response);

        String wolfEnclosureId = null;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject enclosure = jsonArray.getJSONObject(i);
            if ("WOLF".equals(enclosure.getString("animalType"))) {
                wolfEnclosureId = enclosure.getString("id");
                break;
            }
        }


        mockMvc.perform(delete("/enclosures/" + wolfEnclosureId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/enclosures"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].animalType").value("MONKEY"));

        assertThat(enclosureRepository.getEnclosures().size()).isEqualTo(1);
    }

}
