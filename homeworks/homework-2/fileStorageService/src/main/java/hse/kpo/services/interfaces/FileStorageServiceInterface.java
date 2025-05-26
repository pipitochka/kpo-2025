package hse.kpo.services.interfaces;

import hse.kpo.dto.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface FileStorageServiceInterface {
    FileDto saveFile(MultipartFile file);

    Optional<FileDto> getFileById(int id);

    List<FileDto> getAllFiles();

    boolean deleteFile(int id);

    public Optional<String> getFileContentById(int fileId);
}
