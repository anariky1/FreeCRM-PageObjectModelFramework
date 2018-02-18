package com.crm.qa.testscases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import com.crm.qa.pages.ContactsPage;
import com.relevantcodes.extentreports.LogStatus;

public class HomePageTest extends TestBase {
	
	LoginPage LoginPage;
    HomePage HomePage;
    TestUtil TestUtil;
    ContactsPage ContactsPage;
	
	public HomePageTest(){
		super();
	}
	
	@BeforeMethod	
	public void setUp() throws InterruptedException{
		initialization();
		TestUtil = new TestUtil();
		LoginPage = new LoginPage();
		ContactsPage = new ContactsPage();
		HomePage=LoginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyhomePageTitleTest(){
		extentTest = extent.startTest("verifyhomePageTitleTest");
		String homePagetitle=HomePage.verifyHomePageTitle();
		Assert.assertEquals(homePagetitle, "CRMPRO", "Home page title not matched");
	}
	
	@Test(priority=2)
	public void verifyUserNameTest(){
		extentTest = extent.startTest("verifyUserNameTest");
		TestUtil.switchToFrame("mainpanel");
		Assert.assertTrue(HomePage.verifyCorrectUserName());
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest(){
		extentTest = extent.startTest("verifyContactsLinkTest");
		TestUtil.switchToFrame("mainpanel");
		ContactsPage=HomePage.clickonContactsLink();		
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
