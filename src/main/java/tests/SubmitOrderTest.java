package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import procedures.SubmitOrderPurchase;
import utils.ConfigReader;
import utils.GlobalVariables;

public class SubmitOrderTest extends BaseTest {
    SubmitOrderPurchase submitOrderPurchase;
    ConfigReader configReader;
    WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {
        driver = GlobalVariables.getDriver();
        configReader = new ConfigReader();
        driver.get(configReader.getUrl());
        submitOrderPurchase = new SubmitOrderPurchase(driver);
    }

    @Test
    public void submitOrderTest() throws IOException {
        String confirmationText = submitOrderPurchase
            .LogIn()
            .addItemsLessThanTenDollarsToCart()
            .NavigateToCheckOutReview()
            .fillOutDataUser("firstName", "lastName", "postalCode")
            .gotToCompletePage()
            .extractConfirmationText();

        Assert.assertEquals(confirmationText, "Thank you for your order!");
    }
    
    
}
