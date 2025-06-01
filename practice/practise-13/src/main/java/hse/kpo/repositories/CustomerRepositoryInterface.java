package hse.kpo.repositories;

import hse.kpo.domains.objects.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * interface of customer repository.
 */
public interface CustomerRepositoryInterface extends JpaRepository<Customer, Integer> {

    void deleteByName(String name);

    Optional<Customer> findByName(String name);
}
