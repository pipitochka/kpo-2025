package zoo.infrastructure.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import zoo.domains.valueobjects.enums.AnimalFood;


/**
 * class of create feeding schedule request.
 */
@Getter
@Setter
public class CreateFeedingScheduleRequest {

    @NotNull(message = "Name is required")
    @Schema(example = "Leo")
    private String name;

    @NotNull(message = "Food is required")
    @Schema(example = "MEAT", description = "Type of food to give")
    private AnimalFood food;

    @NotNull(message = "Feeding date is required")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Schema(example = "2025-04-24T10:00:00Z", description = "Date and time for scheduled feeding")
    private Date date;
}