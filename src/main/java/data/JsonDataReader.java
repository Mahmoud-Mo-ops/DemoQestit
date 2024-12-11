package data;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class JsonDataReader {
    public static LandingPageData getUserData() throws IOException {
        Gson gson = new Gson(); 
        JsonReader reader = new JsonReader(new FileReader("\\Users\\Mahmoud.Gomaa\\eclipse-workspace\\Demo\\src\\main\\java\\data\\globalData.json"));
        LandingPageData userData = gson.fromJson(reader, LandingPageData.class);
        reader.close();
        return userData;
    }
}
