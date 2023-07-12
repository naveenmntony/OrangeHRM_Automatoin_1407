package com.orangehrm.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.base.BasePage;
import com.framework.reports.ExtentReport;

public class AddEmployeePage extends BasePage {

	@FindBy(id = "menu_pim_addEmployee")
	WebElement link_AddEmployee;

	@FindBy(id = "firstName")
	WebElement textBox_FirstName;

	@FindBy(id = "lastName")
	WebElement textBox_LastName;

	@FindBy(id = "btnSave")
	WebElement button_Save;

	@FindBy(id = "employeeId")
	WebElement textBox_EmployeeId;

	@FindBy(id = "menu_pim_viewEmployeeList")
	WebElement link_EmployeeList;

	@FindBy(xpath = "//table[@id='resultTable']/tbody/tr/td[2]")
	List<WebElement> list_empIds;

	@FindBy(id = "btnSave")
	private WebElement btnEdit;

	@FindBy(id = "personal_txtEmpFirstName")
	private WebElement txtEmpFirstName;

	@FindBy(id = "personal_txtEmpLastName")
	private WebElement txtEmpLastName;
	
	
	
	

	public AddEmployeePage(WebDriver driver, ExtentTest test) {
		super(driver, test);
	}

	public void navigateToAddEmployee() {
		link_AddEmployee.click();
	}

	public String addEmplopyeeDetails() {

		textBox_FirstName.sendKeys("Vishnu");
		textBox_LastName.sendKeys("Reddy");

		String expectedId = textBox_EmployeeId.getAttribute("value");

		button_Save.click();
		return expectedId;
	}

	

	public void verifyEmployeeRecord(String expectedId) {

		boolean status = false;
		String actualId = null;

		for (WebElement element : list_empIds) {
			actualId = element.getText();
			if (expectedId.equals(actualId)) {
				status = true;
				break;
			}
		}

		validateField(true, status, "Verify Employee Record");
	}

	public void clickOnEmpId(String empId) {
		getDriver().findElement(By.xpath("//a[text()='" + empId + "']")).click();

	}

	public List<String> editEmployee(String expectedEmpId, String FnameEdited, String LnameEdited) {
		List<String> expectedEmpDetails = new ArrayList<String>();
		expectedEmpDetails.add(FnameEdited);
		expectedEmpDetails.add(LnameEdited);
		getDriver().findElement(By.xpath("//table[@id='resultTable']//td[2]/a[text()='" + expectedEmpId + "']"))
				.click();
		btnEdit.click();
		ExtentReport.logMessage(Status.INFO, "Click On Edit Button", false);
		txtEmpFirstName.clear();
		txtEmpFirstName.sendKeys(FnameEdited);
		ExtentReport.logMessage(Status.INFO, "Clear Text Box , Enter First Name As: " + FnameEdited, false);
		txtEmpLastName.clear();
		txtEmpLastName.sendKeys(LnameEdited);
		ExtentReport.logMessage(Status.INFO, "Clear Text Box , Enter Last Name As: " + LnameEdited, false);
		btnEdit.click();
		ExtentReport.logMessage(Status.INFO, "Click On Save Button", false);
		return expectedEmpDetails;
	}
	
	public List<String> getUpdatedEmpDetails(String expectedEmpId) {
		List<WebElement> empDetails = getDriver()
				.findElements(By.xpath("//table[@id='resultTable']/descendant::a[text()='" + expectedEmpId
						+ "']/parent::td/following-sibling::td"));
		String actualFirstName = empDetails.get(0).getText(); 
		String actualLastName = empDetails.get(1).getText();
		List<String> updatedEmpDetails = new ArrayList<String>();
		updatedEmpDetails.add(actualFirstName);
		updatedEmpDetails.add(actualLastName);
		return updatedEmpDetails;

	}
	
	public void navigateToEmployeeList() {
		link_EmployeeList.click();
		ExtentReport.logMessage(Status.INFO, "Navigate To Employee List Page",false);
		
	}
	
	public void verifyEmployeeRecordUpdated(List<String> expected, List<String> actual) {
		validateField(expected, actual, "Verify Employee detailed after updating");
	}

}
