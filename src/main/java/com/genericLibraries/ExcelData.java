package com.genericLibraries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExcelData {
	private static String XLPATH= System.getProperty("user.dir")+UtilityClass.getPropertyData("XLPATH");
	private static final String XLLookUP_Sheet = UtilityClass.getPropertyData("XLLOOKUP_SHEETNAME"); ;
	
	public String getScenarioSheetName(ExcelReader xl,String methodname)
	{
		String SheetName=null;
		
		for(int i=1;i<=xl.getXLRowCount(XLLookUP_Sheet);i++)
		{
			if(xl.getXLData(XLLookUP_Sheet, i, 1).equalsIgnoreCase(methodname))
					{
						SheetName = xl.getXLData(XLLookUP_Sheet, i, 2);
						//System.out.println(SheetName);
					}
		}return SheetName;
		
	}
	
	public List<Object[]> getXLData(String methodname)
	{
		
		ExcelReader xl=null;
		String Script_SheetName = null;
				
		List<Object[]> ls = new ArrayList<Object[]>();
		
		try {
			 xl = new ExcelReader(XLPATH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Script_SheetName=getScenarioSheetName(xl,methodname);
		int xlRowCount = xl.getXLRowCount(Script_SheetName);
		int xlColCount = xl.getXLColCount(Script_SheetName);
		
		for(int i=1;i<=xlRowCount;i++)
		{	
			if(xl.getXLData(Script_SheetName,i,3).equals("Y") && xl.getXLData(Script_SheetName,i,2).equals(methodname))
			{
				Map<String, String> map = new HashMap<String,String>();
				for(int j=0;j<xlColCount;j++)
					{					
						String colname = xl.getXLData(Script_SheetName, 0, j);
						String colvalue = xl.getXLData(Script_SheetName, i, j);
						map.put(colname, colvalue);						
					}
				Object[] obj = new Object[1];
				obj[0]=map;
				ls.add(obj);
			}			
		}
		return ls;		
	}
	
	

}
