package hse.kpo.repositories;

import hse.kpo.domain.realizations.objects.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IOperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> findByAccount_Id(Long accountId);

    Optional<Operation> findByExternalId(String externalId);
}
