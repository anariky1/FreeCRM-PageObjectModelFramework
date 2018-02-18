package com.crm.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class LoginPage extends TestBase {

	@FindBy(name="username")
	public WebElement username;
	
	@FindBy(name="password")
	public WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")	
	public WebElement loginBtn1;
	
	@FindBy(xpath="//button[contains(text(),'Sign Up')]")
	public WebElement SignUpBtn;
	
	@FindBy(xpath="//div[@class='input-group-btn']/input[@value='Login']")
	public WebElement loginBtn;
	
	@FindBy(xpath="//img[contains(@class,'img-responsive')]")
	public WebElement crmLogo;
	
	//initializing page objects
	public LoginPage(){		
		PageFactory.initElements(driver, this);		
	}
	
	public String validateLoginPageTitle(){
		return driver.getTitle();		
	}
	
	public boolean validateCRMLogo(){
		return crmLogo.isDisplayed();		
	}
	
	public HomePage login(String un , String pwd) throws InterruptedException{
		username.sendKeys(un);
		password.sendKeys(pwd);		
		javascriptexecutor_click(loginBtn);		
		return new HomePage();
	}
	
	

}
