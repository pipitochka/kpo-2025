package hse.kpo.repositories;

import hse.kpo.domain.realizations.objects.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOperationRepositories extends JpaRepository<Operation, Long> {
}
