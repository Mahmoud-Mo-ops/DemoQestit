package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.GlobalVariables;

public class CheckoutOverviewPage {
	private WebDriver driver;
   private By finishButton = By.id("finish");
   
   public CheckoutOverviewPage(WebDriver driver) {
		this.driver = GlobalVariables.getDriver();
   }
   
   public WebElement  getFinishButton () {  
	   return driver.findElement(finishButton);
   }
}
