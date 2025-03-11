package hse.file.classes;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.domains.facade.HseFacade;
import org.springframework.stereotype.Component;

@Component
public class JsonFileImporter extends FileImporter<HseFacade> {

    @Override
    protected HseFacade parseData(String content) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(content, HseFacade.class);
        } catch (Exception e) {
            System.out.println("Error parsing data: " + e.getMessage());
            return null;
        }
    }
}
