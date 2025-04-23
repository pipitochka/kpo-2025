package zoo.infrastructure.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import zoo.domains.valueObjects.enums.AnimalFood;
import zoo.domains.valueObjects.enums.AnimalGender;
import zoo.domains.valueObjects.enums.AnimalStatus;
import zoo.domains.valueObjects.enums.AnimalTypes;

import java.util.Date;

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
