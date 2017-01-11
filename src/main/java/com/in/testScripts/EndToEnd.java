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

public class EndToEnd extends BaseClass {
	
	@Test(dataProvider = "DP_GetData",dataProviderClass = com.dataprovider.DataProviderGeneric.class)
	public void end2EndOrderCancel(Map<String,String> dataMap) throws Exception
	{
		HomePageIndia homePageIndia = new HomePageIndia(driver, startTest);
		LoginGeneric loginGeneric = new LoginGeneric(driver,startTest);
		CartGeneric cartGeneric = new CartGeneric(driver,startTest);
		ProductGeneric productGeneric = new ProductGeneric(driver,startTest);
		CheckoutGeneric checkoutGeneric = new CheckoutGeneric(driver, startTest);
		MyOrderGeneric myOrderGeneric = new MyOrderGeneric(driver, startTest);
		
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
		//Check out Order
		checkoutGeneric.checkOutCart(dataMap);
		//Make payment
		String orderDateTime = checkoutGeneric.makePayment();
		//Logout
		loginGeneric.logoutUser();
		//Login back as user for cancellation
		loginGeneric.validLoginGeneric( dataMap);
		//CancelOrder
		myOrderGeneric.cancelOrder(orderDateTime);
		
	}

}
