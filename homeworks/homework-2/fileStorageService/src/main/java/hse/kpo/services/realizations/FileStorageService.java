package hse.kpo.services.realizations;

import hse.kpo.domains.interfaces.FileFactoryInterface;
import hse.kpo.domains.interfaces.FileInterface;
import hse.kpo.domains.realizations.FileRealization;
import hse.kpo.dto.FileDto;
import hse.kpo.mappers.interfaces.FileMapperInterface;
import hse.kpo.mappers.realization.FileMapper;
import hse.kpo.repositories.FileRepositoryInterface;
import hse.kpo.services.interfaces.FileStorageServiceInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Log4j2
@Getter
public class FileStorageService implements FileStorageServiceInterface {

    private final FileRepositoryInterface fileRepository;

    private final FileFactoryInterface fileFactory;

    private final FileMapperInterface fileMapper;

    @Value("${filestorage.path}")
    private String storagePath;

    @Override
    public FileDto saveFile(MultipartFile file) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] fileBytes = file.getBytes();
            byte[] hashBytes = digest.digest(fileBytes);
            String hash = bytesToHex(hashBytes);

            Optional<FileRealization> existingFile = fileRepository.findByHash(hash);
            if (existingFile.isPresent()) {
                return fileMapper.toDto(existingFile.get());
            }

            String filename = file.getOriginalFilename();
            String path = storagePath + "/" + filename;

            Files.write(Paths.get(path), fileBytes);

            FileInterface newFile = fileFactory.createFile(filename, path, hash);
            FileRealization savedFile = fileRepository.save((FileRealization) newFile);

            return fileMapper.toDto(savedFile);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @Override
    public Optional<FileDto> getFileById(int id) {
        return fileRepository.findById(id)
                .map(fileMapper::toDto);
    }

    @Override
    public List<FileDto> getAllFiles() {
        return fileRepository.findAll().stream()
                .map(fileMapper::toDto)
                .toList();
    }

    @Override
    public boolean deleteFile(int id) {
        Optional<FileRealization> optionalFile = fileRepository.findById(id);

        if (optionalFile.isPresent()) {
            FileRealization file = optionalFile.get();

            try {
                Files.deleteIfExists(Paths.get(file.getPath()));
            } catch (IOException e) {
                log.warn("Не удалось удалить физический файл: {}", file.getPath(), e);
            }

            fileRepository.deleteById(id);
            return true;
        }

        return false;

    }
}
