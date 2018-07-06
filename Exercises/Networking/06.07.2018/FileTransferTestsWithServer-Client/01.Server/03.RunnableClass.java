package com.seeburger.fileTransferAutomation;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * File mover thread which contains
 * the file moving methods - Input/Output Stream based
 */

public class RunnableClass implements Runnable
{
	private volatile Object lock = new Object();
	private volatile boolean movingFinished = false;
	private static volatile boolean toStop = false;
	private String destination;
	private String location;
	private Logger logger;
	private DataOutputStream dout;

	public RunnableClass(String location, String dest, Logger logger, DataOutputStream dout)
	{
		this.destination = dest;
		this.location = location;
		this.logger = logger;
		this.dout = dout;
	}

	public void setToStop(boolean toStop)
	{
		this.toStop = toStop;
	}
	
	public static boolean getToStop() {
		return toStop;
	}

	
	@Override
	public void run()
	{
		synchronized (lock)
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

			lock.notifyAll();
			long end = System.currentTimeMillis();
			movingFinished = true;
			System.out.println("Moving complete. Operation took: " + (end - current) + " miliseconds. "
					+ Thread.currentThread().getName());
			System.out.println("Enter 'y' if you want to continue or 'end' if you want to exit.");
		}
	}

	public Object getLock()
	{
		return lock;
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
