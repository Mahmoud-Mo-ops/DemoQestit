package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import procedures.CompletePageProcedures;
import utils.BaseTest;
import utils.GlobalVariables;

public class CompletePageTest extends BaseTest {

    @Test
    public void verifiedConfirmationText() {
        String confirmationText = CompletePageProcedures.extractConfirmationText(GlobalVariables.driver);
        Assert.assertEquals(confirmationText, "Thank you for your order!");
    }
}