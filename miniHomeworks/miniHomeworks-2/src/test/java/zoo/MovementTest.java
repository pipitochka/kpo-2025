package zoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(classes = KpoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MovementTest {

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
}
