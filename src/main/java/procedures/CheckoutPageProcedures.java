package procedures;

import data.CheckoutPageData;
import pageobjects.CheckoutPage;

public class CheckoutPageProcedures {
    private CheckoutPage checkoutPage;

    public CheckoutPageProcedures() {
        this.checkoutPage = new CheckoutPage();
    }


    public void fillOutDataUser(CheckoutPageData data) {
    	checkoutPage.sendFirstNameTextField(data.getFirstName())
                    .sendLastNameTextField(data.getLastName())
                    .sendPostalCodeTextField(data.getPostalCode())
                    .clickOnContinueButton();
    }
}