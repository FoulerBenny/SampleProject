package com.genericLibraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/*latest*/
	public class ZipUtils
	{
	
		private List<String> fileList;
		private static  String FOLDERNAME = null;
		private static  String OUTPUT_ZIP_FILE = null; //"C:\\ExtentReport";
		private static  String SOURCE_FOLDER = null; //"C:\\ExtentReport"; // SourceFolder path
	
		public ZipUtils()
		{
		   fileList = new ArrayList<String>();
		}
	
		/*public static void main(String[] args)
		{
			ZipUtils appZip = new ZipUtils();
			appZip.runzip("C:\\08.12.16 08.30.14");
			
		}*/
		
		public String runzip(String path)
		{
			
			SOURCE_FOLDER = path+File.separator+"ExtentReport";
			checkDirectoryExist();
			OUTPUT_ZIP_FILE = SOURCE_FOLDER;
			generateFileList(new File(SOURCE_FOLDER));
			zipIt(OUTPUT_ZIP_FILE);
			deleteDirectory(new File(SOURCE_FOLDER));
			return createDirAndSubDir();
		}
		
	
		public String createDirAndSubDir()
		{
			
			File file = new File(SOURCE_FOLDER+File.separator+"snap");
			
			if(!file.exists())
			{
				file.mkdirs();
			}
			file = new File(SOURCE_FOLDER+File.separator+"Processed");
			if(!file.exists())
			{
				file.mkdir();
			}
			return SOURCE_FOLDER;
		}
	
		public static void checkDirectoryExist()
		{
			File file = new File(SOURCE_FOLDER+"\\Processed");
			if(!file.exists())
			{
				file.mkdirs();
			}
			file = new File(SOURCE_FOLDER+"\\Processed");
			if(!file.exists())
			{
				file.mkdir();
			}
		}
	
		public static String getCurrentDataTime()
		{
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh.mm.ss");
			return simpleDateFormat.format(date);
		}
		
			public void zipIt(String zipFile)
			{
				if(fileList.size()!=0)
				{
					byte[] buffer = new byte[1024];
					   String source = "";
					   FileOutputStream fos = null;
					   ZipOutputStream zos = null;
					   try
					   {
					      try
					      {
					         source = FOLDERNAME;
					      }
					     catch (Exception e)
					     {
					        source = SOURCE_FOLDER;
					     }
					     fos = new FileOutputStream(zipFile);
					     zos = new ZipOutputStream(fos);
					
					     System.out.println("Output to Zip : " + zipFile);
					     FileInputStream in = null;
					
					     for (String file : this.fileList)
					     {
					        //System.out.println("File Added : " + file);
					        ZipEntry ze = new ZipEntry(source + File.separator + file);
					        zos.putNextEntry(ze);
					        try
					        {
					           in = new FileInputStream(SOURCE_FOLDER + File.separator + file);
					           int len;
					           while ((len = in.read(buffer)) > 0)
					           {
					              zos.write(buffer, 0, len);
					           }
					        }
					        finally
					        {
					           in.close();
					        }
					     }
					
					     zos.closeEntry();
					    // System.out.println("Folder successfully compressed");
					
					  }
					  catch (IOException ex)
					  {
					     ex.printStackTrace();
					  }
					  finally
					  {
					     try
					     {
					        zos.close();
					     }
					     catch (IOException e)
					     {
					        e.printStackTrace();
					     }
					  }
				}
			   
			}
	
	public void generateFileList(File node)
	{
	
	  // add file only
	  if (node.isFile())
	  {
	     fileList.add(generateZipEntry(node.toString()));
	     if(node.getName().endsWith(".html"))
	     {
	    	 FOLDERNAME = node.getName().substring(15, 30);
	    	 OUTPUT_ZIP_FILE=OUTPUT_ZIP_FILE+File.separator+"Processed"+File.separator+FOLDERNAME+".zip";
	     }
	
	  }
	
	  if (node.isDirectory())
	  {
		
			 String[] subNote = node.list();
		     for (String filename : subNote)
		     { 
		    	 if(!node.getName().equalsIgnoreCase("Processed"))
				 {
			        generateFileList(new File(node, filename));
				 }
		     }
		 
	    
	  }
	}
	
	private String generateZipEntry(String file)
	{
	   return file.substring(SOURCE_FOLDER.length(), file.length());
	}
	

public void deleteDirectory(File file)
{
	removeFiles(file);
	removeDirectory(file);
}
public void removeDirectory(File file)
{
	//removeFiles(file);
	// add file only
		  if (file.isFile())
		  {
			  file.delete();

		  }

		  if (file.isDirectory())
		  {
			 // System.out.println(file.getName());
			  if(!file.getName().equalsIgnoreCase("Processed"))
			  {
				  
				  File[] listFiles = file.listFiles();
				  
					  for (File filename : listFiles)
					     {	   
						  	removeDirectory(filename);
						  	
					     }
					  file.delete();
			     
			 }
		  }
}

public void removeFiles(File file)
{	 
	 // add file only
	  if (file.isFile())
	  {
		  file.delete();

	  }

	  if (file.isDirectory())
	  {
		  //System.out.println(file.getName());
		  if(!file.getName().equalsIgnoreCase("Processed"))
		  {
			  File[] listFiles = file.listFiles();
	     
		     for (File filename : listFiles)
		     {	   
		    	 removeFiles(filename);
		     }
		 }
	  }
	 
}
	}    