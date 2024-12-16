package tests;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import data.CheckoutPageData;
import procedures.CheckoutPageProcedures;
import utils.BaseTest;
import utils.DataReaderUtil;

public class CheckoutPageTest extends BaseTest {
	WebDriver driver;
	private CheckoutPageProcedures checkoutPageProcedures;
	List<CheckoutPageData> dataList;

	@BeforeMethod
	public void setUp() throws IOException {
		final Logger logger = LogManager.getLogger(CheckoutPageTest.class);

		// data = new CheckoutPageData();
		checkoutPageProcedures = new CheckoutPageProcedures(driver);
		String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\globalData.json";
		dataList = DataReaderUtil.getJsonDataToList(filePath, CheckoutPageData.class);

	}

	@Test()
	public void goToCheckoutOverview() throws IOException, InterruptedException {
		String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\globalData.json";
		dataList = DataReaderUtil.getJsonDataToList(filePath, CheckoutPageData.class);
		for (CheckoutPageData data : dataList) {
			checkoutPageProcedures.fillOutDataUser(data);
		}	
	}
}