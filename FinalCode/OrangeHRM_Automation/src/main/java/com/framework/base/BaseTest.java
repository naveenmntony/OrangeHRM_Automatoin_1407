package com.framework.base;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.dataaccess.ExcelUtil;
import com.framework.dataaccess.PropertyUtil;
import com.framework.reports.ExtentReport;

public class BaseTest {
	
	
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	
	ExtentReport  extentReport = new ExtentReport();
	//public static ExtentTest test;
	
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	
	private static ThreadLocal<String> testName = new ThreadLocal<String>();
	
	@BeforeSuite
	public void intialize() throws IOException {
		PropertyUtil.initProperty();
		ExcelUtil.intializeExcel();
		ExcelUtil.readAllDataFromExcel();
		extentReport.initializeReport();
	}

	@BeforeMethod
	public void launchBrowser(Method method, ITestContext context, ITestResult result) {
		WebDriver driver = null;

		setMethodName(method.getName());
		String moduleName = context.getName();

		String browserName = PropertyUtil.readPropertyValue("browsername");
		String path = System.getProperty("user.dir");
		
		String runMode = PropertyUtil.readPropertyValue("runMode");
		if(runMode.equalsIgnoreCase("Local")) {
			if (browserName.equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver", path + "\\src\\test\\resources\\drivers\\chromedriver.exe");

				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.edge.driver", path + "\\src\\test\\resources\\drivers\\msedgedriver.exe");

				driver = new EdgeDriver();
			}
		}else if(runMode.equalsIgnoreCase("Grid")) {
			String hubUrl = PropertyUtil.readPropertyValue("hubUrl");			
			if (browserName.equalsIgnoreCase("chrome")) {
			DesiredCapabilities capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(Platform.WINDOWS);
			try {
				driver = new RemoteWebDriver(new URL(hubUrl), capability);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}

		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		setWebDriver(driver);
		test.set(extentReport.createExtentTest(getMethod(), moduleName));
		result.setAttribute("web.driver", getDriver());
		result.setAttribute("extent.test", getExtentTest());

	}

	@BeforeMethod(dependsOnMethods = "launchBrowser")
	public void launchApplication()  {		
		String url = PropertyUtil.readPropertyValue("url");
		getDriver().get(url);
		//ExtentReport.logMessage(Status.INFO, "Enter URL: " +url , false);
	}

	@AfterMethod
	public void closeBrowser() {
		WebDriver driver = getDriver();
		if(null!=driver) {
			driver.quit();
		}
		
	}

	public WebDriver getDriver() {
		return driver.get();
	}
	
	public void setWebDriver(WebDriver driver) {
		 BaseTest.driver.set(driver);
	}
	
	public String getData(String colName) {
		return ExcelUtil.readData(getMethod(),colName);
	}
	
	@AfterSuite
	public void tearDown() {
		extentReport.flushTests();
	}
	
	

	public ExtentTest getExtentTest() {
		return test.get();
	}
	
	public void setMethodName(String methodName) {
		BaseTest.testName.set(methodName);

	}

	public String getMethod() {
		return testName.get();
	}
}
