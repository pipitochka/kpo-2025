package zoo.infrastructure.dto;

import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import zoo.domains.valueobjects.enums.AnimalFood;
import zoo.domains.valueobjects.enums.AnimalGender;
import zoo.domains.valueobjects.enums.AnimalStatus;
import zoo.domains.valueobjects.enums.AnimalTypes;

/**
 * class of animal DTO (to send and receive information).
 */
@Getter
@RequiredArgsConstructor
public class AnimalDto {
    private @NonNull String name;
    private @NonNull AnimalTypes type;
    private @NonNull Date birthDate;
    private @NonNull AnimalGender gender;
    private @NonNull UUID animalId;
    private final UUID enclosureId;
    private @NonNull AnimalFood food;
    private @NonNull AnimalStatus animalStatus;
}
