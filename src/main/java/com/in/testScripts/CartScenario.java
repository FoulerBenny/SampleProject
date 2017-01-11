package com.in.testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.genericLibraries.BaseClass;
import com.in.genericTestScenario.CartGeneric;
import com.in.genericTestScenario.LoginGeneric;
import com.in.genericTestScenario.ProductGeneric;
import com.in.pageFactory.CartPageIndia;
import com.in.pageFactory.HomePageIndia;
import com.relevantcodes.extentreports.LogStatus;

public class CartScenario extends BaseClass{
	@Test(dataProvider = "DP_GetData",dataProviderClass = com.dataprovider.DataProviderGeneric.class,priority = 1)
	public void addProductToCart(Map<String,String> dataMap) throws Exception{
		System.out.println("Start the Test "+dataMap.get("TestScenario"));
		String testDescription = (dataMap.get("TestDescription"));		
		
		
		HomePageIndia homePageIndia = new HomePageIndia(driver, startTest);
		LoginGeneric loginGeneric = new LoginGeneric(driver,startTest);
		CartGeneric cartGeneric = new CartGeneric(driver,startTest);
		ProductGeneric productGeneric = new ProductGeneric(driver,startTest);
		
		startTest.log(LogStatus.INFO,testDescription ,"Successfull");
		//Click on Home Menu
		homePageIndia.clickWebElement(homePageIndia.HomeMenu);
		//Login as a valid user
		loginGeneric.validLoginGeneric( dataMap);
		//Clear cart
		cartGeneric.clearAllItemInCart();
		//select product and product Item
		productGeneric.selectProductFromMenu(dataMap);
		//Buy the product
		productGeneric.buyNowProduct(dataMap);
		//click view cart
		cartGeneric.viewCartInProductPage();
		//validate product in the cart
		cartGeneric.validateProductItemInCart(dataMap);
		System.out.println("End the Test "+dataMap.get("TestScenario"));
	}
	
	@Test(dataProvider = "DP_GetData",dataProviderClass = com.dataprovider.DataProviderGeneric.class,priority = 2)
	public void addProductToCartBeforeLogin(Map<String,String> dataMap) throws Exception{
		System.out.println("Start the Test "+dataMap.get("TestScenario"));
		String testDescription = dataMap.get("TestDescription");
		
		HomePageIndia homePageIndia = new HomePageIndia(driver, startTest);
		LoginGeneric loginGeneric = new LoginGeneric(driver,startTest);
		CartGeneric cartGeneric = new CartGeneric(driver,startTest);
		ProductGeneric productGeneric = new ProductGeneric(driver,startTest);
		
		startTest.log(LogStatus.INFO,testDescription ,"Successfull");
		//Click on Home Menu
		homePageIndia.clickWebElement(homePageIndia.HomeMenu);
		
		//select product and product Item
		productGeneric.selectProductFromMenu(dataMap);
		
		//Validate delivery estimation
		productGeneric.deliveryEstimationCheck(dataMap);
		//Buy the product
		productGeneric.buyNowProduct(dataMap);
		//Login as a valid user
		loginGeneric.validLoginGeneric( dataMap);
		//Clear cart
		cartGeneric.clearAllItemInCart();
		//select product and product Item
		productGeneric.selectProductFromMenu(dataMap);
		//Buy the product
		productGeneric.buyNowProduct(dataMap);		
		//click view cart
		cartGeneric.viewCartInProductPage();
		//validate product in the cart
		cartGeneric.validateProductItemInCart(dataMap);
		System.out.println("End the Test "+dataMap.get("TestScenario"));
	}
	
	
	
	@Test(dataProvider = "DP_GetData",dataProviderClass = com.dataprovider.DataProviderGeneric.class,priority = 1)
	public void validateCartToProductPageNavigation(Map<String,String> dataMap) throws Exception{
		System.out.println("Start the Test "+dataMap.get("TestScenario"));
		String testDescription = (dataMap.get("TestDescription"));		
		
		
		HomePageIndia homePageIndia = new HomePageIndia(driver, startTest);
		LoginGeneric loginGeneric = new LoginGeneric(driver,startTest);
		CartGeneric cartGeneric = new CartGeneric(driver,startTest);
		ProductGeneric productGeneric = new ProductGeneric(driver,startTest);
		CartPageIndia cartPageIndia = new CartPageIndia(driver,startTest);
		
		startTest.log(LogStatus.INFO,testDescription ,"Successfull");
		//Click on Home Menu
		homePageIndia.clickWebElement(homePageIndia.HomeMenu);
		//Login as a valid user
		loginGeneric.validLoginGeneric( dataMap);
		//Clear cart
		cartGeneric.clearAllItemInCart();
		//select product and product Item
		productGeneric.selectProductFromMenu(dataMap);
		//Buy the product
		productGeneric.buyNowProduct(dataMap);
		//click view cart
		cartGeneric.viewCartInProductPage();
		//validate product in the cart
		cartGeneric.validateProductItemInCart(dataMap);
		
		//Click on the productitem		
		cartPageIndia.clickWebElement(cartPageIndia.getCartProductitem(dataMap.get("ProductItem")));
		
		productGeneric.validateProductTitle(dataMap);
		
		System.out.println("End the Test "+dataMap.get("TestScenario"));
	}
	
	@Test(dataProvider = "DP_GetData",dataProviderClass = com.dataprovider.DataProviderGeneric.class,priority = 1)
	public void buyAccessoriesUsingAddCartIcon(Map<String,String> dataMap) throws Exception{
		System.out.println("Start the Test "+dataMap.get("TestScenario"));
		String testDescription = (dataMap.get("TestDescription"));		
		
		HomePageIndia homePageIndia = new HomePageIndia(driver, startTest);
		LoginGeneric loginGeneric = new LoginGeneric(driver,startTest);
		CartGeneric cartGeneric = new CartGeneric(driver,startTest);
		ProductGeneric productGeneric = new ProductGeneric(driver,startTest);
		
		startTest.log(LogStatus.INFO,testDescription ,"Successfull");
		//Click on Home Menu
		homePageIndia.clickWebElement(homePageIndia.HomeMenu);
		//Login as a valid user
		loginGeneric.validLoginGeneric( dataMap);
		//Clear cart
		cartGeneric.clearAllItemInCart();
		//select product and product Item
		productGeneric.selectProductFromMenu(dataMap);
		//validate product title
		productGeneric.validateProductTitle(dataMap);
		
		//Accessories Add to Cart using icon
		productGeneric.addToCartIconForAccessories(dataMap);
		
		//click view cart
		cartGeneric.viewCartInProductPage();
		
		cartGeneric.validateProductItemInCart(dataMap);
		
	}
}
