package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.TestUtilities;
import qestit.pageObjects.LandingPage;

public class ErrorValidationsTest extends TestUtilities {

	@Test
	public void errorValidation() {
		LandingPage landingPage = new LandingPage(driver);
		landingPage.login("standard_user", "wrong password");
		Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",
				landingPage.getErrorMessage());

	}
}