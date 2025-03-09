package tests.cucumber;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import procedures.LandingPageProcedures;
import procedures.LogOutProcedures;
import tests.functional.BaseTest;

import utils.GlobalVariables;

public class LogOutStepsDefinitions extends BaseTest {
    WebDriver driver;
    LandingPageProcedures landingPageProcedures;
    LogOutProcedures logOutProcedures;

    public LogOutStepsDefinitions() {
        this.driver = GlobalVariables.getDriver();
        this.landingPageProcedures = new LandingPageProcedures(driver);
        this.logOutProcedures = new LogOutProcedures(driver);
    }

//    @Given("^I am logged in with username (.+) and password (.+)$")
//    public void logged_in_with_username_and_password(String username, String password) {
//        landingPageProcedures.login(username, password);
//    }

    @When("^I log out from the application")
    public void i_log_out_from_the_application() {
		LogOutProcedures.assertLogout();
    }

    @Then("I should be redirected to the login page")
    public void i_should_be_redirected_to_the_login_page() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("https://www.saucedemo.com/"),
                "URL does not contain expected URL after logout. Actual URL: " + currentUrl);
    }
}
