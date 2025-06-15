package hse.kpo.domain.realizations.objects;

import hse.kpo.domain.enums.OperationResult;
import hse.kpo.domain.enums.OperationType;
import hse.kpo.domain.interfaces.objects.IOperation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "operations")
@Getter
@Setter
@NoArgsConstructor
public class Operation implements IOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String externalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OperationType operationType;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private OperationResult operationResult;

    @Override
    public Long getAccountId() {
        return account.getId();
    }
}
