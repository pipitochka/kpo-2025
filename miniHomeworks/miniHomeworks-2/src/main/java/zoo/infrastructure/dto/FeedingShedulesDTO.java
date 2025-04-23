package zoo.infrastructure.dto;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import zoo.domains.valueObjects.enums.AnimalFood;

import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
public class FeedingShedulesDTO {
    private @NonNull UUID animalId;
    private @NonNull AnimalFood animalFood;
    private @NonNull Date date;
}
