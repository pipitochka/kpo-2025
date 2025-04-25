package zoo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
 * class of movement tests.
 */
@SpringBootTest(classes = KpoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MovementTest {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private EnclosureRepository enclosureRepository;

    @Autowired
    private MockMvc mockMvc;

    void createAnimal() throws Exception {
        String create1 = """
                {
                     "type": "WOLF",
                     "name": "Leo",
                     "birthDate": "2020-05-10",
                     "gender": "MALE",
                     "favoriteFood": "MEAT",
                     "status": "HEALTHY"
                }              
                """;
        String create2 = """
                {
                     "type": "MONKEY",
                     "name": "Masha",
                     "birthDate": "2020-10-10",
                     "gender": "FEMALE",
                     "favoriteFood": "RICE",
                     "status": "HEALTHY"
                }              
                """;
        mockMvc.perform(post("/animals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(create1));

        mockMvc.perform(post("/animals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(create2));
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

    @BeforeEach
    void setUp() throws Exception {
        animalRepository.clear();
        enclosureRepository.clear();
    }

    @Test
    @Order(1)
    public void addEnclosureTest() throws Exception {
        String createAnimalRequest = """
                {
                     "type": "WOLF",
                     "name": "Leo",
                     "birthDate": "2020-05-10",
                     "gender": "MALE",
                     "favoriteFood": "MEAT",
                     "status": "HEALTHY"
                }              
                """;
        mockMvc.perform(post("/animals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createAnimalRequest));

        String enclosureId = null;

        mockMvc.perform(get("/animals/Leo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Leo"))
                .andExpect(jsonPath("$.enclosureId").value(enclosureId));


        String createEnclosureRequest = """
                {
                    "animalType": "WOLF",
                    "maxSize": 10
                }              
                """;

        mockMvc.perform(post("/enclosures")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createEnclosureRequest));

        enclosureId = enclosureRepository.getEnclosures().getFirst().getId().toString();

        String moveAnimalRequest = String.format("""
            {
                "name": "Leo",
                "enclosureId": "%s"
            }
            """, enclosureId);

        mockMvc.perform(post("/movement/animals/move")
                .contentType(MediaType.APPLICATION_JSON)
                .content(moveAnimalRequest));

        mockMvc.perform(get("/animals/Leo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Leo"))
                .andExpect(jsonPath("$.enclosureId").value(enclosureId));


    }

    @Test
    @Order(2)
    public void complicatedMovementTest() throws Exception {
        String createAnimal1Request = """
                {
                     "type": "WOLF",
                     "name": "Leo",
                     "birthDate": "2020-05-10",
                     "gender": "MALE",
                     "favoriteFood": "MEAT",
                     "status": "HEALTHY"
                }              
                """;
        mockMvc.perform(post("/animals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createAnimal1Request));

        String createAnimal2Request = """
                {
                     "type": "WOLF",
                     "name": "Brian",
                     "birthDate": "2020-05-10",
                     "gender": "MALE",
                     "favoriteFood": "MEAT",
                     "status": "HEALTHY"
                }              
                """;
        mockMvc.perform(post("/animals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createAnimal2Request));

        String createEnclosure1Request = """
                {
                    "animalType": "WOLF",
                    "maxSize": 10
                }              
                """;

        mockMvc.perform(post("/enclosures")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createEnclosure1Request));

        String enclosure1Id = enclosureRepository.getEnclosures().getFirst().getId().toString();

        String createEnclosure2Request = """
                {
                    "animalType": "WOLF",
                    "maxSize": 5
                }              
                """;

        mockMvc.perform(post("/enclosures")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createEnclosure2Request));

        String enclosure2Id = enclosureRepository.getEnclosures().get(1).getId().toString();
        if (enclosure2Id == enclosure1Id) {
            enclosure2Id = enclosureRepository.getEnclosures().get(0).getId().toString();
        }

        String moveAnimal1Request = String.format("""
            {
                "name": "Leo",
                "enclosureId": "%s"
            }
            """, enclosure1Id);

        mockMvc.perform(post("/movement/animals/move")
                .contentType(MediaType.APPLICATION_JSON)
                .content(moveAnimal1Request));

        String moveAnimal2Request = String.format("""
            {
                "name": "Brian",
                "enclosureId": "%s"
            }
            """, enclosure2Id);

        mockMvc.perform(post("/movement/animals/move")
                .contentType(MediaType.APPLICATION_JSON)
                .content(moveAnimal2Request));

        mockMvc.perform(get("/enclosures/" + enclosure1Id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maxSize").value("10"))
                .andExpect(jsonPath("$.currentSize").value(1));

        mockMvc.perform(get("/enclosures/" + enclosure2Id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maxSize").value("5"))
                .andExpect(jsonPath("$.currentSize").value(1));

        String moveAnimal3Request = String.format("""
            {
                "name": "Brian",
                "enclosureId": "%s"
            }
            """, enclosure1Id);

        mockMvc.perform(post("/movement/animals/move")
                .contentType(MediaType.APPLICATION_JSON)
                .content(moveAnimal3Request));

        mockMvc.perform(get("/enclosures/" + enclosure1Id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maxSize").value("10"))
                .andExpect(jsonPath("$.currentSize").value(2));

        mockMvc.perform(get("/enclosures/" + enclosure2Id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maxSize").value("5"))
                .andExpect(jsonPath("$.currentSize").value(0));
    }

}
