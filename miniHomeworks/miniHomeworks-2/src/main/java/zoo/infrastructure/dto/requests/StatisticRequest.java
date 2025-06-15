package zoo.infrastructure.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import zoo.domains.valueobjects.enums.AnimalTypes;

/**
 * class of statistic requests.
 */
@Getter
@Setter
public class StatisticRequest {
    @NotNull(message = "Animal type is required")
    @Schema(example = "PREDATOR")
    private AnimalTypes type;
}
