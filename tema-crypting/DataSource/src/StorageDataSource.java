import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class StorageDataSource implements DataSource {

    private final Path filePath = Path.of("storage.txt");

    @Override
    public void Write(String data) {
        try {
            Files.writeString(filePath, data, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file!", e);
        }
    }

    @Override
    public String Read() {
        try {
            if (!Files.exists(filePath)) return "";
            return Files.readString(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file!", e);
        }
    }
}