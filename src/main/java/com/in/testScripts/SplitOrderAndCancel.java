package com.in.testScripts;


import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.in.commonHelpers.SplitOrderMethod;

public class SplitOrderAndCancel {
	
	@Test(dataProvider = "DP_GetData",dataProviderClass = com.dataprovider.DataProviderGeneric.class)
	public void splitProductOrder(Map<String,String> dataMap) throws Exception
	{
		String productInfo = dataMap.get("Product");
		SplitOrderMethod productsplit= new SplitOrderMethod();
		List<Map<String, String>> splitProductDetails = productsplit.getSplitProductDetails(productInfo);
		for(Map<String, String> m :splitProductDetails)
		{
			System.out.print(m.get("ProductName")+"\t"+m.get("ProductItem"));
			System.out.println("Next Iteration");
		}
	}
	
	
	
}
