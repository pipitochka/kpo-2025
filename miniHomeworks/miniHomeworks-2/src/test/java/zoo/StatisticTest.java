package zoo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import zoo.application.interfaces.AnimalRepository;

/**
 * class of statistic tests.
 */
@SpringBootTest(classes = KpoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StatisticTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AnimalRepository animalRepository;

    @BeforeEach
    void setUp() {
        animalRepository.clear();
    }

    @Test
    public void testStatistic() throws Exception {
        String createAnimal1 = """
                {
                     "type": "WOLF",
                     "name": "Leo",
                     "birthDate": "2020-05-10",
                     "gender": "MALE",
                     "favoriteFood": "MEAT",
                     "status": "HEALTHY"
                }              
                """;
        String createAnimal2 = """
                {
                     "type": "MONKEY",
                     "name": "Masha",
                     "birthDate": "2020-10-10",
                     "gender": "FEMALE",
                     "favoriteFood": "RICE",
                     "status": "HEALTHY"
                }              
                """;
        String createAnimal3 = """
                {
                     "type": "MONKEY",
                     "name": "Vanya",
                     "birthDate": "2020-10-10",
                     "gender": "MALE",
                     "favoriteFood": "RICE",
                     "status": "HEALTHY"
                }              
                """;

        mockMvc.perform(post("/animals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createAnimal1));

        mockMvc.perform(post("/animals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createAnimal2));

        mockMvc.perform(post("/animals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createAnimal3));

        mockMvc.perform(get("/statistic/count_Male"))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));

        mockMvc.perform(get("/statistic/count_Female"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

        mockMvc.perform(get("/statistic/count_by_type/WOLF"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

        mockMvc.perform(get("/statistic/count_by_type/MONKEY"))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }
}
