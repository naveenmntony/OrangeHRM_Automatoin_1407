package com.framework.base;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.reports.ExtentReport;

public class BasePage {
	
	private WebDriver driver;
	protected ExtentTest test;
	
	public BasePage(WebDriver driver,ExtentTest test) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
		this.test=test;
	}
	
	public void validateField(String expected,String actual,String message) {
		if (expected.equals(actual)) {

			ExtentReport.logMessage(
					Status.PASS, message + "<b><font color=" + "green" + "><br> Expected value : "
							+ expected + ".<br> Actual value : " + actual + ".</font><b><br>",
					true);
		} else {

			ExtentReport.logMessage(
					Status.FAIL, message + "<b><font color=" + "red" + "><br> Expected value : "
							+ expected + ".<br> Actual value : " + actual + ".</font><b><br>",
					true);
			Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
		}
	}
	
	
	
	
	
	public void validateField(int expected,int actual,String message) {
		if (expected==actual) {

			ExtentReport.logMessage(Status.PASS,
					message+"<BR> Expected: " + expected + "<BR>Actual: " + actual,
					true);
		} else {

			ExtentReport.logMessage(Status.FAIL,
					message +"<BR> Expected: " + expected + "<BR>Actual: " + actual,
					true);
			Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
		}
	}
	
	public void validateField(List<String> expected,List<String> actual,String message) {
		if (expected.equals(actual)) {

			ExtentReport.logMessage(Status.PASS,
					message+"<BR> Expected: " + expected + "<BR>Actual: " + actual,
					true);
		} else {

			ExtentReport.logMessage(Status.FAIL,
					message +"<BR> Expected: " + expected + "<BR>Actual: " + actual,
					true);
			Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
		}
	}
	
	public void jsClickOnElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click();", element);
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	
	public void validateField(boolean expected,boolean actual,String message) {
		if (expected==actual) {

			ExtentReport.logMessage(Status.PASS,
					message+"<BR> Expected: " + expected + "<BR>Actual: " + actual,
					true);
		} else {

			ExtentReport.logMessage(Status.FAIL,
					message +"<BR> Expected: " + expected + "<BR>Actual: " + actual,
					true);
			Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
		}
	}
	
	public void validateField(float expected,float actual,String message) {
		if (expected==actual) {

			ExtentReport.logMessage(Status.PASS,
					message+"<BR> Expected: " + expected + "<BR>Actual: " + actual,
					true);
		} else {

			ExtentReport.logMessage(Status.FAIL,
					message +"<BR> Expected: " + expected + "<BR>Actual: " + actual,
					true);
			Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
		}
	}
	
	public void validateFieldContains(List<String> expected,List<String> actual,String message) {
		if (expected.contains(actual)) {

			ExtentReport.logMessage(Status.PASS,
					message+"<BR> Expected: " + expected + "<BR>Actual: " + actual,
					true);
		} else {

			ExtentReport.logMessage(Status.FAIL,
					message +"<BR> Expected: " + expected + "<BR>Actual: " + actual,
					true);
			Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
		}
	}

}
