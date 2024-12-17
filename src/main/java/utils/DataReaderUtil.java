package utils;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

public class DataReaderUtil {
	
    // Generic method to deserialize JSON into any array type
       public static <T> T[] getJsonDataToArray(String filePath, Class<T[]> clazz) throws IOException {
           // Read JSON content as String
	//	String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

        // Deserialize JSON string to the specified class type array,Use GSON to parse the JSON file and return an array of the desired class type
		Gson gson = new Gson();
		  FileReader reader = new FileReader(filePath);
        return gson.fromJson(reader, clazz);

	}

}
