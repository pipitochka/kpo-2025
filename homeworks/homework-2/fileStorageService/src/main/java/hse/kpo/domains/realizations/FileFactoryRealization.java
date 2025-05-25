package hse.kpo.domains.realizations;

import hse.kpo.domains.interfaces.FileFactoryInterface;
import hse.kpo.domains.interfaces.FileInterface;
import org.springframework.stereotype.Component;

@Component
public class FileFactoryRealization implements FileFactoryInterface {

    @Override
    public FileInterface createFile(String fileName, String path, String hash) {
        return new FileRealization(fileName, path, hash);
    }
}
