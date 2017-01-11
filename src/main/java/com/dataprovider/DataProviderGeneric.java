package com.dataprovider;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.genericLibraries.ExcelData;

public class DataProviderGeneric {
	
	@DataProvider(name="DP_GetData")
	public static Iterator<Object[]> dataProviderGeneric(Method testcase)
	{
		String testname = testcase.getName();
		ExcelData xl = new ExcelData();
		 List<Object[]> xlData = xl.getXLData(testname);
		return xlData.iterator();
		
	}

}
