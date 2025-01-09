package utils;

import java.io.IOException;

import com.google.gson.Gson;

import data.SubmitOrderData;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class ExtractJsonData {
	
	@Step("Extracting data from JSON file and adding to the report")
    public static void extractData(String filePath) throws IOException {
        // Read and deserialize the JSON into an array of SubmitOrderData objects
        SubmitOrderData[] submitOrderDataArray = DataReaderUtil.getJsonDataToArray(filePath, SubmitOrderData[].class);

        // Convert the array back to a JSON string (you can customize how you want it displayed)
        Gson gson = new Gson();
        String jsonContent = gson.toJson(submitOrderDataArray);

        // Attach the JSON data to the Allure report
        Allure.addAttachment("JSON Data", "application/json", jsonContent, ".json");

        // Optionally, log the content if needed
        System.out.println("JSON data has been successfully extracted and attached to Allure.");
    }

    public static void main(String[] args) {
        try {
            String filePath = System.getProperty("user.dir") + "/src/main/resources/globalData.json";
            extractData(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
