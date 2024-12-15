package utils;

import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import data.CheckoutPageData;
import data.LoginLandingPageData;

public class JsonDataReader {
	public static LoginLandingPageData getUserData() throws IOException {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(
				"\\Users\\Mahmoud.Gomaa\\eclipse-workspace\\Demo\\src\\main\\resources\\globalData.json"));
		LoginLandingPageData userData = gson.fromJson(reader, LoginLandingPageData.class);
		reader.close();
		return userData;
	}

	public static CheckoutPageData getCutomerAdressInformation() throws IOException {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(
				"\\Users\\Mahmoud.Gomaa\\eclipse-workspace\\Demo\\src\\main\\java\\data\\globalData.json"));
		CheckoutPageData adressData = gson.fromJson(reader, CheckoutPageData.class);
		reader.close();
		return adressData;
	}
}

