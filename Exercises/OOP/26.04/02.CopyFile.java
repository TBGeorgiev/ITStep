package com.seeburger.filemanipulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CopyFile
{
	private int count;
	private ExecutorService executorService;
	private Runnable runnable; 
	
	private final File folder = new File(System.getProperty("user.dir") + File.separator + "copyFolder");
	
	
	
	
	public CopyFile(ExecutorService executorService)
	{
		super();
		this.executorService = executorService;
	}


	public synchronized void copeFileUsingStream(File source, File dest) throws IOException
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
//			System.out.println(System.currentTimeMillis());
			
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
	
	
	
	
	public void findFileToCopy(String location, int numberOfFilesToCopy)
	{
		File folder = new File(location);
		
		File[] files = folder.listFiles();
		
		
		
		for (File file : files) {
			
			if (this.count > numberOfFilesToCopy) {
				break;
			}
			
			if (file.isDirectory()) {
//				System.out.println(file.getAbsolutePath());
				findFileToCopy(file.getAbsolutePath(), numberOfFilesToCopy);
			} 
			else if (!file.isDirectory() && file.length() > 100000) {
				this.count++;
//				System.out.println(System.currentTimeMillis());
				RunnableClass runnableClass = new RunnableClass(file, new File(this.folder.getAbsolutePath() 
						+ File.separator + file.getName()));
				executorService.execute(runnableClass);
				
			}
		}
		
		
	}
}
