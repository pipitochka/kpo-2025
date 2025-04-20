package zoo.domains.entities;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import zoo.domains.valueObjects.AnimalFood;

import java.util.Date;

@RequiredArgsConstructor
public class FeedingSchedule {
    private final Animal animal;
    private final AnimalFood food;

    @Setter
    private Date date;

    public void makeComplete(){

    }
}
