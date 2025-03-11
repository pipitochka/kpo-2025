package hse.domains.object;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hse.emums.OperationType;
import hse.interfaces.object.Category;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonDeserialize(as = HseCategory.class)
@ToString
public class HseCategory implements Category {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private OperationType operationType;

    @Getter
    @Setter
    private String name;

    public HseCategory(int id, OperationType operationType, String name) {
        this.id = id;
        this.operationType = operationType;
        this.name = name;
    }

    public HseCategory() {
    }
}
