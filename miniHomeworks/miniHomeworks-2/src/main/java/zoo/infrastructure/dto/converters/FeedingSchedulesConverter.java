package zoo.infrastructure.dto.converters;

import java.util.UUID;
import zoo.domains.entities.FeedingSchedule;
import zoo.infrastructure.dto.FeedingSchedulesDto;
import zoo.infrastructure.dto.requests.CreateFeedingScheduleRequest;

/**
 * class of feeding schedule converter(to DTO and to feeding schedule).
 */
public class FeedingSchedulesConverter {

    /**
     * function to make dto object from feeding schedule.
     *
     * @param feedingSchedule real object.
     * @return dto feeding schedule.
     */
    public static FeedingSchedulesDto toDto(FeedingSchedule feedingSchedule) {
        return new FeedingSchedulesDto(feedingSchedule.getId(), feedingSchedule.getAnimalId(),
                feedingSchedule.getFood(), feedingSchedule.getDate(), feedingSchedule.isDone());
    }

    /**
     * function to make feeding schedule from requests.
     *
     * @param createFeedingScheduleRequest requests with description.
     * @param animalId id of animal.
     * @return feeding schedule object
     */
    public static FeedingSchedule toEntity(CreateFeedingScheduleRequest createFeedingScheduleRequest, UUID animalId) {
        var feedingSchedule = new FeedingSchedule(animalId, createFeedingScheduleRequest.getFood(),
                createFeedingScheduleRequest.getDate());
        feedingSchedule.setDone(false);
        return feedingSchedule;
    }
}
