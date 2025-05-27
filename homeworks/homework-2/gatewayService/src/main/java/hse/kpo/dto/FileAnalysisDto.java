package hse.kpo.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
