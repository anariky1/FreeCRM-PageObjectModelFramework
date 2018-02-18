package com.crm.qa.pages;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class ContactsPage extends TestBase {
	
    TestUtil TestUtil = new TestUtil() ;
	
	@FindBy(xpath="//td[contains(text(),'Contacts')]")
	@CacheLookup
	public WebElement contactsLabel;
	
	@FindBy(xpath="//*[contains(text(),'Contact Information')]")
	public WebElement contactInformationLabel;
	
	@FindBy(name="title")
	public WebElement titleField;
	
	@FindBy(id="first_name")
	public WebElement fNameField;
	
	@FindBy(id="surname")
	public WebElement LNameField;
	
	@FindBy(name="client_lookup")
	public WebElement companyField;
	
	@FindBy(xpath="//input[@value='Save' and @class='button']")
	public WebElement saveButton;
	

	//initializing page objects
		public ContactsPage(){		
			PageFactory.initElements(driver, this);		
		}
		
		
		 public boolean verifyContactsLable(){
			   try{
				   contactsLabel.isDisplayed();				   
			   }catch(Exception e){
				   while(!contactsLabel.isDisplayed()){
						TestUtil.explicitWait("//td[contains(text(),'Contacts')]", 60);
						 contactsLabel.isDisplayed();
					  }
			   }
		 return contactsLabel.isDisplayed();

		 }
		 

        public void selectContactByName(String name){ 
    	    driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']"
    			+"//preceding-sibling::td[@class='datalistrow']/input[@name='contact_id']")).click();   	
         }
        
        
        public boolean verifyContactInformationLabel(){
			   try{
				   contactInformationLabel.isDisplayed();				   
			   }catch(Exception e){
				   while(!contactInformationLabel.isDisplayed()){
						TestUtil.explicitWait("//*[contains(text(),'Contact Information')]", 60);
						contactInformationLabel.isDisplayed();
					  }
			   }
		 return contactInformationLabel.isDisplayed();

		 }
        
        
         public boolean createNewContact(String title , String ftNmae , String ltName , String comp){       	 
        	 Select select = new Select(titleField);
        	 select.selectByVisibleText(title);
        	 fNameField.sendKeys(ftNmae);
        	 LNameField.sendKeys(ltName);
        	 companyField.sendKeys(comp);       	
        	 Actions act = new Actions(driver);
        	 act.moveToElement(saveButton).click().build().perform();
        	 if(verifyContactIsAdded(ftNmae,ltName)){
        		 return true;
        	 }
        	 return false;
         }
         
         public boolean verifyContactIsAdded(String fName ,String ltName){        	 
			 try{
        	 TestUtil.explicitWait("//td[contains(text(),'"+fName+"')]",3000);
			 }catch(Exception e){
			  TestUtil.explicitWait("//td[contains(text(),'"+fName+"')]",3000);
		      return driver.findElement(By.xpath("//td[contains(text(),'"+fName+"')]")).isDisplayed();
			 }
			return false;
	 
         }
        
        

}
