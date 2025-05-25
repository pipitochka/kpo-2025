package hse.domains.object;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hse.interfaces.object.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * realizations class of accounts.
 */
@JsonDeserialize(as = HseAccount.class)
@ToString
public class HseAccount implements Account {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private double balance = 0;

    @Getter
    @Setter
    private String name;

    public HseAccount() {
    }

    public HseAccount(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
