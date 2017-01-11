package com.in.genericTestScenario;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.in.pageFactory.AddressPageFactory;
import com.in.pageFactory.MyOrderPageIndia;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MyOrderGeneric {
	WebDriver driver =null;
	ExtentTest startTest = null;
	private MyOrderPageIndia myOrderPageIndia =null;
	private AddressPageFactory addressPageFactory =null;
	
	public MyOrderGeneric(WebDriver driver, ExtentTest startTest)
	{
		this.driver = driver;
		this.startTest = startTest;
	}
	
	public void cancelOrder(String orderDateTime) throws Exception
	{
		myOrderPageIndia = new MyOrderPageIndia(driver, startTest);
		myOrderPageIndia.waitForWebElement(myOrderPageIndia.MyOrderLink);
		myOrderPageIndia.clickWebElement(myOrderPageIndia.MyOrderLink);
		myOrderPageIndia.waitForPageTitle(myOrderPageIndia.MyOrderTitle);
		List<String> OrderID = myOrderPageIndia.cancelOrderIDs(orderDateTime);
		System.out.println(OrderID.get(0));
		
	}
	
	public void addNewAddressMyOrder(Map<String, String> dataMap) throws Exception
	{
		myOrderPageIndia = new MyOrderPageIndia(driver, startTest);
		addressPageFactory = new AddressPageFactory(driver, startTest);
		myOrderPageIndia.waitForWebElement(myOrderPageIndia.MyOrderLink);
		myOrderPageIndia.clickWebElement(myOrderPageIndia.MyOrderLink);
		myOrderPageIndia.waitForPageTitle(myOrderPageIndia.MyOrderTitle);
		myOrderPageIndia.waitForWebElement(myOrderPageIndia.AddressBookLink);
		myOrderPageIndia.clickWebElement(myOrderPageIndia.AddressBookLink);
		addressPageFactory.waitForWebElement(addressPageFactory.AddAddressButton);
		addressPageFactory.clickWebElement(addressPageFactory.AddAddressButton);
		addressPageFactory.addNewAddressMyOrders(dataMap);
		Thread.sleep(2000);
		
	}
	public void deleteAddressMyOrder(Map<String, String> dataMap) throws Exception
	{
		myOrderPageIndia = new MyOrderPageIndia(driver, startTest);
		addressPageFactory = new AddressPageFactory(driver, startTest);
		myOrderPageIndia.waitForWebElement(myOrderPageIndia.MyOrderLink);
		myOrderPageIndia.clickWebElement(myOrderPageIndia.MyOrderLink);
		myOrderPageIndia.waitForPageTitle(myOrderPageIndia.MyOrderTitle);
		myOrderPageIndia.waitForWebElement(myOrderPageIndia.AddressBookLink);
		myOrderPageIndia.clickWebElement(myOrderPageIndia.AddressBookLink);
		String addressMessage = addressPageFactory.AddressMessage.getText();
		String NoAddressMessage = "You don't have any addresses. You can add a maximum of 40 addresses.";
		
		while(addressMessage.equals(NoAddressMessage)==false)
		{
		addressPageFactory.waitForWebElement(addressPageFactory.DeleteBtn);
		addressPageFactory.clickWebElement(addressPageFactory.DeleteBtn);
		addressPageFactory.waitForWebElement(addressPageFactory.DeleteConfirm);
		addressPageFactory.clickWebElement(addressPageFactory.DeleteConfirm);
		Thread.sleep(2000);
		addressMessage = addressPageFactory.AddressMessage.getText();
		}
		
		if(addressMessage.equals(NoAddressMessage))
		{
			startTest.log(LogStatus.INFO, "All addresses have been deleted ","Successfull");	
		}
	}

}
