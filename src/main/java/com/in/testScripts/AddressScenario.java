package com.in.testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.genericLibraries.BaseClass;
import com.in.genericTestScenario.CartGeneric;
import com.in.genericTestScenario.CheckoutGeneric;
import com.in.genericTestScenario.LoginGeneric;
import com.in.genericTestScenario.MyOrderGeneric;
import com.in.genericTestScenario.ProductGeneric;
import com.in.pageFactory.HomePageIndia;
import com.relevantcodes.extentreports.LogStatus;

public class AddressScenario extends BaseClass {
	
	@Test(dataProvider = "DP_GetData",dataProviderClass = com.dataprovider.DataProviderGeneric.class,priority = 2)
	public void editAddressInCheckOutPage(Map<String,String> dataMap) throws Exception
	{
		System.out.println("Start the Test "+dataMap.get("TestScenario"));
		String testDescription = (dataMap.get("TestDescription"));
		HomePageIndia homePageIndia = new HomePageIndia(driver, startTest);
		LoginGeneric loginGeneric = new LoginGeneric(driver,startTest);
		CartGeneric cartGeneric = new CartGeneric(driver,startTest);
		ProductGeneric productGeneric = new ProductGeneric(driver,startTest);
		CheckoutGeneric checkoutGeneric = new CheckoutGeneric(driver, startTest);
		//MyOrderGeneric myOrderGeneric = new MyOrderGeneric(driver, startTest);
		startTest.log(LogStatus.INFO,testDescription ,"Successfull");
		homePageIndia.clickWebElement(homePageIndia.HomeMenu);
		
		//Login as valid user
		loginGeneric.validLoginGeneric( dataMap);
		//Clear cart
		cartGeneric.clearAllItemInCart();
		//select product
		productGeneric.selectProductFromMenu(dataMap);
		//Add product to cart
		productGeneric.buyNowProduct(dataMap);
		//View Cart
		cartGeneric.viewCartInProductPage();
		
		cartGeneric.clickCheckOutButton();
		
		checkoutGeneric.editAddressCheckOut(dataMap);
		
		System.out.println("End the Test "+dataMap.get("TestScenario"));
	}
	
	
	@Test(dataProvider = "DP_GetData",dataProviderClass = com.dataprovider.DataProviderGeneric.class,priority = 1)
	public void addNewAddressInCheckOutPage(Map<String,String> dataMap) throws Exception
	{
		System.out.println("Start the Test "+dataMap.get("TestScenario"));
		String testDescription = (dataMap.get("TestDescription"));
		HomePageIndia homePageIndia = new HomePageIndia(driver, startTest);
		LoginGeneric loginGeneric = new LoginGeneric(driver,startTest);
		CartGeneric cartGeneric = new CartGeneric(driver,startTest);
		ProductGeneric productGeneric = new ProductGeneric(driver,startTest);
		CheckoutGeneric checkoutGeneric = new CheckoutGeneric(driver, startTest);
		//MyOrderGeneric myOrderGeneric = new MyOrderGeneric(driver, startTest);
		
		startTest.log(LogStatus.INFO,testDescription ,"Successfull");
		homePageIndia.clickWebElement(homePageIndia.HomeMenu);
		
		//Login as valid user
		loginGeneric.validLoginGeneric( dataMap);
		//Clear cart
		cartGeneric.clearAllItemInCart();
		//select product
		productGeneric.selectProductFromMenu(dataMap);
		//Add product to cart
		productGeneric.buyNowProduct(dataMap);
		//View Cart
		cartGeneric.viewCartInProductPage();
		
		cartGeneric.clickCheckOutButton();
		
		checkoutGeneric.addNewAddressCheckOutPage(dataMap);
		System.out.println("End the Test "+dataMap.get("TestScenario"));
	}
	
	@Test(dataProvider = "DP_GetData",dataProviderClass = com.dataprovider.DataProviderGeneric.class,priority = 3)
	public void addNewAddressInMyOrders(Map<String,String> dataMap) throws Exception
	{
		System.out.println("Start the Test "+dataMap.get("TestScenario"));
		String testDescription = (dataMap.get("TestDescription"));
		HomePageIndia homePageIndia = new HomePageIndia(driver, startTest);
		LoginGeneric loginGeneric = new LoginGeneric(driver,startTest);		
		MyOrderGeneric myOrderGeneric = new MyOrderGeneric(driver, startTest);
		
		startTest.log(LogStatus.INFO,testDescription ,"Successfull");
		homePageIndia.clickWebElement(homePageIndia.HomeMenu);
		
		//Login as valid user
		loginGeneric.validLoginGeneric( dataMap);
		myOrderGeneric.addNewAddressMyOrder(dataMap);
		System.out.println("End the Test "+dataMap.get("TestScenario"));
	}
	
	@Test(dataProvider = "DP_GetData",dataProviderClass = com.dataprovider.DataProviderGeneric.class,priority = 4)
	public void deleteAddressInMyOrders(Map<String,String> dataMap) throws Exception
	{
		System.out.println("Start the Test "+dataMap.get("TestScenario"));
		String testDescription = (dataMap.get("TestDescription"));
		HomePageIndia homePageIndia = new HomePageIndia(driver, startTest);
		LoginGeneric loginGeneric = new LoginGeneric(driver,startTest);		
		MyOrderGeneric myOrderGeneric = new MyOrderGeneric(driver, startTest);
		
		startTest.log(LogStatus.INFO,testDescription ,"Successfull");
		homePageIndia.clickWebElement(homePageIndia.HomeMenu);
		
		//Login as valid user
		loginGeneric.validLoginGeneric( dataMap);
		myOrderGeneric.deleteAddressMyOrder(dataMap);
		System.out.println("End the Test "+dataMap.get("TestScenario"));
	}

}
