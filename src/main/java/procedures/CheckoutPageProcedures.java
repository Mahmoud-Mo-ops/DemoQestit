package procedures;
import org.openqa.selenium.WebDriver;

import data.CheckoutPageData;
import pageobjects.CheckoutPage;

public class CheckoutPageProcedures {
    private CheckoutPage checkoutPage;

    public CheckoutPageProcedures(WebDriver driver) {
        this.checkoutPage = new CheckoutPage();
    }


    public void fillOutDataUser(CheckoutPageData data) {
    	checkoutPage.sendFirstNameTextField(data.getFirstName())
                    .sendLastNameTextField(data.getLastName())
                    .sendPostalCodeTextField(data.getPostalCode())
                    .clickOnContinueButton();
    }


	public void fillOutDataUser(String firstName, String lastName, String postalCode) {
		checkoutPage.sendFirstNameTextField(firstName)
        .sendLastNameTextField(lastName)
        .sendPostalCodeTextField(postalCode)
        .clickOnContinueButton();		
	}
}