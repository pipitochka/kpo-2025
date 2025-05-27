package hse.kpo.controllers;

import hse.kpo.dto.FileAnalysisDto;
import hse.kpo.services.interfaces.GatewayServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * class of Gateway File Analysis Controller which resend all to Analysis microservice.
 */
@RestController
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
@Tag(name = "Анализ файлов", description = "Операции с анализом содержимого файлов")
public class GatewayFileAnalysisController {

    private final GatewayServiceInterface gatewayService;

    /**
     * function to get analysis by id.
     *
     * @param fileId analysis id.
     * @return FileAnalysisDto.
     */
    @GetMapping("/{fileId}")
    @Operation(summary = "Получить анализ файла по ID")
    public ResponseEntity<FileAnalysisDto> getAnalysisByFileId(@PathVariable int fileId) {
        return gatewayService.getFileAnalysisByFileId(fileId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Получить список всех анализов")
    public ResponseEntity<List<FileAnalysisDto>> getAnalysis() {
        return ResponseEntity.ok(gatewayService.getAllFileAnalysis());
    }

    /**
     * function to delete analysis by id.
     *
     * @param fileId analysis id.
     * @return ok or not found.
     */
    @DeleteMapping("/{fileId}")
    @Operation(summary = "Удалить анализ по ID анализа")
    public ResponseEntity<Void> deleteAnalysis(@PathVariable int fileId) {
        boolean deleted = gatewayService.deleteFileAnalysis(fileId);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }
}
