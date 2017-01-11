package com.genericLibraries;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	XSSFWorkbook wb =null;
	public ExcelReader(String path) throws IOException
	{
		//String path= "D:\\Jim\\SetupFiles\\Age.xlsx";
		FileInputStream fis = new FileInputStream(path);
		
		wb=	new XSSFWorkbook(fis);
		/*if(wb!=null)
			{
				System.out.println("Excel Connection Success");
			}*/
		fis.close();
	}
	
	public int getXLRowCount(String sheetname)
	{
		int lastRowNum = wb.getSheet(sheetname).getLastRowNum();
		//System.out.println("THe number rows is "+lastRowNum );
		return lastRowNum;
	}
	
	public short getXLColCount(String sheetname)
	{
		short lastCellNum = wb.getSheet(sheetname).getRow(0).getLastCellNum();
		//System.out.println("THe number of columns is " +lastCellNum );
		return lastCellNum;
	}
	
	public short getXLColCount(String sheetname, int rowindex)
	{
		short lastCellNum = wb.getSheet(sheetname).getRow(rowindex).getLastCellNum();
		//System.out.println("THe number of columns is " +lastCellNum );
		return lastCellNum;
	}
	
	public String getXLDataDouble(String sheetname, int rowindex, int colindex)
	{
		String celltext=null;
		XSSFCell cell = wb.getSheet(sheetname).getRow(rowindex).getCell(colindex);
		double numericCellValue = cell.getNumericCellValue();
		if(String.valueOf(numericCellValue).length()>9)
		{
			BigDecimal bigDecimal = new BigDecimal(cell.getNumericCellValue());
			//System.out.println(bigDecimal.toString());
			celltext=bigDecimal.toString();
		}
		else
		{
			celltext = String.valueOf(numericCellValue);
			celltext=celltext.replace(".0", "");
		}
		return celltext;
	}
	
	public String getXLData(String sheetname, int rowindex, int colindex)
	{
		//Read a value from excel
		String celltext=null;
		XSSFCell cell = wb.getSheet(sheetname).getRow(rowindex).getCell(colindex);
		if(cell.getCellType()==Cell.CELL_TYPE_STRING)
		{
			/*System.out.println(Cell.CELL_TYPE_STRING);
			System.out.println(cell.getStringCellValue());*/
			celltext=cell.getStringCellValue();
		}
		else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			/*System.out.println(cell.getCellType());
			System.out.println(cell.getNumericCellValue());*/
			double numericCellValue = cell.getNumericCellValue();
			if(String.valueOf(numericCellValue).length()>9)
			{
				BigDecimal bigDecimal = new BigDecimal(cell.getNumericCellValue());
				//System.out.println(bigDecimal.toString());
				celltext=bigDecimal.toString();
			}
			else
			{
				celltext = String.valueOf(numericCellValue);
				celltext=celltext.replace(".0", "");
			}
			
		}
		else if(cell.getCellType()==Cell.CELL_TYPE_BOOLEAN)
		{
			/*System.out.println(cell.getCellType());
			System.out.println(cell.getBooleanCellValue());*/
			boolean booleanCellValue = cell.getBooleanCellValue();
			celltext = String.valueOf(booleanCellValue);
		}
		else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
		{
			/*System.out.println(cell.getCellType());
			System.out.println("The cell is empty");*/
			celltext = "";
		}
		//System.out.println(celltext);
		return celltext;
	}
	
	public void setXLCellValue(String sheetname, int rowindex, int colindex, String cellvalue)
	{
		XSSFRow row = wb.getSheet(sheetname).getRow(rowindex);
		if(row==null)
		{
			row=wb.getSheet(sheetname).createRow(rowindex);
		}
		
		XSSFCell cell2 = row.getCell(colindex);
		if(cell2==null)
		{
			cell2 =  row.createCell(colindex);
		}
		cell2.setCellValue(cellvalue);
	}
	
	public void writeToXL(String path) throws Exception
	{
		FileOutputStream fout = new FileOutputStream(path);
		wb.write(fout);
		fout.close();
		wb.close();
	}

}
