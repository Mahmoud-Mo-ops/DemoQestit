package procedures;

import org.openqa.selenium.WebDriver;
import pageobjects.CompletePage;

public class CompletePageProcedures {

    public static String extractConfirmationText(WebDriver driver) {
        CompletePage completePage = new CompletePage(driver);
        return completePage.findConfirmationText().getText();
    }
}