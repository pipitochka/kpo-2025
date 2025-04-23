package zoo.domains.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import zoo.domains.valueObjects.enums.AnimalFood;

import java.util.Date;
import java.util.UUID;

@Getter
@ToString
@Slf4j
@RequiredArgsConstructor
public class FeedingSchedule {
    private final UUID id = UUID.randomUUID();

    private final UUID animalId;

    private final AnimalFood food;

    private final Date date;

    @Setter
    private boolean isDone = false;

    public void makeComplete(){
        this.isDone = true;
        log.info("feeding animal {} by food {} complete", animalId, food);
    }
}
