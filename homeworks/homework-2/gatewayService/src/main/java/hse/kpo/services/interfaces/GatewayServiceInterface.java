package hse.kpo.services.interfaces;

import hse.kpo.dto.FileAnalysisDto;
import hse.kpo.dto.FileStorageDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface GatewayServiceInterface {
    Optional<FileAnalysisDto> getFileAnalysisByFileId(int fileId);

    List<FileAnalysisDto> getAllFileAnalysis();

    boolean deleteFileAnalysis(int fileId);

    FileStorageDto saveFile(MultipartFile file);

    Optional<FileStorageDto> getFileDescriptionById(int id);

    List<FileStorageDto> getAllFiles();

    boolean deleteFile(int id);

    public Optional<String> getFileContentById(int fileId);
}
