package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.GlobalVariables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LandingPage {
    private static final Logger logger = LogManager.getLogger(LandingPage.class);

    private WebDriver driver;

    private By UserNameTextField = By.xpath("//input[@type='text']");
    private By PasswordTextField = By.xpath("//input[@type='password']");
    private By loginButton = By.id("login-button");

    public LandingPage(WebDriver driver) {
        this.driver = GlobalVariables.driver;
    }

    public LandingPage sendKeysUserNameTextField(String username) {
        logger.info("Entering username: " + username);
        driver.findElement(UserNameTextField).sendKeys(username);
        return this;
    }

    public LandingPage sendKeysPasswordTextField(String password) {
        logger.info("Entering password: " + password);
        driver.findElement(PasswordTextField).sendKeys(password);
        return this;
    }

    public LandingPage clickLoginButton() {
        logger.info("Clicking login button");
        driver.findElement(loginButton).click();
        return this;
    }
}