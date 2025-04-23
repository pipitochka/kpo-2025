package zoo.infrastructure.dto.converters;

import zoo.domains.entities.FeedingSchedule;
import zoo.infrastructure.dto.FeedingSchedulesDTO;
import zoo.infrastructure.dto.requests.CreateFeedingScheduleRequest;

public class FeedingSchedulesConverter {

    public static FeedingSchedulesDTO toDTO(FeedingSchedule feedingSchedule) {
        return new FeedingSchedulesDTO(feedingSchedule.getId(), feedingSchedule.getAnimalId(),
                feedingSchedule.getFood(), feedingSchedule.getDate());
    }

    public static FeedingSchedule toEntity(CreateFeedingScheduleRequest createFeedingScheduleRequest) {
        return new FeedingSchedule(createFeedingScheduleRequest.getAnimalId(), createFeedingScheduleRequest.getFood(),
                createFeedingScheduleRequest.getDate());
    }
}
