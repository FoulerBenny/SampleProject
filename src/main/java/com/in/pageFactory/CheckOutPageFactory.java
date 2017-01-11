package com.in.pageFactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.eventHandler.ObjectEventHandler;
import com.genericLibraries.BaseClass;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CheckOutPageFactory extends ObjectEventHandler {
	private String checkoutTitle ="Checkout - LeMall.com";
	public CheckOutPageFactory(WebDriver driver, ExtentTest startTest){
		super(driver,startTest);
		PageFactory.initElements(driver, this);		
	}
	
	@FindBy(xpath="//a[@id='useAddress']")
	public WebElement UseThisAddressButton;
	
	@FindBy(xpath="//span[@id='orderSubmitSpan']")
	public WebElement ProceedToPaymentButton;
	
	@FindBy(xpath="//div[@id='addressModule']/div/div/ul/li")
	public List<WebElement> AddressList;
	
	
	@FindBy(xpath="//div[@id='listBox']/ul/li")
	public List<WebElement> PaymentMode;
	
	
	@FindBy(xpath="//li[@id='subBtn']")
	public WebElement MakePayment;
	
	@FindBy(css="div[class='india_pay_price']>h3>b")
	public WebElement FinalAmount;
	
	@FindBy(xpath="//div[@id='orderInfo']/span")
	public WebElement OrderID;
	
	
	@FindBy(css="div.undeliverable_products>p>strong")
	public List<WebElement> Undeliverable_products;
	
	@FindBy(css="ul#unavailableData")
	public WebElement Undeliverable_productlist;
	
	
	
	
	
	
	
	public WebElement selectPaymentMode(String mode)
	{
		for(WebElement li:PaymentMode)
		{
			if(li.findElement(By.tagName("p")).getText().equalsIgnoreCase(mode))
			{
				return li;
			}
		}return null;
	}
	public WebElement selectAddressExistingFromList()
	{
		
		WebElement li=null;
		for(WebElement element :AddressList)
		{
			if(element.getAttribute("isdefault").equals("0")||element.getAttribute("isdefault").equals("1"))
			{
				li= element;
				break;
			}
			
		}return li;
	}
	
	public void valideCheckOutPage() throws Exception
	{
		waitForPageTitle(checkoutTitle);
		
		if(!driver.getTitle().equalsIgnoreCase(checkoutTitle))
		{
			BaseClass.forceStopExecution("Failed to swtich into the new Tab- Cart");
		}
		startTest.log(LogStatus.INFO, "User has clicked on Check out button","Successfull");
	}
	
	public String getOrderID() throws Exception
	{
		waitForWebElement(OrderID);
		String Orderno = getWebElementText(OrderID);
		startTest.log(LogStatus.PASS, "Order ID has been generated. " +Orderno,"Successfull");
		System.out.println("THe order id is "+Orderno);	
		return Orderno.split(": ")[1];
	}

}
