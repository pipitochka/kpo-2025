package zoo.infrastructure.dto.converters;

import zoo.domains.entities.Enclosure;
import zoo.infrastructure.dto.EnclosureDTO;
import zoo.infrastructure.dto.requests.CreateEnclosureRequest;

public class EnclosureConverter {

    public static EnclosureDTO toDTO(Enclosure enclosure) {
        var enclosureDTO = new EnclosureDTO(enclosure.getId(), enclosure.getAnimalType());
        enclosureDTO.setMaxSize(enclosure.getMaxSize());
        enclosureDTO.setCurentSize(enclosure.getCurrentSize());
        return enclosureDTO;
    }

    public static Enclosure toEntity(CreateEnclosureRequest createEnclosureRequest) {
        return new Enclosure(createEnclosureRequest.getAnimalType(), createEnclosureRequest.getMaxSize());
    }
}
