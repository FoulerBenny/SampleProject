package com.eventHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class ObjectEventHandler {
	public ExtentTest startTest;
	public WebDriver driver;
	
	public ObjectEventHandler(WebDriver driver,ExtentTest startTest)
	{
		this.startTest = startTest;
		this.driver = driver;
		
	}
	
	
	
	public boolean clickWebElement(WebElement element) throws Exception
	{
		
		try
		{
			element.click();
		}
		catch(Exception  e)
		{
			throw new  Exception("Unable to click on the webelement in the page");		
		}
		
		
		return true;
	}
	
	public String getTextBoxData(WebElement element) throws Exception
	{
		
		try
		{
			return element.getAttribute("value");
		}
		catch(Exception  e)
		{
			throw new  Exception("Unable to click on the webelement in the page");		
		}
	}
	
	public boolean mouseHoverWebElement(Actions actions , WebElement element) throws Exception{
		
		try{
			
            actions.moveToElement(element).perform();
		}
		catch(Exception e)
		{
			throw new  Exception("Unable to Mousehover the webElement");	
		}
		return true;
	}
	
public boolean clickMouseHoverWebElement(Actions actions, WebElement element ) throws Exception{
		
		try{
			
            actions.click(element).perform();
		}
		catch(Exception e)
		{
			throw new  Exception("Unable to click on the mousehover webElement");	
		}
		return true;
	}
	
	public boolean setTextbox(WebElement element, String text) throws Exception
	{
	
		try
		{
			element.clear();
			element.sendKeys(text);
		}
		catch(Exception  e)
		{
			throw new  Exception("Unable to enter text to the webelement in the page");		
		}
		
		
		return true;
		
	}
	
	public boolean selectCheckbox(WebElement  element) throws Exception
	{
		try
		{
			if(element.isSelected()==true)
			{
				element.click();
				return true;
			}
		}
		catch(Exception  e)
		{
			throw new  Exception("Unable to select the check box on the webelement in the page");		
		}
	
		return false;
	}
	
	
	
	public String getWebElementText(WebElement element)
	{
		String text = null;
		try
		{
			text=element.getText();
		}
		catch(Exception e)
		{
			startTest.log(LogStatus.FAIL, "Unable to enter value to text box in the webpage"+startTest.addScreenCapture(System.getProperty("user.dir")+"\\Report\\Snapshots\\snapshot"),"Refer the screenshot");
			
		}
		return text;
		
	}	
	
	public String getAttributeText(WebElement element)
	{
		String text = null;
		try
		{
			text=element.getAttribute("value");
		}
		catch(Exception e)
		{
			startTest.log(LogStatus.FAIL, "Unable to enter value to text box in the webpage"+startTest.addScreenCapture(System.getProperty("user.dir")+"\\Report\\Snapshots\\snapshot"),"Refer the screenshot");
			
		}
		return text;
		
	}
	
	public String getAttributeText(WebElement element, String attributeName)
	{
		String text = null;
		try
		{
			text=element.getAttribute(attributeName);
		}
		catch(Exception e)
		{
			startTest.log(LogStatus.FAIL, "Unable to enter value to text box in the webpage"+startTest.addScreenCapture(System.getProperty("user.dir")+"\\Report\\Snapshots\\snapshot"),"Refer the screenshot");
			
		}
		return text;
		
	}
	
	public boolean waitForWebElement(WebElement element) throws Exception
	{
		try{
			WebDriverWait wait=new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOf(element));
			
			}
			catch(Exception e)
			{
				throw new  Exception("Unable to find the element in the page.");		
			}
		return true;
	}
	
	public boolean waitForPageTitle(String title) throws Exception
	{
		try{
			WebDriverWait wait=new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.titleContains(title));
		}
		catch(Exception e)
		{
			throw new  Exception("Unable to load the page having the title: "+title+".");		
		}
		return true;
	}
	
	public boolean waitForPageAlert(String title) throws Exception
	{
		try{
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.alertIsPresent());
		}
		catch(Exception e)
		{
			throw new  Exception("No alert is displayed on this page");		
		}return true;
	}
	
	public String parseDateTime(String date) throws ParseException
	{
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		Date date1 = simpleDateFormat.parse(date);
		
		return simpleDateFormat.format(date1);
		
	}
	
	
	public void scrollToElement(WebElement element) throws Exception
	{
		try{
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("arguments[0].scrollIntoView(true);",element);
		}
		catch(Exception e)
		{
			throw new  Exception("No alert is displayed on this page");	
		}
		
	}

}
