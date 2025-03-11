package hse.file.classes;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.domains.facade.HseFacade;
import hse.file.interfaces.Visitor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class JsonFileExporter implements Visitor {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void visit(HseFacade facade, String fileName) {
        loadFromFile(facade, fileName);

    }

    private void loadFromFile(HseFacade object, String fileName) {
        try {
            objectMapper.writeValue(new File(fileName), object);
            System.out.println("Объект сохранен в " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении " + fileName + ": " + e.getMessage());
        }
    }
}
