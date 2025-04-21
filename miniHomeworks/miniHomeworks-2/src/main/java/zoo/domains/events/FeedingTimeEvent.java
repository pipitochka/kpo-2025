package zoo.domains.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import zoo.domains.valueObjects.AnimalFood;

import java.util.Date;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class FeedingTimeEvent extends DomainEvent {
    private final UUID animalId;
    private final Date feedingTime;
    private final AnimalFood food;
}
