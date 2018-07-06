package com.seeburger.fileTransferAutomation;

import java.io.DataOutputStream;
import java.io.File;
import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Thread which performs the file consistency
 * checks for the files before and after moving.
 */

public class ConsistencyChecker implements Runnable
{
	private RunnableClass runnableClass;
	private String destinationString;
	private LinkedHashMap<String, String> bytesList;
	private DataOutputStream dout;

	public ConsistencyChecker(String destinationString, RunnableClass runnableClass,
			LinkedHashMap<String, String> fileByteStrings, DataOutputStream dout)
	{
		this.runnableClass = runnableClass;
		this.destinationString = destinationString;
		this.bytesList = fileByteStrings;
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

	private void checkFiles(String destinationString, LinkedHashMap<String, String> bytesList) throws Exception
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
		this.dout.writeUTF("File integrity tests:");
//		System.out.println("File integrity tests:");
		for (Map.Entry<String, String> map : bytesList.entrySet())
		{
			if (map.getValue().equals(sourceByteStrings.get(index)))
			{
				this.dout.writeUTF("\tMatching file: " + map.getKey());
//				System.out.println("\tMatching file: " + map.getKey());
			} else
			{
				this.dout.writeUTF("\tMismatching file: " + map.getKey());
//				System.out.println("\tMismatching file: " + map.getKey());
			}
			index++;
		}
	}
}
