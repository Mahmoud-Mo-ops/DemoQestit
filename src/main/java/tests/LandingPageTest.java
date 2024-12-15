
package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.JsonDataReader;
import data.LoginLandingPageData;
import procedures.LandingPageProcedures;
import utils.ConfigReader;
import utils.GlobalVariables;

public class LandingPageTest extends BaseTest {
	WebDriver driver;
	LoginLandingPageData data;
    LandingPageProcedures procedures;
    ConfigReader configReader;
    
    @BeforeMethod
    public void setUp() throws IOException {
        configReader = new ConfigReader();
        GlobalVariables.driver.get(configReader.getUrl());
        procedures = new LandingPageProcedures(GlobalVariables.driver);
        data = JsonDataReader.getUserData(); // Read data from JSON file
    }

    @Test
    public void loginTest() {
        procedures.login(data, GlobalVariables.driver);
    }
}
