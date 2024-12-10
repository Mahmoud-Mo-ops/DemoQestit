package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.GlobalVariables;

public class CompletePage {
    private By confirmationText = By.cssSelector(".complete-header");
    private WebDriver driver;

    public CompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findConfirmationText() {
        return driver.findElement(confirmationText);
    }
}