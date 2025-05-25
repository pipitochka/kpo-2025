package hse.kpo.domains.interfaces;

public interface FileFactoryInterface {

    FileInterface createFile(String fileName, String path, String hash);
}
