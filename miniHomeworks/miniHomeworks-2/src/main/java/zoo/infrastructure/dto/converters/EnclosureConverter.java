package zoo.infrastructure.dto.converters;

import zoo.domains.entities.Enclosure;
import zoo.infrastructure.dto.EnclosureDto;
import zoo.infrastructure.dto.requests.CreateEnclosureRequest;

/**
 * class of enclosure converter(to DTO and to Enclosure).
 */
public class EnclosureConverter {

    /**
     * function to move enclosure into dto.
     *
     * @param enclosure object.
     * @return dto object of enclosure.
     */
    public static EnclosureDto toDto(Enclosure enclosure) {
        var enclosureDto = new EnclosureDto(enclosure.getId(), enclosure.getAnimalType());
        enclosureDto.setMaxSize(enclosure.getMaxSize());
        enclosureDto.setCurrentSize(enclosure.getCurrentSize());
        return enclosureDto;
    }

    /**
     * function to make enclosure from requests.
     *
     * @param createEnclosureRequest requests with description.
     * @return enclosure object.
     */
    public static Enclosure toEntity(CreateEnclosureRequest createEnclosureRequest) {
        return new Enclosure(createEnclosureRequest.getAnimalType(), createEnclosureRequest.getMaxSize());
    }
}
