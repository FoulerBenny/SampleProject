package com.in.genericTestScenario;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.genericLibraries.BaseClass;
import com.in.pageFactory.HomePageIndia;
import com.in.pageFactory.LoginPageIndia;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginGeneric {
	WebDriver driver =null;
	HomePageIndia homePageIndia = null;
	String HomeTitle= "LeMall Official  Website: Buy Tomorrow's  Devices Today";
	ExtentTest startTest = null;
	private LoginPageIndia loginpageindia =null;
	
	
	public LoginGeneric(WebDriver driver, ExtentTest startTest)
	{
		this.driver = driver;
		this.startTest = startTest;
	}
	
	public void validLoginGeneric(Map<String, String> dataMap ) throws Exception
	{
		String UserName = dataMap.get("UserName");
		String Password = dataMap.get("Password");
		String WebAccountID = dataMap.get("AccountID");
		String CurrentTitle = null;
		
		CurrentTitle = driver.getTitle();
		System.out.println("HomePage : "+CurrentTitle);		
		loginpageindia = new LoginPageIndia(driver,startTest);
		loginpageindia.waitForWebElement(loginpageindia.LoginLink);
		loginpageindia.clickWebElement(loginpageindia.LoginLink);
		loginpageindia.setTextbox(loginpageindia.LoginUname, UserName);
		loginpageindia.setTextbox(loginpageindia.LoginPassword, Password);
		loginpageindia.clickWebElement(loginpageindia.LoginButton);
		startTest.log(LogStatus.INFO, "The user entered his credentials successfully. User: "+UserName,"Successfull");
		loginpageindia.waitForPageTitle(CurrentTitle);
		String useraccount = loginpageindia.getWebElementText(loginpageindia.UserAccountName);
		if(useraccount.contains(WebAccountID))
		{
			System.out.println("The user has logged in successfully "+useraccount);
			startTest.log(LogStatus.INFO, "The user has logged in successfully "+useraccount,"Successfull");
			
		}
		else
		{
			BaseClass.forceStopExecution("Terminate execution, Incorrect User details when validating the user");
		}
		Thread.sleep(2000);
	}
	
	public void logoutUser() throws Exception
	{
		loginpageindia = new LoginPageIndia(driver,startTest);
		loginpageindia.waitForWebElement(loginpageindia.LogoutLink);
		loginpageindia.clickWebElement(loginpageindia.LogoutLink);
		/*loginpageindia.waitForWebElement(loginpageindia.LoginLink);
		System.out.println(driver.getTitle());
		loginpageindia.waitForPageTitle(HomeTitle);*/
		Thread.sleep(5000);
		System.out.println(driver.getTitle());
	}

}
