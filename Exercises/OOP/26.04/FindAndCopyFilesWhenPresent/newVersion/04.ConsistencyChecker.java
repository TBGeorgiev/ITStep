package com.seeburger.files;

import java.io.File;
import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConsistencyChecker implements Runnable
{
	private RunnableClass runnableClass;
	private String destinationString;
	private LinkedHashMap<String, String> bytesList;

	public ConsistencyChecker(String destinationString, RunnableClass runnableClass,
			LinkedHashMap<String, String> fileByteStrings)
	{
		this.runnableClass = runnableClass;
		this.destinationString = destinationString;
		this.bytesList = fileByteStrings;
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
					e.printStackTrace();
				}
			}
			try
			{
				checkFiles(destinationString, bytesList);
			} catch (Exception e)
			{
				e.printStackTrace();
			}

			runnableClass.getLock().notifyAll();
		}
	}

	private void checkFiles(String destinationString, LinkedHashMap<String, String> bytesList2) throws Exception
	{
		ArrayList<String> sourceByteStrings = new ArrayList<String>();
		File folder = new File(destinationString);
		File[] files = folder.listFiles();
		for (File file : files)
		{
			if (!file.isDirectory())
			{
				String result = ChecksumUtilities.getMD5(file);
				sourceByteStrings.add(result);
			}
		}

		int index = 0;
		System.out.println("File integrity tests:");
		for (Map.Entry<String, String> map : bytesList2.entrySet())
		{
			if (map.getValue().equals(sourceByteStrings.get(index)))
			{
				System.out.println("\tGood file: " + map.getKey());
			} else
			{
				System.out.println("\tBad file: " + map.getKey());
			}
			index++;
		}
	}
}
