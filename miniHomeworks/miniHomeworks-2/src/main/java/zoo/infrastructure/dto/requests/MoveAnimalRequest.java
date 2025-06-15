package zoo.infrastructure.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * class of move animal requests.
 */
@Getter
@Setter
public class MoveAnimalRequest {
    @NotNull(message = "Name is required")
    @Schema(example = "Leo")
    private String name;

    @NotNull(message = "Enclosure ID is required")
    @Schema(example = "8a1f1e9a-5d3e-4c8b-9d4a-123456789abc", description = "ID of the animal")
    private UUID enclosureId;
}
