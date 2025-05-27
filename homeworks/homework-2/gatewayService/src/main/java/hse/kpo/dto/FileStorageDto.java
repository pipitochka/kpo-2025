package hse.kpo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * dto which received from storage microservice.
 */
@Getter
@RequiredArgsConstructor
public class FileStorageDto {
    @NotNull
    private final int id;

    @NotBlank
    private final String name;

    @NotBlank
    private final String path;

    @NotBlank
    private final String hash;
}
