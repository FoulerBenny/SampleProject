package com.genericLibraries;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.interactions.Actions;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;





public class BaseClass extends BrowserHandler{
	
	private static String URL = UtilityClass.getPropertyData("URL");
	private static String WAIT_IN_MILLISECONDS = UtilityClass.getPropertyData("WAIT_IN_MILLISECONDS");	
	private String browser = null;
	//public String platform = UtilityClass.getPropertyData("Platform");
	public String btype;
	protected static String Reportpath = null;//System.getProperty("user.dir")+ "\\Report\\LeMALLIndia";
	protected static String Reportsnap = null;//System.getProperty("user.dir")+ "\\Report\\snap\\LeMALLIndia";
	protected String TC_ID = null;
	protected String Run = null;
	protected String TestScenario = null;
	private static String report = null;
	
	
	
	@BeforeSuite
	protected void intitalSetup(ITestContext context){
		
		ZipUtils zipUtils = new ZipUtils();		
		Reportpath= zipUtils.runzip(UtilityClass.getPropertyData("shareFolder"));	
		System.out.println(Reportpath);		
		Reportsnap = Reportpath+File.separator+"snap"+File.separator;	
		
		 report = Reportpath+File.separator+"LeMallIndia-PC-"+DateEvents.getCurrentDataTime1()+ ".html";
		 //System.out.println(report);
		extentReports = new ExtentReports(report,true);	
		extentReports.loadConfig(new File(System.getProperty("user.dir")+UtilityClass.getPropertyData("extentReport"))); 
	}	
	
	
	@BeforeMethod
	@Parameters({"browser"})
	protected void launch_App(@Optional("chrome")String browsername, Object[] object) throws Exception{	
		Map<String, String> dataMap = getTestCaseName(object);
		
		TC_ID = dataMap.get("TC_ID");
		Run = dataMap.get("Run");
		TestScenario = dataMap.get("TestScenario");	
		browser = browsername;
		
		startTest = extentReports.startTest(TC_ID+"_"+Run+"_"+TestScenario);	
		startTest.assignCategory(browsername+"_TestScriptExecution");
		
		
	/*	Platform OS = Platform.ANY;
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
		else
		{
			startTest.log(LogStatus.INFO, "Incorrect browser detail","Successfull");
			forceStopExecution("Incorrect browser detail, Terminating execution");
		}*/
		
		getDriver(browsername);
		driver.manage().deleteAllCookies();
		driver.navigate().to(URL);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(WAIT_IN_MILLISECONDS), TimeUnit.MILLISECONDS);		
				
	}
	
	
	
		
	@AfterMethod
	protected void afterMethod(ITestResult result) {
		
		 if (result.getStatus() == ITestResult.FAILURE) {	
			 
		        String path = screenshot(TC_ID, Run, browser, TestScenario);
		        //startTest.log(LogStatus.FAIL, getFailureInfo(result.getThrowable()),"The error will be checked manually and will raise jira if required"+startTest.addScreenCapture(path));
		        startTest.log(LogStatus.FAIL,getFailureInfo(result.getThrowable()),"The error will be checked manually and will raise jira if required"+startTest.addScreenCapture(path));
		        /*Throwable cause = result.getThrowable();
				 if (SkipException.class.isAssignableFrom(cause
				                    .getClass()))
				 {
					 startTest.log(LogStatus.PASS, TC_ID+"_"+Run+"_"+TestScenario+" has passed successfully","Successfull");
				 }*/
		 } else if (result.getStatus() == ITestResult.SKIP) {
		    	startTest.log(LogStatus.PASS, TC_ID+"_"+Run+"_"+TestScenario+" has passed successfully","Successfull");
		    } else {
		    	startTest.log(LogStatus.PASS, TC_ID+"_"+Run+"_"+TestScenario+" has passed successfully","Successfull");
		    }  
	        driver.quit();
	        extentReports.endTest(startTest);        
	        
	        
	    }
	
	
	@AfterSuite
	public void tearDownSuite()
	{
		extentReports.flush();
		EmailAttachmentSender.sendEmail(report);
	}
	
	private Map<String, String> getTestCaseName(Object[] obj)
	{
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) obj[0];
		return map;
		
	}
	
	public static void forceStopExecution(String message) throws Exception    
	{
		throw new Exception(message);		
	}
	
	public static void skipTestExecution(String message)
	{
		throw new SkipException(message);
	}
	

	
	
	
//	screenshot
	public String screenshot(String TCID,String Orderid,String browser, String TestScenario) {
		
		TakesScreenshot Screenshot=(TakesScreenshot)driver;
		File screenshotAs = Screenshot.getScreenshotAs(OutputType.FILE);
		
		String path = Reportsnap + TCID + "_" + Orderid + "_" +browser + "_"+TestScenario+ "_" +DateEvents.getCurrentDataTime1() + ".jpg";
		try {
			FileUtils.copyFile(screenshotAs, new File(path));
		} catch (IOException e) {
			System.out.println("Unable to find the location for screenshot");
		}
		return path;
		
		
	}
	
	 public static String getFailureInfo(Throwable throwable)
	 {
		 
		String MethodName=throwable.getStackTrace()[2].getMethodName();
		int LineNumber1=throwable.getStackTrace()[2].getLineNumber();
		int LineNumber2=throwable.getStackTrace()[1].getLineNumber();
		int LineNumber=0;
		if(LineNumber1<0)
		{
			LineNumber=LineNumber2;
		}
		else			
		{
			LineNumber=LineNumber1;
		}
		StackTraceElement[] outTrace = new StackTraceElement[0];
		throwable.setStackTrace(outTrace);
		String failure ="Failure: "+throwable.toString()+"\n"+".[Method Name: "+MethodName+"\t"+"LineNumber: "+LineNumber+"]";
		//System.out.println("Failure: "+throwable.toString().split(": ")[1]+"\n"+" Method Name: "+MethodName+"\t"+"LineNumber: "+LineNumber);
		return failure;
	 }
	
	public Actions getActionsClassObject()
	{
		return new Actions(driver);
		
	}
	


}
