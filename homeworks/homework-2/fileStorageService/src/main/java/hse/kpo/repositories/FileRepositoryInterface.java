package hse.kpo.repositories;

import hse.kpo.domains.interfaces.FileInterface;
import hse.kpo.domains.realizations.FileRealization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FileRepositoryInterface extends JpaRepository<FileRealization, Integer> {

    @Query("SELECT f FROM FileRealization f WHERE f.hash = :hash")
    Optional<FileRealization> findByHash(String hash);
}
