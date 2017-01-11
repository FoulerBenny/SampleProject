package com.in.genericTestScenario;


import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.genericLibraries.BaseClass;
import com.genericLibraries.BrowserHandler;
import com.in.pageFactory.CartPageIndia;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CartGeneric {
	WebDriver driver =null;
	ExtentTest startTest = null;
	private CartPageIndia cartPageIndia =null;
	private String CartPageTitle = "My cart - LeMall.com";
	private BrowserHandler browserHandler = null;
	
	public CartGeneric(WebDriver driver, ExtentTest startTest)
	{
		this.driver = driver;
		this.startTest = startTest;
	}
	
	public void clearAllItemInCart() throws Exception
	{
		cartPageIndia = new CartPageIndia(driver, startTest);
		cartPageIndia.waitForWebElement(cartPageIndia.CartButton);
		cartPageIndia.clickWebElement(cartPageIndia.CartButton);
		if(cartPageIndia.GotoShopping.isDisplayed()!=true)
		{
			cartPageIndia.deleteAllCartItems();
		}		
		cartPageIndia.waitForWebElement(cartPageIndia.GotoShopping);
		cartPageIndia.clickWebElement(cartPageIndia.GotoShopping);
	}
	
	public void viewCartInProductPage() throws Exception
	{
		cartPageIndia = new CartPageIndia(driver, startTest);
		browserHandler = new BrowserHandler();		
		cartPageIndia.waitForWebElement(cartPageIndia.ViewCart);
		cartPageIndia.clickWebElement(cartPageIndia.ViewCart);
		browserHandler.switchBrowserWindow(driver, CartPageTitle);
		startTest.log(LogStatus.INFO, "User has switched to the Cart Tab on his browser","Successfull");
		
	}
	
	public boolean validateProductItemInCart(Map<String, String> dataMap) throws Exception
	{
		String productName = dataMap.get("ProductItem");
		cartPageIndia = new CartPageIndia(driver, startTest);
		WebElement product = driver.findElement(By.xpath("//table[@class='cart_list']/tbody/tr/td[3]/a[contains(text(),'"+productName+"')]"));
		System.out.println(cartPageIndia.getWebElementText(product) +" has been added to the cart");
		if(cartPageIndia.getWebElementText(product).contains(productName))
		{
			startTest.log(LogStatus.INFO, "The product :"+productName+" is added to the cart","Successfull");
			return true;
		}
		else
		{
			BaseClass.forceStopExecution("Unable to add the product "+productName+" to the cart");
			return false;
		}
		
	}
	
	public void clickCheckOutButton() throws Exception
	{
		cartPageIndia.clickWebElement(cartPageIndia.CheckOutButton);
	}
	
	public boolean validateCartProductInfo(Map<String, String> dataMap) throws Exception
	{
		
		String price = dataMap.get("Price");
		String productItem = dataMap.get("ProductItem");
		cartPageIndia = new CartPageIndia(driver, startTest);
		 String cartPrice = cartPageIndia.getWebElementText(cartPageIndia.cartPrice(productItem));
		 System.out.println(cartPrice);
			 if(cartPrice.contains(price))
			{
			 startTest.log(LogStatus.INFO, "The Product  "+productItem+ "has expected price of " + cartPrice,"Successfull");
			 double p = Double.parseDouble(price);
			String count = cartPageIndia.getTextBoxData(cartPageIndia.qtyCount(productItem));
			 double c = Double.parseDouble(count);
			 System.out.println(c);
			String subTotal = Double.toString(p*c);
			String st = cartPageIndia.getWebElementText(cartPageIndia.productSubToTal(productItem));
			if(st.contains(subTotal))
				{
				 	startTest.log(LogStatus.INFO, "The Product  "+productItem+ "has expected subtoatal " + subTotal,"Successfull");
				 	return true;
				}
			else
				{
					BaseClass.forceStopExecution(" Subtotal in cart is different for "+productItem );
					return false;
				}
			}
			else
			{
				BaseClass.forceStopExecution(" Price is different for "+productItem + "in cart page");
				return false;
			
			}
			 
	}
	
	public void setMultipleProductItems(List<Map<String, String>> splitProductDetails) throws Exception
	{
		CartPageIndia cartPageIndia = new CartPageIndia(driver, startTest);
		ProductGeneric productGeneric = new ProductGeneric(driver,startTest);
		for(Map<String, String> m :splitProductDetails)
		{   
			System.out.print(m.get("Product")+"\t"+m.get("ProductItem") + m.get("StockStatus")+"\t"+  m.get("Quantity"));
			//select product and product Item
			productGeneric.selectProductFromMenu(m);
			//Buy the product
			productGeneric.buyNowProduct(m);
			cartPageIndia = new CartPageIndia(driver, startTest);
			cartPageIndia.clickWebElement(cartPageIndia.ContinueShopping);
			Thread.sleep(2000);
			cartPageIndia.waitForWebElement(cartPageIndia.Logo);
			cartPageIndia.clickWebElement(cartPageIndia.Logo);
		}
		cartPageIndia.waitForWebElement(cartPageIndia.CartButton);
		cartPageIndia.clickWebElement(cartPageIndia.CartButton);
	}
	
	
	
	
	

}
