package hse.kpo.mappers.realization;

import hse.kpo.domains.interfaces.AnalysisInterface;
import hse.kpo.dto.FileAnalysisDto;
import hse.kpo.mappers.interfaces.FileAnalysisMapperInterface;
import org.springframework.stereotype.Component;

@Component
public class FileAnalysisMapper implements FileAnalysisMapperInterface {

    @Override
    public FileAnalysisDto toDto(AnalysisInterface file) {
        return new FileAnalysisDto(file.getId(), file.getFileId(), file.getWordCount(), file.getParagraphCount(),
                file.getCharacterCount());
    }
}
