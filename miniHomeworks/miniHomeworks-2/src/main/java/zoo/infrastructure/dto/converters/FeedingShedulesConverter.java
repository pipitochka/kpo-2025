package zoo.infrastructure.dto.converters;

import zoo.domains.entities.FeedingSchedule;
import zoo.infrastructure.dto.FeedingShedulesDTO;

public class FeedingShedulesConverter {

    public static FeedingShedulesDTO toDto(FeedingSchedule feedingSchedule) {
        return new FeedingShedulesDTO(feedingSchedule.getAnimal().getAnimalId(),
                feedingSchedule.getFood(), feedingSchedule.getDate());
    }
}
