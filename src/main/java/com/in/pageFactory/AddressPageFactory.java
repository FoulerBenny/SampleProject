package com.in.pageFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eventHandler.ObjectEventHandler;
import com.genericLibraries.BaseClass;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class AddressPageFactory extends ObjectEventHandler {
	
	//private String CartPageTitle= "My cart - LeMall.com";
	
	
	public AddressPageFactory(WebDriver driver, ExtentTest startTest){
		super(driver,startTest);
		PageFactory.initElements(driver, this);	}
	
	/*Address list in My Order page*/
	@FindBy(xpath="//div[@id='userAddressList']/ul/li")
	public List<WebElement> AddresslistMyOrderPage;
	
	
	
	/*Address list in check out page*/
	@FindBy(xpath="//div[@id='addressModule']/div/div/ul/li")
	public List<WebElement> AddresslistCheckOutPage;
	
	/*
	Page:MyOrder_Page
	Type:Add address_Payment
	*/
	@FindBy(xpath="//input[@id='postcode']")
	public WebElement zipcodeCheckOutPage;
	
	/*
	Page:MyOrder_Page
	Type:Add address
	*/
	@FindBy(css="input[id='detailAddr']")
	public  WebElement addressLine;
	
	/*
	Page:MyOrder_Page
	Type:Add address
	*/
	@FindBy(xpath="//div[@id='addressCount']/div/a/span")
	public  WebElement AddAddressButton;
	
	/*
	Page:MyOrder_Page
	Type:Add address
	*/
	@FindBy(css="input[id='receiver']")
	public  WebElement fullName;
	
	/*
	Page:MyOrder_Page
	Type:Add address
	*/
	@FindBy(xpath="//input[@id='post']")
	public  WebElement zipcode;

	
	/*
	Page:MyOrder_Page
	Type:Add address
	*/
	@FindBy(xpath="//div[@id='addressModule']/div/div/ul/li[1]/div[3]/dl/dt")
	public WebElement AddressName;
	
	/*
	Page:MyOrder_Page
	Type:Add address
	*/
	@FindBy(xpath="//div[@id='addressModule']/div/div/ul/li[1]/div[4]/span[1]/a")
	public WebElement EditBtn;


	
	/*
	Page:MyOrder_Page
	Type:Add address
	*/
	@FindBy(xpath="//input[@id='mobile']")
	public  WebElement mobileNumber;
	
	
	/*
	Page:MyOrder_Page
	Type:Add address
	*/
	@FindBy(xpath="//input[@id='email']")
	public  WebElement email;
	
	/*
	Page:MyOrder_Page
	Type:Add address
	*/
	@FindBy(xpath="//input[@id='addressName']")
	public  WebElement addressAlias;
	
	@FindBy(xpath="//div[@id='wjPop-body']/div/div/a") 
	public  WebElement addressConfirm;
	
	/*
	Page:MyOrder_Page
	Type:Add address
	*/
	@FindBy(xpath=".//*[@id='addrSubmit']/div/span")
	public  WebElement addressSave;
	
	/*
	Page:MyOrder_Page
	Type:Add address
	*/
	@FindBy(xpath=".//*[@id='addrSubmit']")
	public  WebElement addressSavePayment;
	
	
	 
	/*
	Page:MyOrder_Page
	Type:Add address
	*/
	@FindBy(xpath=".//*[@id='addressCount']/div/div[1]/span[1]")
	public  WebElement addressCount;
	
	
	/*
	Page:MyOrder_Page
	Type:Add address
	*/
	@FindBy(xpath="//div[@id='addressModule']/div/div/ul")
	public  List<WebElement> AddressList;
	
	/*
	Page:MyOrder_Page
	Type:Add address
	*/
	@FindBy(xpath="//a[@id='useAddress']")
	public  WebElement useAddress;
	
	/*
	Page:MyOrder_Page
	Type:Add address
	*/
	
	@FindAll({@FindBy(xpath="//div[@id='addressModule']/div")})
	public  List<WebElement> CheckOutAddressList;
	
	
	/*
	Page:MyOrder_Page
	Type:AddressExceedMessage close icon
	*/
	@FindBy(css="a#wjAlert-close")
	public  WebElement AddressExceedLimits;
	
	/*
	Page:MyOrder_Page
	Type:AddressExceedMessage 40 
	*/
	@FindBy(css="div#wjAlert-content")
	public  List<WebElement> AddressExceedMessage;
	
	/*
	Page:MyOrder_Page
	Type:Add address
	*/
	@FindBy(xpath="//a[@class='delete']")
	public WebElement DeleteBtn;

	/*
	Page:MyOrder_Page
	Type:Add address
	*/
	@FindBy(xpath="//div[@class='msg']")
	public WebElement AddressMessage;
	
	/*
	Page:MyOrder_Page
	Type:Add address
	*/
	@FindBy(xpath="//*[@id='wjConfirm-ok']")
	public  WebElement DeleteConfirm;
	

	
/*******************************************************************************************************************/	
	public int getlistofAddressCheckOutpage()
	{
		return AddresslistCheckOutPage.size();
	}
	
	public int getConfirm()
	{
		return driver.findElements(By.xpath(".//*[@id='wjConfirm']/div")).size();
		
	}
	public int addNewAddress(Map<String, String> dataMap) throws Exception
	{
		int addresscount = getlistofAddressCheckOutpage();
		waitForWebElement(fullName);
		setTextbox(fullName,dataMap.get("FullName") );
		waitForWebElement(zipcodeCheckOutPage);
		setTextbox(zipcodeCheckOutPage,dataMap.get("Pincode"));
		setTextbox(addressLine,dataMap.get("AddressLine") );
		Thread.sleep(3000);

		// Switch to the pop -up
					 Set<String> handles = driver.getWindowHandles(); // get all window handles
					Iterator<String> iterator = handles.iterator();
					
					if(getConfirm()==1)
					{
					String subWindowHandler = null;
					while (iterator.hasNext())
						{
				    	subWindowHandler = iterator.next();
						}
					driver.switchTo().window(subWindowHandler);
					waitForWebElement(addressConfirm);
					clickWebElement(addressConfirm);
					}
					
		
		waitForWebElement(addressLine);
		setTextbox(addressLine,dataMap.get("AddressLine") );
		waitForWebElement(mobileNumber);
		setTextbox(mobileNumber,dataMap.get(" Mobilenumber"));
		waitForWebElement(email);
		setTextbox(email, dataMap.get("Email"));
		waitForWebElement(addressAlias);
		setTextbox(addressAlias,dataMap.get("AddressAlias"));
		Thread.sleep(5000);
		waitForWebElement(addressSavePayment);
		scrollToElement(addressSavePayment);
		clickWebElement(addressSavePayment);
		startTest.log(LogStatus.INFO, "A new Address added successfully","Successfull");	
		Thread.sleep(2000);
		driver.navigate().refresh();		
		return addresscount;
	}
	
	private boolean validateNewAddressInsertion(int addresscount) throws Exception
	{
		if(AddresslistCheckOutPage.size()<=addresscount)
		{
			BaseClass.forceStopExecution("The system failed to add a new address");
		}
		return true;
	}
	
	public void addNewAddress2Existing(Map<String, String> dataMap) throws Exception
	{
		int checkAddressCount = getlistofAddressCheckOutpage();
		if(checkAddressCount>0)
		{
			startTest.log(LogStatus.INFO, "User has existing address to his address list so adding a new address to the list","Successfull");
			
			WebElement findElement = driver.findElement(By.xpath("//ul[@id='addresscheck']/li["+checkAddressCount+"]"));
			
			waitForWebElement(findElement);
			scrollToElement(findElement);
			clickWebElement(findElement);
			Thread.sleep(2000);
			if(AddressExceedMessage.size()>0)
			{
				BaseClass.skipTestExecution("You can create up to 40 addresses!");
			}
		}
					
		 int addresscount = addNewAddress(dataMap);
		 if(validateNewAddressInsertion(addresscount)==true);
		 {
			 startTest.log(LogStatus.INFO, "User has successfully entered a new address","Successfull");
		 }
		 
		
	}
	
	
	
	public void addNewAddressMyOrders(Map<String, String> dataMap) throws Exception
	{
		waitForWebElement(fullName);
		setTextbox(fullName,dataMap.get("FullName") );
		waitForWebElement(zipcode);
		setTextbox(zipcode,dataMap.get("Pincode"));
		//waitForWebElement(addressConfirm);
		setTextbox(addressLine,dataMap.get("AddressLine") );
		Thread.sleep(3000);

		// Switch to the pop -up
					Set<String> handles = driver.getWindowHandles(); // get all window handles
					Iterator<String> iterator = handles.iterator();
					if(getConfirm()==1)
					{
					String subWindowHandler = null;
					while (iterator.hasNext())
						{
				    	subWindowHandler = iterator.next();
						}
					driver.switchTo().window(subWindowHandler);
					waitForWebElement(addressConfirm);
					clickWebElement(addressConfirm);
					}
					
		
		waitForWebElement(addressLine);
		setTextbox(addressLine,dataMap.get("AddressLine") );
		waitForWebElement(mobileNumber);
		setTextbox(mobileNumber,dataMap.get(" Mobilenumber"));
		waitForWebElement(email);
		setTextbox(email, dataMap.get("Email"));
		waitForWebElement(addressAlias);
		setTextbox(addressAlias,dataMap.get("AddressAlias"));
		Thread.sleep(5000);
		waitForWebElement(addressSave);
		clickWebElement(addressSave);
		startTest.log(LogStatus.INFO, "A new Address added successfully","Successfull");	
		Thread.sleep(2000);
		driver.navigate().refresh();		
	}
	
	
	

}
