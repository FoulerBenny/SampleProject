package com.in.genericTestScenario;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.genericLibraries.BaseClass;
import com.genericLibraries.DateEvents;
import com.in.pageFactory.AddressPageFactory;
import com.in.pageFactory.CartPageIndia;
import com.in.pageFactory.CheckOutPageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CheckoutGeneric {
	WebDriver driver =null;
	ExtentTest startTest = null;
	private CartPageIndia cartPageIndia =null;
	private AddressPageFactory addressPage = null;
	private CheckOutPageFactory checkOutPageFactory = null;
	private String CheckOutTitle = "Checkout - LeMall.com";
	
	public CheckoutGeneric(WebDriver driver, ExtentTest startTest)
	{
		this.driver = driver;
		this.startTest = startTest;
	}
	
	public void checkOutCart(Map<String, String> dataMap) throws Exception
	{
		String PaymentMode = dataMap.get("PaymentMode");
		cartPageIndia = new CartPageIndia(driver, startTest);
		checkOutPageFactory = new CheckOutPageFactory(driver, startTest);
		AddressPageFactory addressPageFactory = new AddressPageFactory(driver, startTest);
		
		cartPageIndia.clickWebElement(cartPageIndia.CheckOutButton);
		checkOutPageFactory.waitForPageTitle(CheckOutTitle);
		if(addressPageFactory.getlistofAddressCheckOutpage()==0)
		{
			addressPageFactory.addNewAddress(dataMap);
		}
		validateCheckoutAddressDelivery();
		checkOutPageFactory.clickWebElement(checkOutPageFactory.UseThisAddressButton);
		
		checkOutPageFactory.waitForWebElement(checkOutPageFactory.ProceedToPaymentButton);
		checkOutPageFactory.clickWebElement(checkOutPageFactory.ProceedToPaymentButton);
		startTest.log(LogStatus.INFO, "ProceedToPaymentButton has be clicked","Successfull");
		String FinalAmount = checkOutPageFactory.getWebElementText(checkOutPageFactory.FinalAmount);
		System.out.println("FinalAmount: "+FinalAmount);
		if(Double.parseDouble(FinalAmount.split(". ")[1])>50000.00)
		{
			startTest.log(LogStatus.INFO, "COD not avaliable for amount more than 50000.00","Successfull");
			BaseClass.skipTestExecution("Test Completed");
		}
		checkOutPageFactory.clickWebElement(checkOutPageFactory.selectPaymentMode(PaymentMode));	
		startTest.log(LogStatus.INFO, "Payment Mode "+PaymentMode+" selected","Successfull");
	}
	
	public String makePayment() throws Exception
	{
		checkOutPageFactory.waitForWebElement(checkOutPageFactory.MakePayment);
		checkOutPageFactory.clickWebElement(checkOutPageFactory.MakePayment);
		String currentDateTime = DateEvents.getCurrentDataTime();
		//System.out.println(currentDateTime);
		currentDateTime= currentDateTime.substring(0, 16);
		//System.out.println(currentDateTime);
		return currentDateTime;
		//*******************************************************************************
			//User has made payment and currentDateTime Tracked to cancel the order
		//********************************************************************************
	}

	public void editAddressCheckOut(Map<String, String> dataMap) throws Exception
	{
		
		System.out.println("In edit address");
	
		addressPage = new AddressPageFactory(driver, startTest);
		if((addressPage.CheckOutAddressList.size())>0)
			{   
			addressPage.clickWebElement(addressPage.AddressName);
			addressPage.clickWebElement(addressPage.EditBtn);
			addressPage.addNewAddress(dataMap);
				
				//Thread.sleep(5000);
			}
			else
			{
				System.out.println("There is no address to be saved");
				BaseClass.skipTestExecution("There is no address to be edited");
			}
		

	}
	
	public void addNewAddressCheckOutPage(Map<String,String> dataMap) throws Exception
	{
		addressPage = new AddressPageFactory(driver, startTest);
		addressPage.addNewAddress2Existing(dataMap);
	}
	
	public void validateCheckoutAddressDelivery()
	{
		if(checkOutPageFactory.Undeliverable_products.size()>0)
		{
			List<WebElement> Undeliverable_product = checkOutPageFactory.Undeliverable_productlist.findElements(By.tagName("td"));
			String productlist=null;
			if(Undeliverable_product.size()>0)
			{
				for(WebElement e:Undeliverable_product)
				{
					if(productlist==null)
					{
						productlist=checkOutPageFactory.getWebElementText(e);
					}
					else
					{
						productlist=productlist+","+checkOutPageFactory.getWebElementText(e);
					}					
				}
				System.out.println("There are items in your shopping cart that are undeliverable to the selected address, please edit your shopping cart or select another address: "+productlist);
				BaseClass.skipTestExecution("There are items in your shopping cart that are undeliverable to the selected address, please edit your shopping cart or select another address: "+productlist);
			}
			
		}
	}
}
