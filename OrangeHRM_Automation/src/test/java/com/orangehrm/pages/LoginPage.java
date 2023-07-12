package com.orangehrm.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.base.BasePage;
import com.framework.dataaccess.ExcelUtil;
import com.framework.reports.ExtentReport;

public class LoginPage extends BasePage {

	@FindBy(id = "txtUsername")
	private WebElement userName_TextBox;

	@FindBy(id = "txtPassword")
	private WebElement password_TextBox;

	@FindBy(id = "btnLogin")
	private WebElement login_Button;

	@FindBy(id = "welcome")
	private WebElement welcome_Link;

	@FindBy(id = "spanMessage")
	private WebElement login_errorMsg;

	@FindBy(id = "spanCopyright")
	private WebElement footer_Msg;
	
	@FindBy(xpath="//span[@id='spanSocialMedia']/a")
	private List<WebElement> socialMediaLinks;

	public LoginPage(WebDriver driver,ExtentTest test) {
		super(driver,test);
	}

	public void login(String userName, String pwd) {

		userName_TextBox.sendKeys(userName);
		ExtentReport.logMessage(Status.INFO, "Enter Username: " + userName, false);
		password_TextBox.sendKeys(pwd);
		ExtentReport.logMessage(Status.INFO, "Enter Password: " + pwd, false);
		login_Button.click();
		ExtentReport.logMessage(Status.INFO, "Click on Login ", false);

	}

	public void verifyLoginErrorMessage(String expectedErrorMsg) {
		String actualErrorMsg = login_errorMsg.getText();
		validateField(expectedErrorMsg, actualErrorMsg, "Verify Login Error Message");

	}

	public void verifyLoginSuccessMessage() {
		String actualMsg = welcome_Link.getText();
		String expectedMsg = "Welcome Admin1";

		validateField(expectedMsg, actualMsg, "Verify Welcome Message");
	}

	public void verifyFooter(String expectedMsg) {
		String actualMsg = footer_Msg.getText();
		validateField(expectedMsg, actualMsg, "Verify Footer Message");
	}

	public List<String> clickOnSocialMediaLinks() {
		List<String> actualURLS = new ArrayList<String>();
		String parentWindow = getDriver().getWindowHandle();
		for (WebElement link : socialMediaLinks) {
			link.click();

			Set<String> allWindows = getDriver().getWindowHandles();

			for (String currentWindow : allWindows) {
				if (!currentWindow.equals(parentWindow)) {
					getDriver().switchTo().window(currentWindow);
				}
			}

		//	String actualUrl = getDriver().getCurrentUrl();
			
			actualURLS.add(getDriver().getCurrentUrl());
			
			getDriver().close();
			getDriver().switchTo().window(parentWindow);

		}
		return actualURLS;

	}
	
	public void verifyLinkNames(String expectedUrls,List<String> actualList) {
		String[] expectedValues= expectedUrls.split("&");		
		List<String> expectedList = Arrays.asList(expectedValues);
		validateField(expectedList, actualList, "Verify URLS");
		
	}
}
