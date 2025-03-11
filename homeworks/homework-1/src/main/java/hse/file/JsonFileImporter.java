package hse.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.domains.facade.HseFacade;

public class JsonFileImporter extends Reader<HseFacade>{
    @Override
    protected HseFacade parseData(String content) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(content, HseFacade.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
