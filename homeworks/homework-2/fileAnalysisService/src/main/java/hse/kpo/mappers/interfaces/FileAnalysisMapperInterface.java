package hse.kpo.mappers.interfaces;

import hse.kpo.domains.interfaces.AnalysisInterface;
import hse.kpo.dto.FileAnalysisDto;

public interface FileAnalysisMapperInterface {

    public FileAnalysisDto toDto(AnalysisInterface file);

}
