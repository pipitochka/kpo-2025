package zoo.application.interfaces;

import lombok.RequiredArgsConstructor;
import zoo.domains.entities.FeedingSchedule;

import java.util.List;

public interface FeedingScheduleRepository {

    void add(FeedingSchedule feedingSchedule);

    void remove(FeedingSchedule feedingSchedule);

    List<FeedingSchedule> getSchedules();
}
