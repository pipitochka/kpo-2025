package hse.kpo.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FileAnalysisDto {

    private final int id;

    private final int fileId;

    private final int wordCount;

    private final int paragraphCount;

    private final int characterCount;
}
