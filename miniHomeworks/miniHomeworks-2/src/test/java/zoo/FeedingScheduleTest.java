package zoo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import zoo.application.interfaces.AnimalRepository;
import zoo.application.interfaces.FeedingScheduleRepository;
import zoo.domains.entities.FeedingSchedule;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = KpoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FeedingScheduleTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private FeedingScheduleRepository feedingScheduleRepository;

    @BeforeEach
    void setUp() {
        animalRepository.clear();
        feedingScheduleRepository.clear();
    }

    void createAnimals() throws Exception {
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
    void createFeedingSchedule() throws Exception {
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
    }

    @Test
    @Order(1)
    void createFeedingScheduleTest() throws Exception {
        createAnimals();
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

        String firsId = animalRepository.getAnimalByName("Leo").get().getAnimalId().toString();
        String secondId = animalRepository.getAnimalByName("Masha").get().getAnimalId().toString();

        mockMvc.perform(post("/feedingSchedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(create1))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.animalId").value(firsId))
                .andExpect(jsonPath("$.animalFood").value("MEAT"))
                .andExpect(jsonPath("$.date", containsString("2025-04-24T10:00:00")));


        mockMvc.perform(post("/feedingSchedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(create2))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.animalId").value(secondId))
                .andExpect(jsonPath("$.animalFood").value("RICE"))
                .andExpect(jsonPath("$.date", containsString("2025-04-24T10:00:00")));
    }

    @Test
    @Order(2)
    void deleteFeedingSchedule() throws Exception {
        createAnimals();
        createFeedingSchedule();
        String firsId = animalRepository.getAnimalByName("Leo").get().getAnimalId().toString();
        String secondId = animalRepository.getAnimalByName("Masha").get().getAnimalId().toString();

        FeedingSchedule feedingSchedule = feedingScheduleRepository.getSchedules().stream().
                filter(schedule -> schedule.getAnimalId().toString().equals(firsId))
                .findFirst()
                .get();

        String id = feedingSchedule.getId().toString();

        mockMvc.perform(delete("/feedingSchedule/" + id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/feedingSchedule/feedingSchedules"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].animalId").value(secondId))
                .andExpect(jsonPath("$[0].animalFood").value("RICE"));
    }
}
