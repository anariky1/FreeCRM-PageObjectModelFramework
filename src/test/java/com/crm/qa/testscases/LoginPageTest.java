package com.crm.qa.testscases;
import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.HomePage;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageTest extends TestBase {
	
	LoginPage LoginPage;
    HomePage HomePage;
	
	public LoginPageTest(){
		super();
	}
	
	
	@BeforeMethod	
	public void setUp(){
		initialization();
		LoginPage = new LoginPage();
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		extentTest = extent.startTest("loginPageTitleTest");
		String title=LoginPage.validateLoginPageTitle();
	    Assert.assertEquals(title,"#1 Free CRM for Any Business: Online Customer Relationship Software" );		
	}
	
	@Test(priority=2)
		public void crmLogoImageTest(){
		extentTest = extent.startTest("crmLogoImageTest");
		boolean flag = LoginPage.validateCRMLogo();
		Assert.assertTrue(flag);
	} 

	@Test(priority=3)
	public void loginTest() throws InterruptedException{
		extentTest = extent.startTest("loginTest");
		HomePage=LoginPage.login(prop.getProperty("username"), prop.getProperty("password"));
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
