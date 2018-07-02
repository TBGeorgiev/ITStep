package com.seeburger.fileTransferAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.LinkedList;

public class ConsistencyChecker implements Runnable
{
	// private File[] files;
	private RunnableClass runnableClass;
	private String destinationString;
	private LinkedList<String> bytesList;

	public ConsistencyChecker(String destinationString, RunnableClass runnableClass, LinkedList<String> fileByteStrings)
	{
		// this.files = files;
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

	private byte[] createChecksum(String filename) throws Exception
	{
		InputStream fis = new FileInputStream(filename);

		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("MD5");
		int numRead;

		do
		{
			numRead = fis.read(buffer);
			if (numRead > 0)
			{
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);

		fis.close();
		return complete.digest();
	}

	private void checkFiles(String destinationString, LinkedList<String> bytesList2) throws Exception
	{
		ArrayList<String> sourceByteStrings = new ArrayList<String>();
		File folder = new File(destinationString);
		File[] files = folder.listFiles();
		System.out.println(files.length);
		for (File file : files)
		{
			if (!file.isDirectory())
			{
				String result = "";
				byte[] b1 = createChecksum(file.getAbsolutePath());
				for (int j = 0; j < b1.length; j++)
				{
					result += Integer.toString((b1[j] & 0xff) + 0x100, 16).substring(1);
				}
				sourceByteStrings.add(result);
			}
		}

		for (int i = 0; i < bytesList2.size(); i++)
		{
			if (!bytesList2.get(i).equals(sourceByteStrings.get(i)))
			{
				System.out.println("Corrupted file");
			} else
			{
				System.out.println("Good");
			}
		}
	}
}
