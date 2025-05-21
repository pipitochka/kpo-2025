package hse.kpo.dto;

import hse.kpo.enums.ReportFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class ReportRequest {
    @Schema(description = "Тип отчета (json, CSV, Markdown, XML)", example = "CSV")
    @NotNull(message = "Тип отчета обязателен")
    ReportFormat reportFormat;

    @Schema(description = "Название файла", example = "transport.json")
    @NotNull(message = "Имя файла обязательно")
    String filename;
}
