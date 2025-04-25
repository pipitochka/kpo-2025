package zoo.application.servies;

import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zoo.application.interfaces.AnimalRepository;
import zoo.application.interfaces.FeedingScheduleRepository;
import zoo.domains.entities.FeedingSchedule;
import zoo.domains.events.EventHandler;
import zoo.domains.events.FeedingTimeEvent;

/**
 * class of feeding service (to feed all animals).
 */
@Component
@RequiredArgsConstructor
public class FeedingOrganizationService {
    private final FeedingScheduleRepository feedingScheduleRepository;
    private final AnimalRepository animalRepository;
    private final EventHandler eventHandler;

    /**
     * function to feed all animals in animal repository.
     */
    public void feed() {
        for (FeedingSchedule feedingSchedule : feedingScheduleRepository.getSchedules()) {
            if (feedingSchedule.getDate().before(new Date())) {
                if (feedingSchedule.isDone() == false) {
                    animalRepository.getAnimalById(feedingSchedule.getAnimalId()).orElseThrow(
                                    () -> new RuntimeException("Animal not found")
                            )
                            .feed(feedingSchedule.getFood());
                    feedingSchedule.setDone(true);
                    eventHandler.handle(new FeedingTimeEvent(
                            feedingSchedule.getAnimalId(),
                            feedingSchedule.getDate(),
                            feedingSchedule.getFood()
                    ));
                }
            }
        }
    }
}
