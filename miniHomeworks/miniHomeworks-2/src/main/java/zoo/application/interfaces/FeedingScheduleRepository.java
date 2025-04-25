package zoo.application.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import zoo.domains.entities.FeedingSchedule;

/**
 * interface of feeding schedule repository.
 */
public interface FeedingScheduleRepository {

    Optional<FeedingSchedule> findById(UUID id);

    void add(FeedingSchedule feedingSchedule);

    void remove(FeedingSchedule feedingSchedule);

    List<FeedingSchedule> getSchedules();

    void clear();
}
