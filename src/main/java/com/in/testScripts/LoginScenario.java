package com.in.testScripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.genericLibraries.BaseClass;
import com.in.genericTestScenario.LoginGeneric;
import com.in.pageFactory.HomePageIndia;

public class LoginScenario extends BaseClass {
	
	@Test(dataProvider = "DP_GetData",dataProviderClass = com.dataprovider.DataProviderGeneric.class)
	public void validLoginScenario(Map<String,String> dataMap) throws Exception{
		
		HomePageIndia homePageIndia = new HomePageIndia(driver, startTest);
		LoginGeneric loginGeneric = new LoginGeneric(driver, startTest);
		
		homePageIndia.clickWebElement(homePageIndia.HomeMenu);
		loginGeneric.validLoginGeneric(dataMap);
	}

}
