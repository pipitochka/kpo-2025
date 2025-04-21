package zoo.domains.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import zoo.domains.entities.Animal;
import zoo.domains.entities.Enclosure;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class AnimalMovedEvent extends DomainEvent {
    private final UUID animalId;
    private final UUID fromId;
    private final UUID toId;
}
