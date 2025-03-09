package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.GlobalVariables;

public class CompletePage {
	private WebDriver driver;
	private By confirmationText = By.cssSelector(".complete-header");

	public CompletePage(WebDriver driver) {
		this.driver = GlobalVariables.getDriver();
	}

	public WebElement findConfirmationText() {
		return driver.findElement(confirmationText);
	}
}