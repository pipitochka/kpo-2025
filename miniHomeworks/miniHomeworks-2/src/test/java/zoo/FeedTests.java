package zoo;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


/**
 * class of feed tests.
 */
@SpringBootTest(classes = KpoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FeedTests {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void testFeed() throws Exception {
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
        mockMvc.perform(post("/animals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createAnimal1));

        mockMvc.perform(post("/animals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createAnimal2));

        String create1 = """
                {
                     "name": "Leo",
                     "food": "MEAT",
                     "date": "2025-04-24T10:00:00Z"
                }              
                """;
        String create2 = """
                {
                     "name": "Masha",
                     "food": "RICE",
                     "date": "2025-04-24T10:00:00Z"
                }              
                """;
        mockMvc.perform(post("/feedingSchedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(create1))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/feedingSchedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(create2))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/feedingSchedule/feedingSchedules"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].isDone", everyItem(is(false))));

        mockMvc.perform(post("/feeding/feed"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/feedingSchedule/feedingSchedules"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].isDone", everyItem(is(true))));
    }
}
