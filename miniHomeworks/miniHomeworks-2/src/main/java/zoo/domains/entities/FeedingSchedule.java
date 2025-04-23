package zoo.domains.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import zoo.domains.valueObjects.enums.AnimalFood;

import java.util.Date;

@ToString
@Slf4j
@RequiredArgsConstructor
public class FeedingSchedule {
    @Getter
    private final Animal animal;

    @Getter
    private final AnimalFood food;

    @Setter
    @Getter
    private Date date;

    @Setter
    @Getter
    private boolean isDone = false;

    public void makeComplete(){
        this.isDone = true;
        log.info("feeding animal {} by food {} complete", animal.getAnimalId(), food);
    }
}
