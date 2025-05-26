package hse.kpo.repositories;

import hse.kpo.domains.realizations.AnalysisRealization;
import hse.kpo.dto.FileAnalysisDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileAnalysisRepositoryInterface extends JpaRepository<AnalysisRealization, Integer> {
    Optional<AnalysisRealization> findByFileId(int fileId);
}
