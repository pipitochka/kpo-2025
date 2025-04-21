package zoo.infrastructure.repositories;

import zoo.application.interfaces.FeedingScheduleRepository;
import zoo.domains.entities.FeedingSchedule;

import java.util.ArrayList;
import java.util.List;

public class InMemoryFeedingScheduleRepository implements FeedingScheduleRepository {
    private final List<FeedingSchedule> feedingSchedules = new ArrayList<>();

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
}
