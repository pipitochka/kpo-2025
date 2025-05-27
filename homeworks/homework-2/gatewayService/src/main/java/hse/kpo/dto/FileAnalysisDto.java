package hse.kpo.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * dto which received from analysis microservice.
 */
@Getter
@RequiredArgsConstructor
public class FileAnalysisDto {

    @NonNull
    private final int id;

    @NonNull
    private final int fileId;

    private final int wordCount;

    private final int paragraphCount;

    private final int characterCount;
}
