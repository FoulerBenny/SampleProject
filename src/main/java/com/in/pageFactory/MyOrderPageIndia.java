package com.in.pageFactory;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eventHandler.ObjectEventHandler;
import com.relevantcodes.extentreports.ExtentTest;

public class MyOrderPageIndia extends ObjectEventHandler {
	public String MyOrderTitle = "My orders-LeMall";
	public MyOrderPageIndia(WebDriver driver, ExtentTest startTest){
		super(driver,startTest);
		PageFactory.initElements(driver, this);	}
	
	/*Fetch the Order data of each Order from the list*/
	@FindBy(xpath ="//div[@id='div_order_list']/table/tbody/tr/th/div[2]")
	public List<WebElement> MyOrderDateList;
	
	@FindBy(css=".ul_login>li:nth-child(3)>a")
	public WebElement MyOrderLink;
	
	@FindBy(xpath="//ul[@id='ulReason']/li")
	public List<WebElement> CancellationReason;	
	
	@FindBy(xpath="//div[@id='cancel_reason_div']/div/div[3]/a")
	public WebElement CancellationConfrimButton;
	
	/*Addressbook link in My Order page*/
	@FindBy(linkText="Address book")
	public WebElement AddressBookLink;
	
	public WebElement cancelOrder(String CancelReason)
	{
		WebElement element = null;
		for(WebElement e:CancellationReason)
		{
			if(e.getText().equalsIgnoreCase(CancelReason))
					{
						element=e;
						break;
					}
		}return element;
	}
	
	public List<String> cancelOrderIDs(String orderDate) throws Exception
	{
		List<String> ls = new ArrayList<String>();
		
		for(int i=1;i<=MyOrderDateList.size();i++)
		{
			String elementText = getWebElementText(driver.findElement(By.xpath("//div[@id='div_order_list']/table["+i+"]/tbody/tr[1]/th/div[2]")));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
			Date date2 = simpleDateFormat.parse(elementText);
			
			if(simpleDateFormat.format(date2).equals(orderDate))
					{
						String OrderID = getWebElementText(driver.findElement(By.xpath("//div[@id='div_order_list']/table["+i+"]/tbody/tr[1]/th/div[1]")));
						ls.add(OrderID);
						System.out.println(OrderID);
						
						clickCancelButton(OrderID, i);
						WebElement cancelbutton = driver.findElement(By.xpath("//div[@id='div_order_list']/table["+i+"]/tbody/tr[2]/td[4]/div[3]"));
						waitForWebElement(cancelbutton);
						clickWebElement(cancelbutton);
						
						clickWebElement(CancellationConfrimButton);
						waitForPageAlert("in.lemall.com says:");
						driver.switchTo().alert().accept();	
					}
		}return ls;
	}
	
	
	private void clickCancelButton(String OrderID, int i) throws Exception
	{
		boolean flag=true;
		while(flag)
		{
			WebElement OrderStatus = driver.findElement(By.xpath("//div[@id='div_order_list']/table["+i+"]/tbody/tr[2]/td[3]/div[1]"));
			String OrderText = OrderStatus.getText();
			if(!OrderText.equalsIgnoreCase("Order submitted"))
			{
				flag=false;
				break;
			}
			else
			{
				driver.navigate().refresh();
			}
			
		}		
		
	}
	
	public WebElement cancellationReceievedMessage(String OrderID)
	{
		return driver.findElement(By.xpath("//div[@id='showDelieverInfo_"+OrderID.split(": ")[1]+"']/../div"));
	}
	
	public boolean validateMyOrderPage() throws Exception
	{
		waitForPageTitle(MyOrderTitle);
		return true;
	}
}
