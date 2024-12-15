package utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DataReaderUtil  {
	String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\globalData.json";

	public static <T> List<T> getJsonDataToList(String filepath, Class<T> clazz) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		// String to List<T> (Using GSON)
		Gson gson = new Gson();
		List<T> data = gson.fromJson(jsonContent, TypeToken.getParameterized(List.class, clazz).getType());

		return data;
	}
	
	
}

