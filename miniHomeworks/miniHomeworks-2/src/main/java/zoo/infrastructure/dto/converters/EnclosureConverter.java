package zoo.infrastructure.dto.converters;

import zoo.domains.entities.Enclosure;
import zoo.infrastructure.dto.EnclosureDTO;

public class EnclosureConverter {

    public static EnclosureDTO toDto(Enclosure enclosure) {
        var enclosureDTO = new EnclosureDTO(enclosure.getId(), enclosure.getAnimalType());
        enclosureDTO.setMaxSize(enclosure.getMaxSize());
        enclosureDTO.setCurentSize(enclosure.getCurrentSize());
        return enclosureDTO;
    }
}
