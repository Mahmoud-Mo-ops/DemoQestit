package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.GlobalVariables;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = GlobalVariables.driver;
    }

    public WebElement getCheckoutButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait
        return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));
    }
}