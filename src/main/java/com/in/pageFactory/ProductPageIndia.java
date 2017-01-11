package com.in.pageFactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.eventHandler.ObjectEventHandler;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ProductPageIndia extends ObjectEventHandler{
	private String ProductPageTitle= "- LeMall.com";
	WebDriverWait wait = new WebDriverWait(driver,10);
	public ProductPageIndia(WebDriver driver, ExtentTest startTest){
		super(driver,startTest);
		PageFactory.initElements(driver, this);	
		
	}
	
	
	/*This is the Quantity dropbox for Accessories*/
	@FindBy(xpath="//div[@id='proAddCart']/div[1]/div/span")
	public WebElement QuantityDropBoxAccessories;
	
	/*Accessories Stock Status*/
	@FindBy(xpath="//a[@id='p_ids_black']")
	public WebElement AccessoriesAddToCart;
	

	
	/*List of Accessories*/
	@FindBy(xpath="//div[@id='item_wrap']/div")
	public List<WebElement> AccessoriesList;
	
	
	@FindBy(xpath="//a[text()='BUY NOW']")
	public WebElement GenericBuyNowButton;
	
	/*@FindBy(xpath="//a[contains(text(),'Buy Now']")
	public WebElement AccessoriesBuyNowIcon;*/
	
	@FindBy(xpath="//a[text()='Buy now']")
	public WebElement OtherBuynowButton;
	
	
	/*Checking the delivery estimation Time*/
	@FindBy(css="input#enterPinCode")
	public  WebElement Enterpincode;
	
	@FindBy(css="a#pinCheck")
	public WebElement pincheck;
	
	@FindBy(css="div#changePinCode>a")
	public WebElement ChangePin;
	
	
	//Invalid delivery pincode message
	@FindBy(xpath=".//div[@id='deliveryEstimate']")
	public WebElement pincodeMessage;
	
	//Product title
	@FindBy(css="div#piao>div>a")
	public WebElement ProductTitle;
	
	
	//AccessoriesTitle
	@FindBy(css="div.pro_info>div>div:nth-child(2)>div>p")
	public WebElement AccessoriesTitle;
	
	//AccessoriesStockStatus
	@FindBy(css="span#stockdesc")
	public WebElement AccessoriesStockStatus;
	

	
	
	public WebElement getAccessoriesAddToCartIcon() throws Exception
	{
		waitForWebElement(driver.findElement(By.id("//a[contains]/../../../div/a")));
		return driver.findElement(By.id("//a[contains]/../../../div/a"));
	}
	
	/*Method to validate the Product page*/
	public boolean validateProductPage()
	{
		wait.until(ExpectedConditions.titleContains(ProductPageTitle));
		if(!driver.getTitle().contains(ProductPageTitle))
		{
			return false;
		}
		return true;
	}
	
	
	
	public WebElement getBuynowButtonOthersProduct()
	{
		List<WebElement> element = driver.findElements(By.xpath("//div[@id='le2Rosegold']/../a"));
		System.out.println(element.size());
		WebElement BuyNow = null;
		for(WebElement e:element)
		{
			if(e.getText().equals("Buy now"))
			{
				System.out.println(e.getText());
				BuyNow=e;
				break;
			}
		}return BuyNow;
	}
	
	
	public void clickBuyNowButton(String product, String productItem,String StockStatus) throws Exception
	{
		if((product.contains("TV"))||(product.contains("Audio")))
		{
			if(StockStatus.contains("InStock"))
				{
				
					waitForWebElement(GenericBuyNowButton);
					clickWebElement(GenericBuyNowButton);
				}
			else
			{
				startTest.log(LogStatus.INFO, "The Product selected is out of stock"+productItem,"Successfull");
			}
		}
		
		if((product.contains("Phones")))
		{
			if(StockStatus.contains("InStock"))
				{
					
				}
			else
			{
				startTest.log(LogStatus.INFO, "The Product selected is out of stock"+productItem,"Successfull");
			}
		}
	}
	
	public WebElement getProductItem(String item) throws Exception
	{
		
		WebElement element = driver.findElement(By.xpath("//p[contains(text(),'"+item+"')]"));
		waitForWebElement(element);
		return  element;
	}
	

}
