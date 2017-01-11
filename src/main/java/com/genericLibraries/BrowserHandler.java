package com.genericLibraries;

import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BrowserHandler {
	private String system = null;
	protected String ChromeDriverExe = null;
	protected String IEExplorerExe =null;
	protected  WebDriver driver=null;	
	public static ExtentReports extentReports;
	public ExtentTest startTest;
	
	
	
	public  WebDriver getDriver(String browsername) throws Exception {

		//if (driver == null) {
			system= UtilityClass.getPropertyData("system");
			System.out.println(system);
				if(system.equalsIgnoreCase("local"))
				{
					Platform OS = Platform.ANY;
					DesiredCapabilities capablities = null;
					if(browsername.equalsIgnoreCase("firefox")){
						capablities = DesiredCapabilities.firefox();
						capablities.setBrowserName(browsername);
						capablities.setPlatform(OS);
						driver = new FirefoxDriver();	
					}else if(browsername.equalsIgnoreCase("chrome")){
						ChromeDriverExe = System.getProperty("user.dir")+UtilityClass.getPropertyData("ChromeDriver");
						System.setProperty("webdriver.chrome.driver", ChromeDriverExe);
						capablities = DesiredCapabilities.chrome();
						capablities.setBrowserName(browsername);
						capablities.setPlatform(OS);
						driver = new ChromeDriver();
					}else if(browsername.equalsIgnoreCase("ie")){
						IEExplorerExe = System.getProperty("user.dir")+UtilityClass.getPropertyData("IEDriver");
						System.setProperty("webdriver.ie.driver", IEExplorerExe);
						driver = new InternetExplorerDriver();		
					}
					else if(browsername.equalsIgnoreCase("safari")){			
						
						driver = new SafariDriver();		
					}
					else
					{
						startTest.log(LogStatus.INFO, "Incorrect browser detail","Successfull");
						BaseClass.forceStopExecution("Incorrect browser detail, Terminating execution");
					}
					driver.manage().timeouts().pageLoadTimeout(180000, TimeUnit.MILLISECONDS);			
				}
				else if(system.equalsIgnoreCase("remote"))
				{
					Platform OS = Platform.ANY;
					DesiredCapabilities capablities = null;
					if(browsername.equalsIgnoreCase("firefox")){
						capablities = DesiredCapabilities.firefox();
						capablities.setBrowserName(browsername);
						capablities.setPlatform(OS);
						//driver = new FirefoxDriver();	
					}else if(browsername.equalsIgnoreCase("chrome")){
						//ChromeDriverExe = System.getProperty("user.dir")+UtilityClass.getPropertyData("ChromeDriver");
						//System.setProperty("webdriver.chrome.driver", ChromeDriverExe);
						capablities = DesiredCapabilities.chrome();
						capablities.setBrowserName(browsername);
						capablities.setPlatform(OS);
						//driver = new ChromeDriver();
					}else if(browsername.equalsIgnoreCase("ie")){
						capablities = DesiredCapabilities.internetExplorer();
						capablities.setBrowserName("internet explorer");						
						capablities.setPlatform(OS);
					}
					else if(browsername.equalsIgnoreCase("safari")){
						capablities = DesiredCapabilities.safari();
						capablities.setBrowserName("safari");						
						capablities.setPlatform(OS);
					}
					else
					{
						startTest.log(LogStatus.INFO, "Incorrect browser detail","Successfull");
						BaseClass.forceStopExecution("Incorrect browser detail, Terminating execution");
					}
					
					URL url = new URL("http://localhost:4444/wd/hub");
					driver = new RemoteWebDriver(url,capablities);
					driver.manage().timeouts().pageLoadTimeout(180000, TimeUnit.MILLISECONDS);	
				}
			
			//}
			
			

		return driver;

	}
	
	
	public void switchBrowserWindow(WebDriver driver , String pageTitle)
	{
		String parentWindow = driver.getWindowHandle();
		Set<String> AllWindow = driver.getWindowHandles();
		for(String Tab : AllWindow )
		{
			if(!Tab.equals(parentWindow))
			{
				driver.switchTo().window(Tab);
				if(driver.getTitle().contains(pageTitle))
				{
					break;
				}
				else
				{
					continue;
				}
			}
			
		}
	}
	
	
}
