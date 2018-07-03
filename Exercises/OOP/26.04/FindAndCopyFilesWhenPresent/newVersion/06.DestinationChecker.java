package com.seeburger.files;

import java.io.File;

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

	public DestinationChecker(RunnableClass runnableClass, String location, String destination, int numberOfFiles)
	{
		this.runnableClass = runnableClass;
		this.location = location;
		this.destination = destination;
		this.numberOfFiles = numberOfFiles;
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
			System.out.println("Location and destination tests:");
			if (isLocationEmpty(this.location))
			{
				System.out.println("\tLocation test: Good" + " - No files present.");
			} else
			{
				System.out.println("\tLocation test: Bad" + " - Files still present in location: " + this.location);
			}
			if (destinationTest(this.destination, this.numberOfFiles))
			{
				System.out.println("\tDestination test: Good" + " - Number of files matches.");
			} else
			{
				System.out.println("\tDestination test: Bad" + " - Number of files mismatch.");
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
