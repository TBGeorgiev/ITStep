package com.seeburger.fileTransferAutomation;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Thread for checking the location/destination
 * and correct file amount.
 */

public class DestinationChecker implements Runnable
{

	private RunnableClass runnableClass;
	private String location;
	private String destination;
	private int numberOfFiles;
	private DataOutputStream dout;

	public DestinationChecker(RunnableClass runnableClass, String location, String destination, int numberOfFiles, DataOutputStream dout)
	{
		this.runnableClass = runnableClass;
		this.location = location;
		this.destination = destination;
		this.numberOfFiles = numberOfFiles;
		this.dout = dout;
	}

	@Override
	public void run()
	{
		synchronized (runnableClass.getLock())
		{
			while (!runnableClass.isMovingFinished())
			{
				try
				{
					runnableClass.getLock().wait();

				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try
			{
				this.dout.writeUTF("Location and destination tests:");
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println("Location and destination tests:");
			if (isLocationEmpty(this.location))
			{
				try
				{
					this.dout.writeUTF("\tLocation test: Good" + " - No files present.");
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//				System.out.println("\tLocation test: Good" + " - No files present.");
			} else
			{
				try
				{
					this.dout.writeUTF("\tLocation test: Bad" + " - Files still present in location: " + this.location);
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				System.out.println("\tLocation test: Bad" + " - Files still present in location: " + this.location);
			}
			if (destinationTest(this.destination, this.numberOfFiles))
			{
				try
				{
					this.dout.writeUTF("\tDestination test: Good" + " - Number of files matches.");
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				System.out.println("\tDestination test: Good" + " - Number of files matches.");
			} else
			{
				try
				{
					this.dout.writeUTF("\tDestination test: Bad" + " - Number of files mismatch.");
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				System.out.println("\tDestination test: Bad" + " - Number of files mismatch.");
			}
			runnableClass.getLock().notifyAll();
		}

	}

	private boolean isLocationEmpty(String location)
	{
		File folder = new File(location);
		File[] files = folder.listFiles();
		for (File file : files)
		{
			if (!file.isDirectory())
			{
				return false;
			}
		}
		return true;
	}

	private boolean destinationTest(String destination, int numberOfFiles)
	{
		int count = 0;
		File folder = new File(destination);
		File[] files = folder.listFiles();
		for (File file : files)
		{
			if (!file.isDirectory())
			{
				count++;
			}
		}
		if (count == numberOfFiles)
		{
			return true;
		} else
		{
			return false;
		}
	}
}
