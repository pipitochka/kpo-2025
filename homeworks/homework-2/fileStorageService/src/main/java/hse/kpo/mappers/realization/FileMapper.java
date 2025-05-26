package hse.kpo.mappers.realization;

import hse.kpo.domains.interfaces.FileFactoryInterface;
import hse.kpo.domains.interfaces.FileInterface;
import hse.kpo.dto.FileDto;
import hse.kpo.mappers.interfaces.FileMapperInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileMapper implements FileMapperInterface {

    @Override
    public FileDto toDto(FileInterface file) {
        return new FileDto(file.getId(), file.getName(), file.getPath(), file.getHash());
    }

}
