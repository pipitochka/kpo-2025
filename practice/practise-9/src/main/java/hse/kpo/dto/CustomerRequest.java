package hse.kpo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * class of customer requests.
 */
@Getter
@RequiredArgsConstructor
public class CustomerRequest {
    @Schema(description = "Имя покупателя", example = "Артем")
    @NotNull(message = "Имя покупателя обязательно")
    private final String name;

    @Schema(description = "Сила ног", example = "10")
    @NotNull(message = "Сила ног обязательна")
    @Min(value = 1, message = "Минимальный размер педалей - 1")
    @Max(value = 15, message = "Максимальный размер педалей - 15")
    private final int legPower;

    @Schema(description = "Сила рук", example = "15")
    @NotNull(message = "Сила рук обязательна")
    @Min(value = 1, message = "Минимальный размер ручных педалей - 1")
    @Max(value = 15, message = "Максимальный размер ручных педалей - 15")
    private final int handPower;

    @Schema(description = "Iq покупателя", example = "200")
    private final int iq;

}
