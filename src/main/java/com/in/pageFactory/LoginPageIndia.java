package com.in.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.eventHandler.ObjectEventHandler;
import com.genericLibraries.BaseClass;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageIndia extends ObjectEventHandler {
	private String LoginTitle= "User center";
	
	
	public LoginPageIndia(WebDriver driver, ExtentTest startTest){
		super(driver,startTest);
		PageFactory.initElements(driver, this);		
	}
	
	
	/*UserAccount details in text format*/
	@FindBy(xpath="//li[@id = 'loginMess']/span[2]")
	public WebElement UserAccountName;
	
	/*Check for login link*/
	@FindBy(linkText="Log in")
	public WebElement LoginLink;
	
	/*UserName Textbox*/
	@FindBy(xpath="//input[@name='loginname']")
	public WebElement LoginUname;
	
	/*Password Textbox*/
	@FindBy(xpath="//input[@id='password']")
	public WebElement LoginPassword;
	
	/*Login button*/
	@FindBy(xpath="//a[@id='login-btn']")
	public WebElement LoginButton;
	
	/*Log Out  link*/
	@FindBy(xpath="//a[@id='logout']")
	public WebElement LogoutLink;
	
	/*Method to validate the login page*/
	public boolean validLoginPage() throws Exception
	{
		
		waitForPageTitle(LoginTitle);
		if(!driver.getTitle().contains(LoginTitle))
		{
			BaseClass.forceStopExecution("Login page has loaded incorrectly please retry or check the application");
			return false;
		}
		return true;
	}
	
	/*Method to validate login page , enter user details and click on the login button*/
	public boolean loginUser(String UserName, String Password) throws Exception
	{		
		if(validLoginPage()==true)
		{
			clickWebElement(LoginLink);
			setTextbox(LoginUname, UserName);
			setTextbox(LoginPassword, Password);
			clickWebElement(LoginButton);
			startTest.log(LogStatus.INFO, "The user entered his credentials successfully. User: "+UserName,"Successfull");
			return true;
		}
		return false;
	}

}
