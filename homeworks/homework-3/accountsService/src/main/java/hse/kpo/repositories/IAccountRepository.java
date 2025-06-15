package hse.kpo.repositories;

import hse.kpo.domain.realizations.objects.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, Long> {
}
