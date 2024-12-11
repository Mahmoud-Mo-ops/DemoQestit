package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage {
    private WebDriver driver;

    private By UserNameTextField = By.xpath("//input[@type='text']");
    private By PasswordTextField = By.xpath("//input[@type='password']");
    private By loginButton = By.id("login-button");

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public LandingPage sendKeysUserNameTextField(String username) {
        System.out.println("Entering username: " + username);
        driver.findElement(UserNameTextField).sendKeys(username);
        return this;
    }

    public LandingPage sendKeysPasswordTextField(String password) {
        System.out.println("Entering password: " + password);
        driver.findElement(PasswordTextField).sendKeys(password);
        return this;
    }

    public LandingPage clickLoginButton() {
        System.out.println("Clicking login button");
        driver.findElement(loginButton).click();
        return this;
    }
}