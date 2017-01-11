package com.in.testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.genericLibraries.BaseClass;
import com.in.genericTestScenario.ProductGeneric;
import com.in.pageFactory.HomePageIndia;
import com.relevantcodes.extentreports.LogStatus;

public class DeliveryEstimationClass extends BaseClass {
	
	@Test(dataProvider = "DP_GetData",dataProviderClass = com.dataprovider.DataProviderGeneric.class,priority = 1)
	public void deliveryEstimationPincode(Map<String,String> dataMap) throws Exception{
		System.out.println("Start the Test "+dataMap.get("TestScenario"));
		String testDescription = dataMap.get("TestDescription");
		
		HomePageIndia homePageIndia = new HomePageIndia(driver, startTest);
		ProductGeneric productGeneric = new ProductGeneric(driver,startTest);
		
		startTest.log(LogStatus.INFO,testDescription ,"Successfull");
		//Click on Home Menu
		homePageIndia.clickWebElement(homePageIndia.HomeMenu);
		
		//select product and product Item
		productGeneric.selectProductFromMenu(dataMap);
		
		//Validate delivery estimation
		productGeneric.deliveryEstimationCheck(dataMap);
			
	}

	@Test(dataProvider = "DP_GetData",dataProviderClass = com.dataprovider.DataProviderGeneric.class,priority = 2)
	public void deliveryEstimationChangePincode(Map<String,String> dataMap) throws Exception{
		System.out.println("Start the Test "+dataMap.get("TestScenario"));
		String testDescription = dataMap.get("TestDescription");
		
		HomePageIndia homePageIndia = new HomePageIndia(driver, startTest);
		ProductGeneric productGeneric = new ProductGeneric(driver,startTest);
		
		startTest.log(LogStatus.INFO,testDescription ,"Successfull");
		//Click on Home Menu
		homePageIndia.clickWebElement(homePageIndia.HomeMenu);
		
		//select product and product Item
		productGeneric.selectProductFromMenu(dataMap);
		
		//Validate delivery estimation
		productGeneric.deliveryEstimationCheck(dataMap);
			
	}

}
