package hse.kpo.controllers;

import hse.kpo.dto.FileStorageDto;
import hse.kpo.services.interfaces.GatewayServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Tag(name = "Файлы", description = "Управление файловой системой")
public class GatewayFileStorageContoller {
    private final GatewayServiceInterface gatewayService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить файл по id")
    public ResponseEntity<FileStorageDto> getFileById(@PathVariable int id){
        return gatewayService.getFileDescriptionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузить файл")
    public ResponseEntity<FileStorageDto> createFile(
            @Parameter(description = "Файл для загрузки") @RequestPart("file") MultipartFile file) {
        FileStorageDto savedFile = gatewayService.saveFile(file);
        return ResponseEntity.ok(savedFile);
    }

    @GetMapping()
    @Operation(summary = "Получить все файлы")
    public ResponseEntity<List<FileStorageDto>> getAllFiles(){
        return ResponseEntity.ok(gatewayService.getAllFiles());
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удалить файл")
    public ResponseEntity<Void> deleteFileById(@PathVariable int id){
        boolean deleted = gatewayService.deleteFile(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @GetMapping("/{id}/content")
    @Operation(summary = "Получить содержимое файла по id")
    public ResponseEntity<String> getFileContentById(@PathVariable int id) {
        return gatewayService.getFileContentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
