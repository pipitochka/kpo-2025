package zoo.infrastructure.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import zoo.domains.valueObjects.AnimalFood;
import zoo.domains.valueObjects.enums.AnimalGender;
import zoo.domains.valueObjects.enums.AnimalStatus;
import zoo.domains.valueObjects.enums.AnimalTypes;

import java.util.Date;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class AnimalDTO {
    private @NonNull String name;
    private @NonNull AnimalTypes type;
    private @NonNull Date birthDate;
    private @NonNull AnimalGender gender;
    private @NonNull UUID animalId;
    private final UUID enclosureID;
    private @NonNull AnimalFood food;
    private @NonNull AnimalStatus animalStatus;
}
