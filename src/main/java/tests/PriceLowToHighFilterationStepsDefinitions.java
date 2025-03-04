package tests;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Then;
import procedures.CartPageProcedures;
import procedures.CheckoutPageProcedures;
import procedures.ProductCatalogueProcedures;
import utils.GlobalVariables;

public class PriceLowToHighFilterationStepsDefinitions extends BaseTest {
    WebDriver driver;
    ProductCatalogueProcedures productCatalogueProcedures;
    CartPageProcedures cartPageProcedures;
    CheckoutPageProcedures checkoutPageProcedures;

    // Add a constructor to initialize WebDriver and procedures
    public PriceLowToHighFilterationStepsDefinitions() {
        this.driver = GlobalVariables.getDriver(); // Ensure WebDriver is set
        this.productCatalogueProcedures = new ProductCatalogueProcedures(driver);
         
    }
          
    
    @Then("Verify sorting by Price Low to High") 
    public void Filter_Products_Low_To_High_dependingOn_Price()  throws InterruptedException {
	    productCatalogueProcedures.PriceSortingLowToHighProcedures();
    }
}
