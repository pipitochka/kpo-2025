package hse.domains.object;

import hse.interfaces.object.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class HseAccount implements Account {

    @Getter
    private final int id;

    @Getter
    @Setter
    private double balance = 0;

    @Getter
    private final String name;
}
