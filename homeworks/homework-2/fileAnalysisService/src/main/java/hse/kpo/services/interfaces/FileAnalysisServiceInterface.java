package hse.kpo.services.interfaces;

import hse.kpo.dto.FileAnalysisDto;

import java.util.List;
import java.util.Optional;

public interface FileAnalysisServiceInterface {

    Optional<FileAnalysisDto> getFileAnalysisByFileId(int fileId);

    List<FileAnalysisDto> getAllFileAnalysis();

    boolean deleteFileAnalysis(int fileId);
}
