package procedures;

import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import pageobjects.ProductCataloguePage;

public class LogOutProcedures {
	private WebDriver driver;
	static ProductCataloguePage productCataloguePage;

	public LogOutProcedures(WebDriver driver) {
		this.driver = driver;
		this.productCataloguePage = new ProductCataloguePage(driver);
	}

	@Step("Log Out")
	public static void assertLogout() {
		productCataloguePage.ClickhamburgerMenu()
		                    .ClickLogOutButton();
		

	}

}
