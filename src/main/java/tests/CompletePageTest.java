package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import procedures.CompletePageProcedures;
import utils.GlobalVariables;

public class CompletePageTest extends BaseTest {
    @Test(description="Verify the confirmation message displayed after completing the checkout process correctly indicates that the product has been successfully ordered")
    public void verifiedConfirmationText() {
        String confirmationText = CompletePageProcedures.extractConfirmationText(GlobalVariables.getDriver());
        Assert.assertEquals(confirmationText, "Thank you for your order!");
    }
}