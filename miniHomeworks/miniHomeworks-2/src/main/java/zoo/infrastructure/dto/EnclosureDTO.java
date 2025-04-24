package zoo.infrastructure.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import zoo.domains.valueObjects.enums.AnimalTypes;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class EnclosureDTO {
    private @NonNull UUID id;
    private @NonNull AnimalTypes animalType;
    @Setter
    private int maxSize;
    @Setter
    private int currentSize;
}
