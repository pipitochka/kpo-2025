package zoo.infrastructure.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import zoo.domains.valueObjects.enums.AnimalFood;
import zoo.domains.valueObjects.enums.AnimalGender;
import zoo.domains.valueObjects.enums.AnimalStatus;
import zoo.domains.valueObjects.enums.AnimalTypes;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;

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
