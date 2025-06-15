package hse.kpo.domain.realizations.objects;

import hse.kpo.domain.interfaces.objects.IAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@RequiredArgsConstructor
public class Account implements IAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private double balance;

}
