package hse.domains;

import hse.emums.OperationType;
import hse.interfaces.Category;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@ToString
@RequiredArgsConstructor
public class HseCategory implements Category {

    @Getter
    private final int id;

    @Getter
    private final OperationType operationType;

    @Getter
    private final String name;
}
