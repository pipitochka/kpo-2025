package zoo.infrastructure.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import zoo.domains.valueobjects.enums.AnimalFood;
import zoo.domains.valueobjects.enums.AnimalGender;
import zoo.domains.valueobjects.enums.AnimalStatus;
import zoo.domains.valueobjects.enums.AnimalTypes;

/**
 * class of create animal request.
 */
@Getter
@Setter
public class CreateAnimalRequest {

    @NotNull(message = "Animal type is required")
    @Schema(example = "PREDATOR")
    private AnimalTypes type;

    @NotNull(message = "Name is required")
    @Schema(example = "Leo")
    private String name;

    @NotNull(message = "Birth date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(example = "2020-05-10")
    private Date birthDate;

    @NotNull(message = "Gender is required")
    @Schema(example = "MALE")
    private AnimalGender gender;

    @NotNull(message = "Favorite food is required")
    @Schema(example = "MEAT")
    private AnimalFood favoriteFood;

    @NotNull(message = "Status is required")
    @Schema(example = "HEALTHY")
    private AnimalStatus status;
}
