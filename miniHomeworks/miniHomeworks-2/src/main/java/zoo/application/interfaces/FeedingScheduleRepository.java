package zoo.application.interfaces;

import lombok.RequiredArgsConstructor;
import zoo.domains.entities.FeedingSchedule;

import java.util.List;
import java.util.UUID;

public interface FeedingScheduleRepository {
    void add(FeedingSchedule feedingSchedule);

    void remove(FeedingSchedule feedingSchedule);

    List<FeedingSchedule> getSchedules();
}
