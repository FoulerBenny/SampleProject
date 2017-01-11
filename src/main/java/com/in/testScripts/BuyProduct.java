package com.in.testScripts;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.genericLibraries.BaseClass;
import com.in.commonHelpers.SplitOrderMethod;
import com.in.genericTestScenario.CartGeneric;
import com.in.genericTestScenario.CheckoutGeneric;
import com.in.genericTestScenario.LoginGeneric;
import com.in.genericTestScenario.ProductGeneric;
import com.in.pageFactory.HomePageIndia;
import com.relevantcodes.extentreports.LogStatus;

public class BuyProduct extends BaseClass {
	
	@Test(dataProvider = "DP_GetData",dataProviderClass = com.dataprovider.DataProviderGeneric.class,priority = 1)
	public void buyAnyProduct(Map<String,String> dataMap) throws Exception{
	
		System.out.println("Start the Test "+dataMap.get("TestScenario"));
		String testDescription = (dataMap.get("TestDescription"));	
		
		HomePageIndia homePageIndia = new HomePageIndia(driver, startTest);
		LoginGeneric loginGeneric = new LoginGeneric(driver,startTest);
		CartGeneric cartGeneric = new CartGeneric(driver,startTest);
		ProductGeneric productGeneric = new ProductGeneric(driver,startTest);
		CheckoutGeneric checkoutGeneric = new CheckoutGeneric(driver,startTest);	
		
			
		startTest.log(LogStatus.INFO,testDescription ,"Successfull");
		//Click on Home Menu
		homePageIndia.clickWebElement(homePageIndia.HomeMenu);
		//Login as a valid user
		loginGeneric.validLoginGeneric( dataMap);
		//Clear cart
		cartGeneric.clearAllItemInCart();
		//select product and product Item
		productGeneric.selectProductFromMenu(dataMap);		
		//Delivery estimation
		//productGeneric.deliveryEstimationCheck(dataMap);
		//Buy product
		productGeneric.buyNowProduct(dataMap);
		//click view cart
		cartGeneric.viewCartInProductPage();
		//if productitem exist in cart
		cartGeneric.validateProductItemInCart(dataMap);
		// validate product details in cart
		cartGeneric.validateCartProductInfo(dataMap);
		checkoutGeneric.checkOutCart(dataMap);
		
	}
	
	
	//@Test(dataProvider = "DP_GetData",dataProviderClass = com.dataprovider.DataProviderGeneric.class,priority = 2)
	public void buyMultipleProduct(Map<String,String> dataMap) throws Exception{
		String testDescription = (dataMap.get("TestDescription"));		
		String productInfo = dataMap.get("Product");
		
		HomePageIndia homePageIndia = new HomePageIndia(driver, startTest);
		LoginGeneric loginGeneric = new LoginGeneric(driver,startTest);
		CartGeneric cartGeneric = new CartGeneric(driver,startTest);
		CheckoutGeneric checkoutGeneric = new CheckoutGeneric(driver,startTest);	
		
		startTest.log(LogStatus.INFO,testDescription ,"Successfull");
		//Click on Home Menu
		homePageIndia.clickWebElement(homePageIndia.HomeMenu);
		//Login as a valid user
		loginGeneric.validLoginGeneric(dataMap);
		//Clear cart
		cartGeneric.clearAllItemInCart();
		
		SplitOrderMethod productsplit= new SplitOrderMethod();
		List<Map<String, String>> splitProductDetails = productsplit.getSplitProductDetails(productInfo);
		cartGeneric.setMultipleProductItems(splitProductDetails);		
		checkoutGeneric.checkOutCart(dataMap);
	
	}
	
	@Test(dataProvider = "DP_GetData",dataProviderClass = com.dataprovider.DataProviderGeneric.class,priority = 3)
	public void buyAccessoriesUsingAddCartIcon(Map<String,String> dataMap) throws Exception{
		System.out.println("Start the Test "+dataMap.get("TestScenario"));
		String testDescription = (dataMap.get("TestDescription"));		
		
		HomePageIndia homePageIndia = new HomePageIndia(driver, startTest);
		LoginGeneric loginGeneric = new LoginGeneric(driver,startTest);
		CartGeneric cartGeneric = new CartGeneric(driver,startTest);
		ProductGeneric productGeneric = new ProductGeneric(driver,startTest);
		CheckoutGeneric checkoutGeneric = new CheckoutGeneric(driver,startTest);	
		
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
		
		// validate product details in cart
		cartGeneric.validateCartProductInfo(dataMap);
		checkoutGeneric.checkOutCart(dataMap);
		
	}

}
