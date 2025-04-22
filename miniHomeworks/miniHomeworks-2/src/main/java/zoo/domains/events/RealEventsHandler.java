package zoo.domains.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RealEventsHandler implements EventHandler {
    @Override
    public void handle(Object event) {
        log.info(event.toString());
    }
}
