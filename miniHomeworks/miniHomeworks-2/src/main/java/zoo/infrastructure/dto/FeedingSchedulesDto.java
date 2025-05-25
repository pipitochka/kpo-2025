package zoo.infrastructure.dto;

import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import zoo.domains.valueobjects.enums.AnimalFood;


/**
 * class of feeding schedule DTO (to send and receive information).
 */
@Getter
@RequiredArgsConstructor
public class FeedingSchedulesDto {
    private @NonNull UUID id;
    private @NonNull UUID animalId;
    private @NonNull AnimalFood animalFood;
    private @NonNull Date date;
    private final boolean isDone;
}
