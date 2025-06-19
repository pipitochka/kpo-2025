package hse.kpo.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateAccountRequest(
        @Schema(description = "Имя аккаунта", example = "Иван")
        @NotBlank
        String accountName,

        @Schema(description = "Баланс аккаунта", example = "1000.0")
        double accountBalance
) {}
