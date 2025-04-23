package zoo.application.servies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zoo.application.interfaces.AnimalRepository;
import zoo.application.interfaces.FeedingScheduleRepository;
import zoo.domains.entities.FeedingSchedule;
import zoo.domains.events.EventHandler;
import zoo.domains.events.FeedingTimeEvent;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class FeedingOrganizationService {
    private final FeedingScheduleRepository feedingScheduleRepository;
    private final AnimalRepository animalRepository;
    private final EventHandler eventHandler;

    public void feed(){
        for (FeedingSchedule feedingSchedule : feedingScheduleRepository.getSchedules()){
            if (feedingSchedule.getDate().before(new Date())){
                if (feedingSchedule.isDone() == false){
                    animalRepository.getAnimalById(feedingSchedule.getAnimalId()).orElseThrow(
                                    ()->new RuntimeException("Animal not found")
                            )
                            .Feed(feedingSchedule.getFood());
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
