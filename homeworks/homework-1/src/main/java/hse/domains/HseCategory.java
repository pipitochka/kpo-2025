package hse.domains;

import hse.emums.OperationType;
import hse.interfaces.Category;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HseCategory implements Category {

    @Getter
    private String name;

    @Getter
    private int id;

    @Getter
    private OperationType operationType;
}
