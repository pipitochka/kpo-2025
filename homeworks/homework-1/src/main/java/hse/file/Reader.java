package hse.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class Reader<T> {
    public final T importData(String filePath) {
        try {
            // 1. Читаем файл в строку
            String content = readFile(filePath);

            // 2. Парсим данные (определяется в подклассе)
            return parseData(content);

        } catch (IOException e) {
            System.out.println("Error reading file " + filePath);
            return null;
        }
    }

    private String readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readString(path);
    }

    protected abstract T parseData(String content);
}
