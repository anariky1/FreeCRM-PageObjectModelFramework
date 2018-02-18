package com.crm.qa.testscases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class ContactsPageTest extends TestBase {
	
	LoginPage LoginPage;
    HomePage HomePage;
    TestUtil TestUtil;
    ContactsPage ContactsPage;
    
    String sheetname="Contacts";
	
	public ContactsPageTest(){
		super();
	}
	
	@BeforeMethod	
	public void setUp() throws InterruptedException{
		initialization();
		TestUtil = new TestUtil();
		LoginPage = new LoginPage();
		ContactsPage = new ContactsPage();
		HomePage=LoginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		TestUtil.switchToFrame("mainpanel");
		ContactsPage=HomePage.clickonContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContactsLabel(){
		extentTest = extent.startTest("verifyContactsLabelTest");
		Assert.assertTrue(ContactsPage.verifyContactsLable(),"Contacts label is missing on the page");		
	}
	
	@Test(priority=2)
	public void selectSingleContactTest(){
		extentTest = extent.startTest("selectSingleContactTest");
		ContactsPage.verifyContactsLable();
		ContactsPage.selectContactByName("testing test");
	}
	
	@Test(priority=3)
	public void selectMultipleContactTest(){
		extentTest = extent.startTest("selectMultipleContactTest");
		ContactsPage.verifyContactsLable();
		ContactsPage.selectContactByName("testing test");
		ContactsPage.selectContactByName("ananth ananth");
	}
	
	@Test(priority=4 , dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title , String firstName,String lastname,String company){
		extentTest = extent.startTest("validateCreateNewContact");
		HomePage.clickOnNewContactsLink();
		ContactsPage.verifyContactInformationLabel();
		//ContactsPage.createNewContact(title, firstName, lastname , company);
		//Assert.assertTrue(ContactsPage.verifyContactIsAdded(firstName ,lastname ),firstName+ " is not added ");
		Assert.assertTrue(ContactsPage.createNewContact(title, firstName, lastname , company),firstName+ " is not added ");
	}
	
	
	
	@DataProvider
	public Object[][] getCRMTestData(){
		Object data[][]=TestUtil.getTestData(sheetname);
		return data;
	}
	
	
	
	
	
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			
			String screenshotPath = getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}
		
		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		driver.quit();
	}


}
