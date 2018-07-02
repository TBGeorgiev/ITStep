package com.seeburger.fileTransferAutomation;

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
		while (!runnableClass.isMovingFinished())
		{
			try
			{
				Thread.sleep(100);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		try
		{
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		try
		{
			checkFiles(destinationString, bytesList);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		for (Map.Entry<String, String> map : bytesList2.entrySet())
		{
//			System.out.println(map.getValue() + " : " + sourceByteStrings.get(index));
			if (map.getValue().equals(sourceByteStrings.get(index)))
			{
				System.out.println("Good");
			} else
			{
				System.out.println("Bad file " + map.getKey());
			}
			 index++;
		}
	}
}
