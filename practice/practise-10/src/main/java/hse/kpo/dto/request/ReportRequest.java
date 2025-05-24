package hse.kpo.dto.request;

import hse.kpo.enums.ReportFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * class of customer request.
 */
@RequiredArgsConstructor
@Getter
public class ReportRequest {
    @Schema(description = "Тип отчета (json, CSV, Markdown, XML)", example = "CSV")
    @NotNull(message = "Тип отчета обязателен")
    private final ReportFormat reportFormat;

    @Schema(description = "Название файла", example = "transport.json")
    @NotNull(message = "Имя файла обязательно")
    private final String filename;
}
