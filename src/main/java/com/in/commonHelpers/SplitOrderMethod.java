package com.in.commonHelpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitOrderMethod {
	
	public List<Map<String, String>> getSplitProductDetails(String productInfo)
	{
		List<Map<String,String>> ls = new ArrayList<Map<String, String>>();
		String[] NoOfProduct = productInfo.split(",");
		
		
		for(int i = 0;i<NoOfProduct.length;i++)
		{
			Map<String, String> map = new HashMap<String,String>();			
			String[] ProductCategory = NoOfProduct[i].split("&");
			//System.out.print(ProductCategory[0] +"\t"+ProductCategory[1]);
			map.put("Product", ProductCategory[0]);
			map.put("ProductItem", ProductCategory[1]);
			map.put("StockStatus", ProductCategory[2]);
			map.put("Quantity", ProductCategory[3]);

			ls.add(map);
		}return ls;
	}

}
