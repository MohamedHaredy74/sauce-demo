package utils;

import tools.jackson.databind.ObjectMapper;
import java.nio.file.Path;

public class JsonReader {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String BASE_PATH =
            "src/test/resources/testData/";

    private JsonReader() {
    }

    public static <T> T read(String filePath, Class<T> clazz) {

        try {
            return mapper.readValue(
                    Path.of(BASE_PATH, filePath).toFile(),
                    clazz
            );
        } catch (Exception e) {
            throw new RuntimeException(
                    "Unable to read json file: " + filePath,
                    e
            );
        }
    }
}