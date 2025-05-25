package hse.kpo.repositories;

import hse.kpo.domains.objects.Car;
import hse.kpo.domains.objects.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShipRepositoryInterface extends JpaRepository<Ship, Integer> {
    @Query("""
        SELECT c 
        FROM Ship c 
        JOIN c.engine e 
        WHERE e.type = :engineType 
        AND c.vin > :minVin
    """)
    List<Ship> findShipByEngineTypeAndVinGreaterThan(
            @Param("engineType") String engineType,
            @Param("minVin") Integer minVin
    );
}
