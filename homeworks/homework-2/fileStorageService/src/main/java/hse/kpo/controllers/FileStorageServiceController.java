package hse.kpo.controllers;

import hse.kpo.dto.FileDto;
import hse.kpo.services.realizations.FileStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Tag(name = "Файлы", description = "Управление файловой системой")
public class FileStorageServiceController {
    private final FileStorageService fileStorageService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить файл по id")
    public ResponseEntity<FileDto> getFileById(@PathVariable int id) {
        return fileStorageService.getFileById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузить файл")
    public ResponseEntity<FileDto> createFile(@Parameter(description = "Файл для загрузки") @RequestPart("file") MultipartFile file) {
        FileDto savedFile = fileStorageService.saveFile(file);
        return ResponseEntity.ok(savedFile);
    }

    @GetMapping()
    @Operation(summary = "Получить все файлы")
    public ResponseEntity<List<FileDto>> getAllFiles() {
        return ResponseEntity.ok(fileStorageService.getAllFiles());
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удалить файл")
    public ResponseEntity<Void> deleteFileById(@PathVariable int id){
        boolean deleted = fileStorageService.deleteFile(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @GetMapping("/{id}/content")
    @Operation(summary = "Получить содержимое файла по id")
    public ResponseEntity<String> getFileContentById(@PathVariable int id) {
        return fileStorageService.getFileContentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
