package com.orangehrm.tests;

import java.util.List;

import org.testng.annotations.Test;

import com.framework.base.BaseTest;
import com.orangehrm.pages.AddEmployeePage;
import com.orangehrm.pages.LoginPage;

public class AddEmployeeTest extends BaseTest {

	@Test
	public void verifyAddEmployee() {

		LoginPage loginPage = new LoginPage(getDriver(), getExtentTest());
		loginPage.login("Admin", "Admin");
		AddEmployeePage addEmployeePage = new AddEmployeePage(getDriver(), getExtentTest());
		addEmployeePage.navigateToAddEmployee();
		String expectedId = addEmployeePage.addEmplopyeeDetails();
		addEmployeePage.navigateToEmployeeList();
		addEmployeePage.verifyEmployeeRecord(expectedId);

	}

	@Test
	public void verifyEditEmployee() {
		LoginPage loginPage = new LoginPage(getDriver(), getExtentTest());
		loginPage.login("Admin", "Admin");
		AddEmployeePage addEmployeePage = new AddEmployeePage(getDriver(), getExtentTest());
		addEmployeePage.navigateToAddEmployee();
		String expectedId = addEmployeePage.addEmplopyeeDetails();
		addEmployeePage.navigateToEmployeeList();
		addEmployeePage.verifyEmployeeRecord(expectedId);
		List<String> expectedEmpData = addEmployeePage.editEmployee(expectedId, getData("FnameEdited"),
				getData("LnameEdited"));
		addEmployeePage.navigateToEmployeeList();
		List<String> actualEmpData = addEmployeePage.getUpdatedEmpDetails(expectedId);
		addEmployeePage.verifyEmployeeRecordUpdated(expectedEmpData, actualEmpData);
	}

	@Test
	public void verifyDeleteEmployee() {

		LoginPage loginPage = new LoginPage(getDriver(), getExtentTest());
		loginPage.login("Admin", "Admin");
		AddEmployeePage addEmployeePage = new AddEmployeePage(getDriver(), getExtentTest());
		addEmployeePage.navigateToAddEmployee();
		String expectedId = addEmployeePage.addEmplopyeeDetails();
		addEmployeePage.navigateToEmployeeList();
		addEmployeePage.verifyEmployeeRecord(expectedId);
		addEmployeePage.clickOnEmpId(expectedId);

	}

}
