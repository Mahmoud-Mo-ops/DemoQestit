package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.GlobalVariables;

public class CheckoutPage {
    private WebDriver driver;

    private By firstNameTextField = By.id("first-name");
    private By lastNameTextField = By.xpath("//input[@placeholder='Last Name']");
    private By postalCodeTextField = By.id("postal-code");
    private By continueButton = By.id("continue");

    public CheckoutPage() {
        this.driver = GlobalVariables.driver;
    }

    public CheckoutPage sendFirstNameTextField(String firstNameText) {
        driver.findElement(firstNameTextField).sendKeys(firstNameText);
        return this;
    }

    public CheckoutPage sendLastNameTextField(String lastNameText) {
        driver.findElement(lastNameTextField).sendKeys(lastNameText);
        return this;
    }

    public CheckoutPage sendPostalCodeTextField(String postalCodeText) {
        driver.findElement(postalCodeTextField).sendKeys(postalCodeText);
        return this;
    }

    public CheckoutPage clickOnContinueButton() {
        driver.findElement(continueButton).click();
        return this;
    }
}