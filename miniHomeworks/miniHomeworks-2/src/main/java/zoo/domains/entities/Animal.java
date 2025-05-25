package zoo.domains.entities;

import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import zoo.domains.valueobjects.enums.AnimalFood;
import zoo.domains.valueobjects.enums.AnimalGender;
import zoo.domains.valueobjects.enums.AnimalStatus;
import zoo.domains.valueobjects.enums.AnimalTypes;

/**
 * class of animal.
 */
@ToString
@Slf4j
@RequiredArgsConstructor
public class Animal {
    @Getter
    private final AnimalTypes type;
    @Getter
    private final String name;
    @Getter
    private final Date birthDate;
    @Getter
    private final AnimalGender gender;
    @Getter
    private final UUID animalId = UUID.randomUUID();

    @Getter
    @Setter
    private Enclosure enclosure;
    @Getter
    @Setter
    private AnimalFood favoriteFood;
    @Getter
    @Setter
    private AnimalStatus status;

    public void feed(AnimalFood favoriteFood) {
        log.info("feed animal {}", animalId);
    }

    public void treat() {
        log.info("treat animal {}", animalId);
        this.status = AnimalStatus.HEALTHY;
    }

    public void moveToEnclosure(Enclosure enclosure) {
        log.info("Move to enclosure: animal {}, enclosure {}", animalId, enclosure.getId());
        this.enclosure = enclosure;
    }

    public void moveFromEnclosure() {
        log.info("Move from enclosure animal {}", animalId);
        this.enclosure = null;
    }
}
