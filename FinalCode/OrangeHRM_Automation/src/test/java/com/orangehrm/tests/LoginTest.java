package com.orangehrm.tests;

import java.util.List;

import org.testng.annotations.Test;

import com.framework.base.BaseTest;
import com.orangehrm.pages.LoginPage;

public class LoginTest extends BaseTest {

	@Test
	public void loginWithValidCredentials() {
		LoginPage loginPage = new LoginPage(getDriver(),getExtentTest());
		loginPage.login("Admin", "Admin");
		loginPage.verifyLoginSuccessMessage();
	}

	@Test
	public void loginWithBlankPassword() {
		LoginPage loginPage = new LoginPage(getDriver(),getExtentTest());
		loginPage.login("Admin", "");
		loginPage.verifyLoginErrorMessage("Password cannot be empty1");
	}

	@Test
	public void loginWithInvalidCredentials() {
		LoginPage loginPage = new LoginPage(getDriver(),getExtentTest());
		loginPage.login(getData("InvalidUserName"), getData("InvalidPassword"));
		loginPage.verifyLoginErrorMessage(getData("errorMessage"));

	}

	@Test
	public void verifyCopyRightFooterText() {
		LoginPage loginPage = new LoginPage(getDriver(),getExtentTest());
		loginPage.verifyFooter(getData("successMessage"));
	}

	@Test
	public void verifySocialMediaLinks() {
		LoginPage loginPage = new LoginPage(getDriver(),getExtentTest());
		List<String> actualValue = loginPage.clickOnSocialMediaLinks();
		loginPage.verifyLinkNames(getData("Link Names"), actualValue);
	}
}
