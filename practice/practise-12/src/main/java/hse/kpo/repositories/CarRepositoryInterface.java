package hse.kpo.repositories;

import hse.kpo.domains.objects.Car;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * interface of car repository.
 */
public interface CarRepositoryInterface extends JpaRepository<Car, Integer> {

    @Query("""
        SELECT c 
        FROM Car c 
        JOIN c.engine e 
        WHERE e.type = :engineType 
        AND c.vin > :minVin
        """)
    List<Car> findCarsByEngineTypeAndVinGreaterThan(
            @Param("engineType") String engineType,
            @Param("minVin") Integer minVin
    );
}
