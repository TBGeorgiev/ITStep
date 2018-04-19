package com.seeburger.filemanipulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFile
{

	public static void copeFileUsingStream(File source, File dest) throws IOException
	{
		InputStream iStream = null;
		OutputStream outputStream = null;
		
		System.out.println(source.getAbsolutePath());
		System.out.println(dest.getAbsolutePath());
		
		try
		{
			iStream = new FileInputStream(source);
			outputStream = new FileOutputStream(dest);
			byte[] buffer = new byte[512];
			int length;
			while ((length = iStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, length);
			}
			System.out.println(System.currentTimeMillis());
			
		} finally
		{
			iStream.close();
			outputStream.close();
		}
		{
			
		}
	}
	
	
	public static File generateFileDest(String location)
	{
		File file = new File(location);
		return file;
	}
	
	
	
	
	public static File findFileToCopy(String location)
	{
		File folder = new File(location);
		
		File[] files = folder.listFiles();
		
		
		
		for (File file : files) {
			if (file.isDirectory()) {
				System.out.println(file.getAbsolutePath());
				findFileToCopy(file.getAbsolutePath());
			} 
			else if (!file.isDirectory() && file.length() > 100000) {
				System.out.println(System.currentTimeMillis());
				return file;
			}
		}
		
		return null;	
	}
}
