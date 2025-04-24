package zoo.infrastructure.dto.converters;

import zoo.domains.entities.FeedingSchedule;
import zoo.infrastructure.dto.FeedingSchedulesDTO;
import zoo.infrastructure.dto.requests.CreateFeedingScheduleRequest;

import java.util.UUID;

public class FeedingSchedulesConverter {

    public static FeedingSchedulesDTO toDTO(FeedingSchedule feedingSchedule) {
        return new FeedingSchedulesDTO(feedingSchedule.getId(), feedingSchedule.getAnimalId(),
                feedingSchedule.getFood(), feedingSchedule.getDate(), feedingSchedule.isDone());
    }

    public static FeedingSchedule toEntity(CreateFeedingScheduleRequest createFeedingScheduleRequest, UUID animalId) {
        var feedingSchedule = new FeedingSchedule(animalId, createFeedingScheduleRequest.getFood(),
                createFeedingScheduleRequest.getDate());
        feedingSchedule.setDone(false);
        return feedingSchedule;
    }
}
