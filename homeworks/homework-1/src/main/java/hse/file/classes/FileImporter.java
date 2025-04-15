package hse.file.classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * class to make objects from file.
 *
 * @param <T> type of making object
 */
public abstract class FileImporter<T> {

    /**
     * function to make object from file.
     *
     * @param filePath path.
     * @return object.
     */
    public final T importData(String filePath) {
        try {
            String content = readFile(filePath);

            return parseData(content);

        } catch (IOException e) {
            System.out.println("Error while reading file " + filePath);
            return null;
        }
    }

    private String readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readString(path);
    }

    protected abstract T parseData(String content);

}