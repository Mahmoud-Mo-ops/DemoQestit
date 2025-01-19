package procedures;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import pageobjects.ProductCataloguePage;

public class LogOutProcedures {
    private WebDriver driver;
    ProductCataloguePage productCataloguePage;

    public LogOutProcedures(WebDriver driver) {
        this.driver = driver;
        this.productCataloguePage = new ProductCataloguePage(driver);
    }

    // Click on hamburger menu
    public LogOutProcedures openHamburgerMenu() {
        productCataloguePage.hamburgerMenu().click();
        return this;
    }

    // Click on logout page
    public LogOutProcedures performLogOut() {
        productCataloguePage.LogOutButton().click();
        return this;
    }

    public LogOutProcedures assertLogout() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("https://www.saucedemo.com/"), 
            "URL does not contain expected URL after logout. Actual URL: " + currentUrl);
        return this;
    }
}
