
package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;

import data.LandingPageData;
import data.JsonDataReader;
import procedures.LandingPageProcedures;
import utils.ConfigReader;
import utils.GlobalVariables;

public class LandingPageTest extends BaseTest {
    LandingPageData data;
    LandingPageProcedures procedures;
    ConfigReader configReader;
    @BeforeMethod
    public void setUp() throws IOException {
        configReader = new ConfigReader();
        GlobalVariables.driver.get(configReader.getUrl());
      //  GlobalVariables.driver.get("https://www.saucedemo.com/");
        procedures = new LandingPageProcedures(GlobalVariables.driver);
        data = JsonDataReader.getUserData(); // Read data from JSON file
    }

    @Test
    public void loginTest() {
        procedures.login(data, GlobalVariables.driver);
    }
}
