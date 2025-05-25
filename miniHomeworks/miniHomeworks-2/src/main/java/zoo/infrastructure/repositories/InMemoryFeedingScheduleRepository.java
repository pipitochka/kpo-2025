package zoo.infrastructure.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;
import zoo.application.interfaces.FeedingScheduleRepository;
import zoo.domains.entities.FeedingSchedule;


/**
 * realizations of feeding schedule repository in memory.
 */
@Component
public class InMemoryFeedingScheduleRepository implements FeedingScheduleRepository {

    private final List<FeedingSchedule> feedingSchedules = new ArrayList<>();

    @Override
    public Optional<FeedingSchedule> findById(UUID id) {
        return feedingSchedules.stream()
                .filter(feedingSchedule -> feedingSchedule.getId().equals(id))
                .findFirst();
    }

    @Override
    public void add(FeedingSchedule feedingSchedule) {
        feedingSchedules.add(feedingSchedule);
    }

    @Override
    public void remove(FeedingSchedule feedingSchedule) {
        feedingSchedules.remove(feedingSchedule);
    }

    @Override
    public List<FeedingSchedule> getSchedules() {
        return feedingSchedules;
    }

    @Override
    public void clear() {
        feedingSchedules.clear();
    }
}
