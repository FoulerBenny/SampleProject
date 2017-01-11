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

public class HomePageIndia extends ObjectEventHandler{
	private String HomePageTitle= "LeMall Official Website: Buy Tomorrow's Devices Today";
	
	public HomePageIndia(WebDriver driver, ExtentTest startTest){
		super(driver,startTest);
		PageFactory.initElements(driver, this);		
	}
	
	
	
	/*Home Menu*/
	@FindBy(xpath="//ul[@class='nav_item']/li[1]/a")
	public WebElement HomeMenu;
	
	/*Login link*/
	@FindBy(id="login")
	public WebElement HomeLoginLink;
	
	/*UserAccount details in text format*/
	@FindBy(xpath="//li[@id = 'loginMess']/span[2]")
	public WebElement UserAccountName;
	
	/*Home Page cart button*/
	@FindBy(xpath="//a[@class='cart_icon'][text()='Cart']")
	public WebElement HomeCartButton;
	
	/*Home Page Logo button*/
	@FindBy(xpath="//a[@href = '/in/index.html?ref=hp-head-home'][@class='logo']")
	public WebElement HomePageLogo;
	
	
	
	
	
	
	/*Method to validate the Home page*/
	public boolean validateHomePage() throws Exception
	{
		
		waitForPageTitle(HomePageTitle);
		if(!driver.getTitle().contains(HomePageTitle))
		{
			BaseClass.forceStopExecution("Home page has loaded incorrectly please retry or check the application");
			return false;
		}
		return true;
	}
	
	
	
	
	/*Method to select the product from the Home Page menu*/
	public WebElement selectProduct(String Product) throws Exception
	{
		WebElement SelectProduct = null;
			waitForWebElement(driver.findElement(By.cssSelector("ul[class='nav_item']")));
			List<WebElement> li_tags = driver.findElement(By.cssSelector("ul[class='nav_item']")).findElements(By.tagName("li"));
			for(WebElement li : li_tags)
			{
				
				if(li.findElement(By.tagName("a")).getText().equalsIgnoreCase(Product))
				{
					System.out.println(li.findElement(By.tagName("a")).getText());
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
	
	/*Method to validate user account info after logging in , data picked from the excel*/
	public boolean validateUserAccount(String WebAccountID) throws Exception
	{
			validateHomePage();
			String useraccount = getWebElementText(UserAccountName);
			if(useraccount.contains(WebAccountID))
			{
				System.out.println("The user has logged in successfully "+useraccount);
				startTest.log(LogStatus.INFO, "The user has logged in successfully "+useraccount,"Successfull");
				return true;
			}
			else
			{
				BaseClass.forceStopExecution("Terminate execution, Incorrect User details when validating the user");
			}
			return false;
		
	}
	
	
	public WebElement selectProductMenu(String product) throws Exception
	{
		waitForWebElement(driver.findElement(By.linkText(product)));
		return driver.findElement(By.linkText(product));
	}
	
	public WebElement getProductItem(String item) throws Exception
	{
		
		WebElement element = driver.findElement(By.xpath("//p[contains(text(),'"+item+"')]"));
		waitForWebElement(element);
		return  element;
	}
	

}
