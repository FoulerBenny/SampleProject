package com.in.genericTestScenario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.genericLibraries.BaseClass;
import com.in.pageFactory.CartPageIndia;
import com.in.pageFactory.ProductPageIndia;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ProductGeneric {
	
	WebDriver driver =null;
	ExtentTest startTest = null;
	ProductPageIndia productPageIndia = null;
	CartPageIndia cartPageIndia = null;
	
	public ProductGeneric(WebDriver driver, ExtentTest startTest)
	{
		this.driver = driver;
		this.startTest = startTest;
	}
	
	public void selectProductFromMenu(Map<String, String> dataMap ) throws Exception
	{
		String ProductName = dataMap.get("Product");
		String ProductItem = dataMap.get("ProductItem");
		
		productPageIndia = new ProductPageIndia(driver, startTest);
		if(!ProductName.contains("Accessories"))
		{
			Actions action = new Actions(driver);
			WebElement Product = selectProduct(ProductName);				
			productPageIndia.mouseHoverWebElement(action, Product);
			startTest.log(LogStatus.INFO, "User has mouse hover the Product"+ ProductName,"Successfull");
			Thread.sleep(2000);
			WebElement productItem = productPageIndia.getProductItem(ProductItem);
			productPageIndia.mouseHoverWebElement(action, productItem);
			productPageIndia.clickMouseHoverWebElement(action,productItem);
			startTest.log(LogStatus.INFO, "User has clicked the ProductItem"+ ProductItem,"Successfull");
		}
		else if(ProductName.contains("Accessories"))
		{
			WebElement Product = selectProduct(ProductName);
			productPageIndia.clickWebElement(Product);
			//System.out.println(ProductItem);
			getAccessoriesSubItem(productPageIndia.AccessoriesList, ProductItem);
			
		}
		
	}
	
	public void setItemQuantity(String itemQuantity) throws Exception
	{
		if(itemQuantity==null)
		{
			itemQuantity="1";
		}
		if(itemQuantity==null||itemQuantity.equals("")||itemQuantity.equalsIgnoreCase("na"))
		{
			BaseClass.skipTestExecution("The maximum number of Quantity added for the select accessories is 2. Please re-enter the quantity");
		}
		productPageIndia.waitForWebElement(productPageIndia.QuantityDropBoxAccessories);
		productPageIndia.clickWebElement(productPageIndia.QuantityDropBoxAccessories);
		short itemqnty = Short.parseShort(itemQuantity);
		if(itemqnty>2)
		{
			BaseClass.skipTestExecution("The maximum number of Quantity added for the select accessories is 2. Please re-enter the quantity");
		}
		int qty_index= Integer.parseInt(itemQuantity)+1;
		productPageIndia.clickWebElement(driver.findElement(By.xpath("//div[@id='proAddCart']/div/ul/li["+qty_index+"]")));
	}
	
	private int scrollAccessoriesList(List<WebElement> AccessoriesList, String productItem) throws Exception
	{
		int countOfVisibleAccessories = AccessoriesList.size();
		boolean flag =true;
		List<WebElement> AccessoriesListReassigned =null;
		while(flag)
		{
			WebElement lastvisibleElement = driver.findElement(By.xpath("//div[@id='item_wrap']/div["+countOfVisibleAccessories+"]"));
			productPageIndia.waitForWebElement(lastvisibleElement);
			productPageIndia.scrollToElement(lastvisibleElement);
			Thread.sleep(5000);
			AccessoriesListReassigned  = driver.findElements(By.xpath("//div[@id='item_wrap']/div"));
			if(AccessoriesListReassigned.size()>countOfVisibleAccessories)
			{
				countOfVisibleAccessories=AccessoriesListReassigned.size();
			}
			else
			{
				countOfVisibleAccessories=AccessoriesListReassigned.size();
				flag=false;
			}
		}
		
		Thread.sleep(3000);
		return countOfVisibleAccessories;
	}
	
	private boolean getAccessoriesSubItem(List<WebElement> AccessoriesList, String productItem) throws Exception
	{
		int countOfVisibleAccessories = scrollAccessoriesList(AccessoriesList, productItem);
		
		for(int i=1;i<=countOfVisibleAccessories;i++)
		{
			//WebElement subitems = driver.findElement(By.xpath(".//div[@id='item_wrap']/div["+i+"]/div[2]/p[1]/a"));
			WebElement subitems = driver.findElement(By.xpath(".//div[@id='item_wrap']/div["+i+"]/div[2]/p[1]/a"));
			String text =productPageIndia.getWebElementText(subitems);
			//System.out.println(text);
			if(text!=null&&text.contains(productItem))
			{
				productPageIndia.scrollToElement(driver.findElement(By.xpath(".//div[@id='item_wrap']/div["+i+"]/div[2]/p[1]/a")));
				Thread.sleep(2000);
				productPageIndia.clickWebElement(driver.findElement(By.xpath(".//div[@id='item_wrap']/div["+i+"]/div[2]/p[1]/a")));
				break;
			}
			else if (text==null)
			{
				BaseClass.skipTestExecution("The system is unable to find the Product Item that you are trying to select. Please recheck the productItem");
			}
		}
		return true;
		
	}
	
	
	public void buyNowProduct(Map<String, String> dataMap) throws Exception
	{
		String ProductName = dataMap.get("Product");
		String ProductItem = dataMap.get("ProductItem");
		String StockStatus = dataMap.get("StockStatus");
		String accessoriesItemQuantity = dataMap.get("Quantity");
		productPageIndia = new ProductPageIndia(driver, startTest);
		cartPageIndia = new CartPageIndia(driver, startTest);
		
		if(ProductName.contains("Accessories"))
		{
			String accessoriesStockStatus = productPageIndia.getWebElementText(productPageIndia.AccessoriesStockStatus);
			if(accessoriesStockStatus.contains("In stock"))
			{
				deliveryEstimationCheck(dataMap);
				setItemQuantity(accessoriesItemQuantity);
				productPageIndia.clickWebElement(productPageIndia.AccessoriesAddToCart);
			}
			else
			{
				startTest.log(LogStatus.INFO, "The Product selected is out of stock: "+ProductItem,"Successfull");
				BaseClass.skipTestExecution("The Product selected is out of stock "+ProductItem );
			}
			
		}
		else
		{
			if(StockStatus.contains("InStock"))
			{
				Thread.sleep(2000);
				deliveryEstimationCheck(dataMap);
				productPageIndia.waitForWebElement(productPageIndia.GenericBuyNowButton);
				productPageIndia.clickWebElement(productPageIndia.GenericBuyNowButton);
				//cartPageIndia.waitForWebElement(cartPageIndia.ViewCart);
			}
			else
			{
				startTest.log(LogStatus.INFO, "The Product selected is out of stock: "+ProductItem,"Successfull");
				BaseClass.skipTestExecution("The Product selected is out of stock "+ProductItem );
			}
		}Thread.sleep(2000);
	}
	
	private WebElement selectProduct(String Product) throws Exception
	{
		WebElement SelectProduct = null;
		productPageIndia.waitForWebElement(driver.findElement(By.cssSelector("ul[class='nav_item']")));
			List<WebElement> li_tags = driver.findElement(By.cssSelector("ul[class='nav_item']")).findElements(By.tagName("li"));
			for(WebElement li : li_tags)
			{
				
				if(li.findElement(By.tagName("a")).getText().equalsIgnoreCase(Product))
				{
					System.out.println("Selected ProductItem to be added is "+li.findElement(By.tagName("a")).getText());
					SelectProduct = li;
					break;
					
				}
				
			}
			if(SelectProduct==null)
			{
				BaseClass.forceStopExecution("Check the product name that you have entered");
			}
		
		return SelectProduct;
		
	}
	
	//Checking delivery estimation using the Pincode
	
	public boolean deliveryEstimationCheck(Map<String,String> dataMap) throws Exception
	{	
		List<String> listOfPincode = new ArrayList<String>();
		String pincode = dataMap.get("Pincode");
		String changePincode = dataMap.get("ChangePincode");
		String product = dataMap.get("Product");
		String message=null;
		listOfPincode.add(pincode);
		listOfPincode.add(changePincode);
		
		for(int i=0;i<listOfPincode.size();i++)
		{
			String code = listOfPincode.get(i);
			
			
			if(code==null||code.length()!=6||code.matches("[0-9]+") == false)
			{
				System.out.println("The pincode selected should be 6 digit or not a numberic. Currently set as  "+code+". ReEnter a valid pincode");
				BaseClass.skipTestExecution("The pincode selected should be 6 digit. Currently set as  "+code+". ReEnter a valid pincode");
			}			
				message = enterPincode(code);
				//System.out.println(message.split("?")[1]);
				if(message.contains("Not available for delivery, please change your pincode"))
				{
					if(changePincode==null||changePincode.equalsIgnoreCase("na")||changePincode.equalsIgnoreCase(""))
					{
						System.out.println(message);
						BaseClass.skipTestExecution("The product: "+product+" selected cannot be delivered to the select location: "+code);
					}
					else
					{
						productPageIndia.clickWebElement(productPageIndia.ChangePin);
					}
									
				}
				else
				{
					break;
				}				
		}
		System.out.println(message);
		startTest.log(LogStatus.INFO, message,"Successfull");
		startTest.log(LogStatus.INFO, "User has insert the pincode successfull and gets the estimation date "+pincode,"Successfull");
		return true;
		
		
	}
	
	private String enterPincode(String pincode) throws Exception
	{
		productPageIndia.waitForWebElement(productPageIndia.Enterpincode);
		productPageIndia.setTextbox(productPageIndia.Enterpincode,pincode);
		productPageIndia.waitForWebElement(productPageIndia.pincheck);
		Thread.sleep(2000);
		productPageIndia.clickWebElement(productPageIndia.pincheck);
		productPageIndia.waitForWebElement(productPageIndia.pincodeMessage);
		Thread.sleep(2000);
		return productPageIndia.getWebElementText(productPageIndia.pincodeMessage);
	}
	
	
	public void validateProductTitle(Map<String,String> dataMap) throws Exception
	{
		String productItem = dataMap.get("ProductItem");
		String product = dataMap.get("Product");
		if(product.equalsIgnoreCase("accessories"))
		{
			productPageIndia.waitForWebElement(productPageIndia.AccessoriesTitle);
			String webElementText = productPageIndia.getWebElementText(productPageIndia.AccessoriesTitle);
			if(!webElementText.contains(productItem))
			{
				System.out.println("System failed to navigate to the product page : "+productItem);
				BaseClass.forceStopExecution("System failed to navigate to the product page : "+productItem);
			}
		}
		else
		{
			productPageIndia.waitForWebElement(productPageIndia.ProductTitle);
			String webElementText = productPageIndia.getWebElementText(productPageIndia.ProductTitle);
			if(!webElementText.contains(productItem))
			{
				System.out.println("System failed to navigate to the product page : "+productItem);
				BaseClass.forceStopExecution("System failed to navigate to the product page : "+productItem);
			}
		}
		
		startTest.log(LogStatus.INFO, "User is navigated to Product page : "+productItem,"Successfull");
	}
	
	public void addToCartIconForAccessories(Map<String,String> dataMap) throws Exception
	{
		//Get stock status of the accessories
		String accessoriesStockStatus = productPageIndia.getWebElementText(productPageIndia.AccessoriesStockStatus);
		
		String productItem = dataMap.get("ProductItem");
		
				
		driver.navigate().back();
		int countOfVisibleAccessories = scrollAccessoriesList(productPageIndia.AccessoriesList, productItem);
		
		for(int i=1;i<=countOfVisibleAccessories;i++)
		{
			//WebElement subitems = driver.findElement(By.xpath(".//div[@id='item_wrap']/div["+i+"]/div[2]/p[1]/a"));
			WebElement subitems = driver.findElement(By.xpath(".//div[@id='item_wrap']/div["+i+"]/div[2]/p[1]/a"));
			String text =productPageIndia.getWebElementText(subitems);
			if(text.contains(productItem))
			{
				productPageIndia.scrollToElement(driver.findElement(By.xpath(".//div[@id='item_wrap']/div["+i+"]/div[2]/p[1]/a")));
				Thread.sleep(2000);
				String AddToCartIconclassName = productPageIndia.getAttributeText(driver.findElement(By.xpath(".//div[@id='item_wrap']/div["+i+"]/div[1]/a")),"class");
				if (AddToCartIconclassName.contains("addToCart add_cart_btn")&&accessoriesStockStatus.equalsIgnoreCase("In stock"))
				{
					Actions action = new Actions(driver);
					productPageIndia.mouseHoverWebElement(action, driver.findElement(By.xpath(".//div[@id='item_wrap']/div["+i+"]/div[2]/p[1]/a")));
					productPageIndia.clickWebElement(driver.findElement(By.xpath(".//div[@id='item_wrap']/div["+i+"]/div[1]/a")));
				}
				else if(AddToCartIconclassName.contains("add_cart_btn btn_dis")&&accessoriesStockStatus.equalsIgnoreCase("Out of stock"))
				{
					BaseClass.skipTestExecution("The product item is out of stock. User cannot add this productitem to cart:  "+productItem);
				}
				
				else if(AddToCartIconclassName.contains("addToCart add_cart_btn")&&accessoriesStockStatus.equalsIgnoreCase("Out of stock"))
				{
					BaseClass.forceStopExecution("User shouldn't be able to click Add to cart icon when product item is out of stock: "+productItem);
				}
				else
				{
					BaseClass.skipTestExecution("Unabled to click on the Add to Cart Icon in the Accessories Page for the product item "+productItem);
				}
				
				break;
			}
		}
		
	}

}
