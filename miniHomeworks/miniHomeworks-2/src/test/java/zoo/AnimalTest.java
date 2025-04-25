package zoo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

/**
 * class to test animals.
 */
@SpringBootTest(classes = KpoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AnimalTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AnimalRepository animalRepository;

    @BeforeEach
    void setUp() {
        animalRepository.clear();
    }

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


    @Test
    @Order(1)
    void createAnimalTest() throws Exception {
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
                        .content(create1))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type").value("WOLF"))
                .andExpect(jsonPath("$.name").value("Leo"))
                .andExpect(jsonPath("$.birthDate").value(org.hamcrest.Matchers.startsWith("2020-05-10")))
                .andExpect(jsonPath("$.gender").value("MALE"))
                .andExpect(jsonPath("$.food").value("MEAT"))
                .andExpect(jsonPath("$.animalStatus").value("HEALTHY"));

        mockMvc.perform(post("/animals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(create2))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type").value("MONKEY"))
                .andExpect(jsonPath("$.name").value("Masha"))
                .andExpect(jsonPath("$.birthDate").value(org.hamcrest.Matchers.startsWith("2020-10-10")))
                .andExpect(jsonPath("$.gender").value("FEMALE"))
                .andExpect(jsonPath("$.food").value("RICE"))
                .andExpect(jsonPath("$.animalStatus").value("HEALTHY"));
    }


    @Test
    @Order(2)
    void getAnimalTest() throws Exception {
        createAnimal();
        mockMvc.perform(get("/animals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[*].name").value(org.hamcrest.Matchers.hasItems("Leo", "Masha")))
                .andExpect(jsonPath("$[*].type").value(org.hamcrest.Matchers.hasItems("WOLF", "MONKEY")));
    }

    @Test
    @Order(3)
    void deleteAnimalTest() throws Exception {
        createAnimal();

        assertThat(animalRepository.getAnimals().size()).isEqualTo(2);

        mockMvc.perform(delete("/animals/Leo"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/animals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Masha"))
                .andExpect(jsonPath("$[0].type").value("MONKEY"));

        assertThat(animalRepository.getAnimals().size()).isEqualTo(1);


    }
}
