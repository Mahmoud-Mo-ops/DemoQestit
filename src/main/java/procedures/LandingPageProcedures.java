package procedures;

import org.openqa.selenium.WebDriver;

import data.LoginLandingPageData;
import pageobjects.LandingPage;

public class LandingPageProcedures {
	private WebDriver driver;
	private LandingPage loginLandingPage;

	public LandingPageProcedures(WebDriver driver) {
		this.driver = driver;
		loginLandingPage = new LandingPage(driver);

	}

	public void login(LoginLandingPageData data, WebDriver driver) {
		loginLandingPage
		                .sendKeysUserNameTextField(data.getUserName())
		                .sendKeysPasswordTextField(data.getPassword())
				        .clickLoginButton();

	}

	// for error validation
	public void LoginInvalidUserName(String username) {
		loginLandingPage.sendKeysUserNameTextField(username);
		loginLandingPage.clickLoginButton();

	}

	// Validate error message
	public boolean validateErrorMessage(String expectedError) {
		String actualError = loginLandingPage.getErrorMessageText();
		return actualError.equals(expectedError);
	}

	public String getErrorMessage() {
		return loginLandingPage.getErrorMessageText();
	}

	// for cucumber
	public void login(String username, String password) {
		loginLandingPage.sendKeysUserNameTextField(username).sendKeysPasswordTextField(password).clickLoginButton();
	}

}