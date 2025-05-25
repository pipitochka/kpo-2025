package hse.kpo.repositories;

import hse.kpo.domains.objects.Car;
import hse.kpo.domains.objects.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * interface of customer repository.
 */
public interface CustomerRepositoryInterface extends JpaRepository<Customer, Integer> {
    @Query("""
        SELECT c 
        FROM Customer c
        """)
    List<Customer> getCustomers();
}
