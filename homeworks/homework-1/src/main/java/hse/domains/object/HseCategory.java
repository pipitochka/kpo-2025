package hse.domains.object;

import hse.emums.OperationType;
import hse.interfaces.object.Category;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

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
