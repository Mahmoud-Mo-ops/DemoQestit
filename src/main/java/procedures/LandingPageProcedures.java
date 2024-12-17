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

	// Landing page
	public void login(LoginLandingPageData data, WebDriver driver) {
		loginLandingPage.sendKeysUserNameTextField(data.getUserName())
		                .sendKeysPasswordTextField(data.getPassword())
				        .clickLoginButton();
		
	}
	
//	// Method to capture error message
//    public String catchErrorMessage() {
//        return loginLandingPage.getErrorField().getText();
//    }
//	// Landing page
//	public void Errorlogin(String errorUserName) {
//		loginLandingPage.sendKeysUserNameTextField(errorUserName);
//		               
//		
//	}
}