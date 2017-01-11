package com.in.pageFactory;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.eventHandler.ObjectEventHandler;
import com.genericLibraries.BaseClass;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CartPageIndia extends ObjectEventHandler {
	private String CartPageTitle= "My cart - LeMall.com";
	
	
	public CartPageIndia(WebDriver driver, ExtentTest startTest){
		super(driver,startTest);
		PageFactory.initElements(driver, this);	}
	
	/*All Page cart button*/
	@FindBy(xpath="//a[@class='cart_icon'][text()='Cart']")
	public WebElement CartButton;
	
	/*The total amount in the Cart page*/
	@FindBy(css="span[id='cartPirceID']")
	public WebElement totalAmount;
	
	/*The view cart button when adding any product to the cart*/
	@FindBy(xpath="//a[@class='red_bt_auto mr20'][text()='View cart']")
	public WebElement ViewCart;
	
	/*Select all checkbox in the cart page*/
	@FindBy(xpath="//input[@class='allcheck'][@name='selectGood']")
	public WebElement SelectAllCheckBoxCart;
	
	/*Link to delete the selected items in the cart*/
	@FindBy(css=".del_cart")
	public WebElement DeleteSelectedCart;
	
	/*Confirmation button in the confirmation box whether to delete the selected items from the cart*/
	@FindBy(css="#wjConfirm-ok")
	public WebElement DeleteCartConfirmation;
	
	/*Button when no items are available in the cart*/
	@FindBy(css=".red_bt_auto")
	public WebElement GotoShopping;
	
	/*Check out button in the cart page*/
	@FindBy(css=".red_bt_l")
	public WebElement CheckOutButton;
	
	/*List of TV items in cart*/
	@FindBy(xpath="//div[@id='suite']/div")
	public List<WebElement> TVItemsInCartPage;
	
	/*List of other items except TV in cart*/
	@FindBy(xpath="//div[@id='suite']/div")
	public List<WebElement> AllItemsInCartPage;
	
	/*continue shopping*/
	@FindBy(xpath="//a[@class='gray_bt_auto'][text()='Continue shopping']")
	public WebElement ContinueShopping;
	
	//Logo
	@FindBy(xpath="//a[@class='logo']")
	public WebElement Logo;

	
	
	public WebElement productSubToTal(String productItem) {
		
		return(driver.findElement(By.xpath("//table[@class='cart_list']/tbody/tr/td[3]/a[contains(text(),'"+productItem+"')]/../../td[6]/span")));

		}
	
	public WebElement cartPrice(String productItem)
		{
			return(driver.findElement(By.xpath("//table[@class='cart_list']/tbody/tr/td[3]/a[contains(text(),'"+productItem+"')]/../../td[4]")));
			
		}
	
	public WebElement qtyCount(String productItem)
	{
		return(driver.findElement(By.xpath("//table[@class='cart_list']/tbody/tr/td[3]/a[contains(text(),'"+productItem+"')]/../../td[5]/input")));
		
	}
	
	public WebElement getCartProductitem(String productItem)
	{
		return(driver.findElement(By.xpath("//table[@class='cart_list']/tbody/tr/td[3]/a[contains(text(),'"+productItem+"')]")));
		
	}
	
	
	/*Method to validate the Cart page*/
	public boolean validateCartPage() throws Exception
	{
		//waitForPageTitle(CartPageTitle);
		if(!driver.getTitle().equalsIgnoreCase(CartPageTitle))
		{
			
			BaseClass.forceStopExecution("Unable to load the page"+CartPageTitle);
		}
		return true;
	}
	
	public boolean deleteAllCartItems() throws Exception
	{
		boolean validateCartPage = validateCartPage();
		//System.out.println(validateCartPage);
		if(validateCartPage)
		{
			startTest.log(LogStatus.INFO, "User in My CarPage Page","Successfull");	
			
									
				
					if(!SelectAllCheckBoxCart.isSelected())
					{
						
						clickWebElement(SelectAllCheckBoxCart);
					}
							
							clickWebElement(DeleteSelectedCart);
							clickWebElement(DeleteCartConfirmation);
							startTest.log(LogStatus.INFO, "Delete All item in the cart Cart Successfull","Successfull");	
						
				return true;
		
		}return false;		
		
	}
	
		
	public void clearItemFromCart(String Product, String ProductItem) throws InterruptedException
	{
		List<WebElement> TVItems = driver.findElements(By.xpath("//div[@id='attact']/div"));
		
		for(int i=0;i<=TVItems.size();i++)
		{			
			//WebDriverWait wait = new WebDriverWait(driver,30);
			//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[1]/table/tbody/tr/td[@class='cart-tab-edit']/span[text()='Delete']"))));
			Thread.sleep(3000);
			TVItems.get(i).findElement(By.xpath("//div[1]/table/tbody/tr/td[@class='cart-tab-edit']/span[text()='Delete']")).click();
			
			driver.findElement(By.xpath("//span[@id = 'wjConfirm-ok'][text()='Confirm']")).click();
			//driver.navigate().refresh();
		}

	}
	
	public void switchToCartWindow(String parentWindow)
	{
		Set<String> AllWindow = driver.getWindowHandles();
		for(String Tab : AllWindow )
		{
			if(!Tab.equals(parentWindow))
			{
				driver.switchTo().window(Tab);
				if(driver.getTitle().contains(CartPageTitle))
				{
					break;
				}
				else
				{
					continue;
				}
			}
			
		}startTest.log(LogStatus.INFO, "User has switched to the Cart Tab on his browser","Successfull");
	}
	
	public boolean isExistProductItemInCart(String productName, String productItem) throws Exception
	{
		if(productName.equalsIgnoreCase("TV"))
		{
			for(WebElement e: TVItemsInCartPage)
			{
				String 	productItemfromCartPage = e.findElement(By.xpath("//table[@class='cart_list']/tbody/tr[1]/td[3]/a")).getText();
				System.out.println(productItemfromCartPage+" "+productItem);
				if(productItemfromCartPage.equalsIgnoreCase(productItem))
				{
					startTest.log(LogStatus.INFO, "The product item :"+productItem+" has been added successfully to the cart","Successfull");
					break;
				}
			}
		}
		else 
		{
			for(WebElement e: AllItemsInCartPage)
			{
				String 	productItemfromCartPage = e.findElement(By.xpath("//table[@class='cart_list']/tbody/tr[1]/td[3]/a")).getText();
				System.out.println(productItemfromCartPage+" "+productItem);
				if(productItemfromCartPage.equalsIgnoreCase(productItem))
				{
					startTest.log(LogStatus.INFO, "The product item :"+productItem+" has been added successfully to the cart","Successfull");
					break;
				}
			}
		}
		return true;
	}
	
	
	

}
