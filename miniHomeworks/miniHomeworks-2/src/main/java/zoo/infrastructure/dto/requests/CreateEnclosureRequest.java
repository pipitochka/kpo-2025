package zoo.infrastructure.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import zoo.domains.valueobjects.enums.AnimalTypes;

/**
 * class of create enclosure request.
 */
@Getter
@Setter
public class CreateEnclosureRequest {
    @NotNull(message = "Animal type is required")
    @Schema(example = "PREDATOR", description = "Type of animals this enclosure is for")
    private AnimalTypes animalType;

    @Min(value = 1, message = "Max size must be at least 1")
    @Schema(example = "5", description = "Maximum number of animals the enclosure can hold")
    private int maxSize;
}
