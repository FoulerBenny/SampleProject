package com.genericLibraries;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEvents {
	
	public static String getCurrentDataTime()
	{
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
		return simpleDateFormat.format(date);
	}
	
	public static String getCurrentDataTime1()
	{
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy_hhmmss");
		return simpleDateFormat.format(date);
	}
	
	
//	create unique date time stamp
	public static String timestamp(){
		
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String reportdate = simpleDateFormat.format(date);
		return reportdate;
	}
	

}
