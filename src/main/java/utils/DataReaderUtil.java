package utils;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class DataReaderUtil {

    // Generic method to deserialize JSON into any array type
    public static <T> T[] getJsonDataToArray(String filePath, Class<T[]> clazz) throws IOException {
        // Create a Gson object for parsing
        Gson gson = new Gson();
        
        // Use JsonReader to enable lenient parsing
        JsonReader reader = new JsonReader(new FileReader(filePath));
        reader.setLenient(true); // Allow lenient parsing to tolerate malformed JSON
        
        // Deserialize JSON content to the specified class type array
        return gson.fromJson(reader, clazz);
    }
}
