package zoo.application.interfaces;

import lombok.RequiredArgsConstructor;
import zoo.domains.entities.FeedingSchedule;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FeedingScheduleRepository {

    Optional<FeedingSchedule> findById(UUID id);

    void add(FeedingSchedule feedingSchedule);

    void remove(FeedingSchedule feedingSchedule);

    List<FeedingSchedule> getSchedules();

    void clear();
}
