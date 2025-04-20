package zoo.domains.entities;

import lombok.RequiredArgsConstructor;
import zoo.domains.valueObjects.AnimalFood;
import zoo.domains.valueObjects.enums.AnimalGender;
import zoo.domains.valueObjects.enums.AnimalStatus;
import zoo.domains.valueObjects.enums.AnimalTypes;

import java.util.Date;

@RequiredArgsConstructor
public class Animal {
    private final AnimalTypes type;
    private final String name;
    private final Date birthDate;
    private final AnimalGender gender;

    private Enclosure enclosure;
    private AnimalFood favoriteFood;
    private AnimalStatus status;

    public void Feed(AnimalFood favoriteFood) {}

    public void Treat(){
        this.status = AnimalStatus.HEALTHY;
    }

    public void MoveToEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    public void MoveFromEnclosure(){
        this.enclosure = null;
    }
}
