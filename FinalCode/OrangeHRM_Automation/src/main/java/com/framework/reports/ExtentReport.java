
package com.framework.reports;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.framework.base.BaseTest;

public class ExtentReport {

	static ExtentReports extent;	
	//private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public void initializeReport() {

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		String dateformat = formatter.format(date);

		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/report/testReport.html");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Browser", "chrome");
		htmlReporter.config().setDocumentTitle("Extent Report Demo");
		htmlReporter.config().setReportName("Test Report");

	}

	/*
	 * public synchronized ExtentTest createExtentTest(String testMethodName, String
	 * category) { return
	 * extent.createTest(testMethodName).assignCategory(category); ExtentTest
	 * extentTest = extent.createTest(testMethodName); test.set(extentTest); return
	 * extentTest; }
	 */
	
	
	public synchronized ExtentTest createExtentTest(String testCaseName,String categoryName) {
		return extent.createTest(testCaseName).assignCategory(categoryName);
		//test.set(extentTest);	
		//return extentTest;
	}

	/*
	 * public static void assignCategory(String categoryName) {
	 * test.get().assignCategory(categoryName); }
	 */

	public void flushTests() {
		extent.flush();
	}

	public static synchronized void logMessage(Status status, String desc, boolean screenshot) {

		ITestResult currentTestResult = Reporter.getCurrentTestResult();

		ExtentTest test = (ExtentTest)currentTestResult.getAttribute("extent.test");
	
		
		if (screenshot) {

			Media image = null;
			try {
				WebDriver driver = (WebDriver)currentTestResult.getAttribute("web.driver");
				image = MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(driver,

						currentTestResult.getMethod().getMethodName())).build();
			} catch (IOException e) {

			}
			test.log(status, desc, image);
		} else {
			test.log(status, desc);
		}

	}

	public static String captureScreenShot(WebDriver driver, String methodName) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(
				System.getProperty("user.dir") + "/images/" + methodName + "_" + System.currentTimeMillis() + ".png");
		String screenshotPath = dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, dest);
		return screenshotPath;
	}

}
