package hse.kpo.controllers;

import hse.kpo.dto.FileAnalysisDto;
import hse.kpo.services.interfaces.FileAnalysisServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
@Tag(name = "Анализ файлов", description = "Операции с анализом содержимого файлов")
public class FileAnalysisController {

    private final FileAnalysisServiceInterface fileAnalysisService;

    @GetMapping("/{fileId}")
    @Operation(summary = "Получить анализ файла по ID")
    public ResponseEntity<FileAnalysisDto> getAnalysisByFileId(@PathVariable int fileId) {
        return fileAnalysisService.getFileAnalysisByFileId(fileId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Получить список всех анализов")
    public ResponseEntity<List<FileAnalysisDto>> getAnalysis() {
        var result = fileAnalysisService.getAllFileAnalysis();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{fileId}")
    @Operation(summary = "Удалить анализ по ID файла")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable int fileId) {
        boolean deleted = fileAnalysisService.deleteFileAnalysis(fileId);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }
}
