package zoo.domains.entities;

import jdk.jfr.Frequency;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import zoo.domains.valueObjects.AnimalFood;
import zoo.domains.valueObjects.enums.AnimalGender;
import zoo.domains.valueObjects.enums.AnimalStatus;
import zoo.domains.valueObjects.enums.AnimalTypes;

import java.util.Date;
import java.util.UUID;

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

    public void Feed(AnimalFood favoriteFood) {
        log.info("feed animal {}", animalId);
    }

    public void Treat(){
        log.info("treat animal {}", animalId);
        this.status = AnimalStatus.HEALTHY;
    }

    public void MoveToEnclosure(Enclosure enclosure) {
        log.info("Move to enclosure: animal {}, enclosure {}", animalId, enclosure.getId());
        this.enclosure = enclosure;
    }

    public void MoveFromEnclosure(){
        log.info("Move from enclosure animal {}", animalId);
        this.enclosure = null;
    }
}
