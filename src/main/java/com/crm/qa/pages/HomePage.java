package com.crm.qa.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {
	
	@FindBy(xpath="//td[contains(text(),'User: ananth kumar')]")
	public WebElement userNameLabel;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	public WebElement contactsLink;
	
	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	public WebElement newContactLink;
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	public WebElement dealsLink;
	
	@FindBy(xpath="//a[contains(text(),'Tasks')]")
	public WebElement taskLink;
	
	
	//initializing page objects
	public HomePage(){		
		PageFactory.initElements(driver, this);		
	}
	
	
	public String verifyHomePageTitle(){
		return driver.getTitle();
	}
	
	
	public boolean verifyCorrectUserName(){
		return userNameLabel.isDisplayed();
	}
	
	public ContactsPage clickonContactsLink(){
		 Actions actions = new Actions(driver);
		 actions.moveToElement(contactsLink).click().build().perform();
		//contactsLink.click();
		return new ContactsPage();
	}
	
	public DealsPage clickonDealsLink(){
		contactsLink.click();
		return new DealsPage();
	}
	
	public TasksPage clickonTaskLink(){
		taskLink.click();
		return new TasksPage();
	}
	
	public void clickOnNewContactsLink(){
		Actions actions = new Actions(driver);
		actions.moveToElement(contactsLink).build().perform();
		newContactLink.click();
		
	}
	
	

}
