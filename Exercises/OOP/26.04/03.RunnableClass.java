package com.seeburger.filemanipulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RunnableClass implements Runnable
{
	private File file;
	private File destination;

	public RunnableClass(File file, File destination)
	{
		this.file = file;
		this.destination = destination;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run()
	{
		
		try
		{
			copeFileUsingStream(this.file, this.destination);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void copeFileUsingStream(File source, File dest) throws IOException
	{
		InputStream iStream = null;
		OutputStream outputStream = null;
		
		System.out.println("Source: " + source.getAbsolutePath());
		System.out.println("Destination: " + dest.getAbsolutePath());
		
		try
		{
			iStream = new FileInputStream(source);
			outputStream = new FileOutputStream(dest);
			byte[] buffer = new byte[512];
			int length;
			while ((length = iStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, length);
			}
//			System.out.println(System.currentTimeMillis());
			
		} finally
		{
			iStream.close();
			outputStream.close();
		}
		{
			
		}
	}
	
	
}
