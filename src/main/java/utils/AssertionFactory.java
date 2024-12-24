package utils;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class AssertionFactory {
	private WebDriver driver;
	private SoftAssert softAssert;

	// Constructor
	public AssertionFactory(WebDriver driver) {
		this.driver = driver;
		this.softAssert = new SoftAssert();
	}

	// Method to loop over all elements and perform an action
	public void loopOverElements() {
		List<WebElement> elements = driver.findElements(By.cssSelector("*"));
		for (WebElement element : elements) {
			try {
				// Example: Log the tag name of each element
				System.out.println("Element: " + element.getTagName());
			} catch (Exception e) {
				// Handle cases where elements are not interactable
				System.out.println("Error accessing element: " + e.getMessage());
			}
		}
	}

	// Method to perform an assertion with detailed logging
	public void assertTrue(boolean condition, String expected, String actual, String message) {
		if (!condition) {
			System.out.println("Assertion failed: " + message);
			System.out.println("Expected: \"" + expected + "\", but found: \"" + actual + "\"");
		}
		softAssert.assertTrue(condition, message + " (Expected: \"" + expected + "\", Found: \"" + actual + "\")");
	}

	// Finalize the assertions
	public void assertAll() {
		softAssert.assertAll();
	}

}
