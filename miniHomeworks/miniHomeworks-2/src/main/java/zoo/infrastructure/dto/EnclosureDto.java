package zoo.infrastructure.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import zoo.domains.valueobjects.enums.AnimalTypes;


/**
 * class of enclosure DTO (to send and receive information).
 */
@Getter
@RequiredArgsConstructor
public class EnclosureDto {
    private @NonNull UUID id;
    private @NonNull AnimalTypes animalType;
    @Setter
    private int maxSize;
    @Setter
    private int currentSize;
}
