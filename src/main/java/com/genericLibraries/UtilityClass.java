package com.genericLibraries;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UtilityClass {
	//static String ConfigFile= "\\src\\main\\resources\\Configuration.properties";
	
	public static String getPropertyData(String key)
	{
		
		InputStream fis =null;
		fis = UtilityClass.class.getResourceAsStream("/Configuration.properties");
		
		Properties prop = new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
			System.out.println(" There is a IO exception when reading the property file");
		}
		return prop.getProperty(key);
	}
	
	

}
