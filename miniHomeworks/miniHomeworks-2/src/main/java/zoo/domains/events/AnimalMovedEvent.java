package zoo.domains.events;

import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * class of animal move event.
 */
@Getter
@RequiredArgsConstructor
public class AnimalMovedEvent extends DomainEvent {
    private final UUID animalId;
    private final UUID fromId;
    private final UUID toId;
}
