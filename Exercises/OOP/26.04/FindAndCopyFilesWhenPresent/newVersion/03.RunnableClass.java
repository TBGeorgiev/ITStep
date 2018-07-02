package com.seeburger.fileTransferAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Logger;

public class RunnableClass implements Runnable
{
	private volatile boolean movingFinished = false;
	private volatile boolean toStop = false;
	private String destination;
	private String location;
	private Logger logger;

	public RunnableClass(String location, String dest, Logger logger)
	{
		this.destination = dest;
		this.location = location;
		this.logger = logger;
	}

	public void setToStop(boolean toStop)
	{
		this.toStop = toStop;
	}

	@Override
	public void run()
	{
		movingFinished = false;
		File source = new File(location);
		File[] files = source.listFiles();
		File dest = new File(destination);
		if (!dest.exists())
		{
			dest.mkdir();
		}
		long current = System.currentTimeMillis();
		for (File file : files)
		{
			if (!file.isDirectory())
			{
				try
				{
					copyFileUsingStream(file, dest);
					Files.delete(Paths.get(file.getAbsolutePath()));
					if (toStop)
					{
						System.out.println("Operation stopped.");
						return;
					}

				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		long end = System.currentTimeMillis();
		movingFinished = true;
		System.out.println("Moving complete. Operation took: " + (end - current) + " miliseconds. "
				+ Thread.currentThread().getName());
		System.out.println("Enter 'y' if you want to continue or 'end' if you want to exit.");
	}

	private void copyFileUsingStream(File source, File dest) throws IOException
	{
		InputStream iStream = null;
		OutputStream oStream = null;

		try
		{
			iStream = new FileInputStream(source);
			oStream = new FileOutputStream(dest + File.separator + source.getName());
			byte[] buffer = new byte[8192];
			int length;
			while ((length = iStream.read(buffer)) > 0)
			{
				oStream.write(buffer, 0, length);
			}
			logger.info(source.getName() + " size: " + source.length());
		} finally
		{
			iStream.close();
			oStream.close();
		}
	}

	public boolean isMovingFinished()
	{
		return movingFinished;
	}

}
