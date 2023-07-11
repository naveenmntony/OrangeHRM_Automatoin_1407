
package com.framework.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtils {

	public static void main(String args[]) {

		String path = System.getProperty("user.dir") + "\\reports\\Testreports2.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);

		ExtentReports extent = new ExtentReports();

		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Browser", "chrome");

		reporter.config().setDocumentTitle("Orange HRM Report");
		reporter.config().setReportName("OrangeHRM execution");

		extent.attachReporter(reporter);

		ExtentTest test = extent.createTest("verifyLoginWithUsernameBlank");

		test.assignCategory("Login");

		test.log(Status.PASS, "Launch Application");

		test.log(Status.PASS, "Leave Username Blank");
		test.log(Status.PASS, "Enter password");
		test.log(Status.PASS, "Click on Login");
		test.log(Status.FAIL, "Verify Error Message");

		ExtentTest test1 = extent.createTest("VerifyAddUser");

		test1.assignCategory("Admin");

		test1.log(Status.PASS, "Launch Application");

		test1.log(Status.PASS, "ENter username");
		test1.log(Status.PASS, "Enter password");
		test1.log(Status.PASS, "Click on Login");
		test1.log(Status.FAIL, "Verify Error Message");

		extent.flush();

	}
}
