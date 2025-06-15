package hse.kpo.repositories;

import hse.kpo.domain.objects.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIncomeRepository extends JpaRepository<Income, Long> {
}
