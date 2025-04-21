package zoo.domains.entities;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import zoo.domains.valueObjects.AnimalFood;

import java.util.Date;

@ToString
@Slf4j
@RequiredArgsConstructor
public class FeedingSchedule {
    private final Animal animal;
    private final AnimalFood food;

    @Setter
    private Date date;

    public void makeComplete(){
        log.info("feeding animal {} by food {} complete", animal.getAnimalId(), food);
    }
}
