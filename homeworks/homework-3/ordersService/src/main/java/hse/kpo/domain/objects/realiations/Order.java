package hse.kpo.domain.objects.realiations;

import hse.kpo.domain.enums.IncomeStatus;
import hse.kpo.domain.enums.OrderType;
import hse.kpo.domain.objects.interfaces.IOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order implements IOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private IncomeStatus status;

    @Column(nullable = false)
    private OrderType orderType;
}
