package hse.kpo.mappers.interfaces;

import hse.kpo.domains.interfaces.FileInterface;
import hse.kpo.dto.FileDto;

public interface FileMapperInterface {

    public FileDto toDto(FileInterface file);

    //public FileInterface toFile(FileDto fileDto);

}
