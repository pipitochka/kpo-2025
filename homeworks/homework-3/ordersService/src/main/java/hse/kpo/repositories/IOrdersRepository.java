package hse.kpo.repositories;

import hse.kpo.domain.objects.realiations.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdersRepository extends JpaRepository<Order, Long> {
}
