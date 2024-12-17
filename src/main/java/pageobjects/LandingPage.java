package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LandingPage {
    private static final Logger logger = LogManager.getLogger(LandingPage.class);

    private WebDriver driver;
    private By userNameTextField = By.xpath("//input[@type='text']");
    private By passwordTextField = By.xpath("//input[@type='password']");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.xpath("//h3[@data-test='error']");

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public LandingPage sendKeysUserNameTextField(String username) {
        logger.info("Entering username: " + username);
        driver.findElement(userNameTextField).sendKeys(username);
        return this;
    }

    public LandingPage sendKeysPasswordTextField(String password) {
        logger.info("Entering password: " + password);
        driver.findElement(passwordTextField).sendKeys(password);
        return this;
    }

    public LandingPage clickLoginButton() {
        logger.info("Clicking login button");
        driver.findElement(loginButton).click();
        return this;
    }

    // Retrieve error message text
    public String getErrorMessageText() {
        logger.info("Fetching error message");
        return driver.findElement(errorMessage).getText();
    }

  

}
