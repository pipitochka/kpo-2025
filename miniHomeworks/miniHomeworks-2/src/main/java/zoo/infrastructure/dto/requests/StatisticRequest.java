package zoo.infrastructure.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import zoo.domains.valueObjects.enums.AnimalTypes;

@Getter
@Setter
public class StatisticRequest {
    @NotNull(message = "Animal type is required")
    @Schema(example = "PREDATOR")
    private AnimalTypes type;
}
