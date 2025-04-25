package zoo.domains.events;

import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import zoo.domains.valueobjects.enums.AnimalFood;

/**
 * class of feeding time event.
 */
@Getter
@RequiredArgsConstructor
public class FeedingTimeEvent extends DomainEvent {
    private final UUID animalId;
    private final Date feedingTime;
    private final AnimalFood food;
}
