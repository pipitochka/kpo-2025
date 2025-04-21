package zoo.infrastructure.dto.requests;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import zoo.domains.valueObjects.enums.AnimalTypes;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class EnclosureDTO {
    private @NonNull UUID id;
    private @NonNull AnimalTypes animalType;
    private int maxSize;
    private int curentSize;
}
