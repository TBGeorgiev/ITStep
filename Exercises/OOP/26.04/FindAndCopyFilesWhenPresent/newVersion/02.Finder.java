package com.seeburger.fileTransferAutomation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Finder
{
	private ExecutorService executorService;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private boolean emptyFolder;
	private static String DIRECTORY_TO_SEARCH = "Enter a directory to search:";
	private static String MOVE_DESTINATION = "Enter the destination you want to copy the files to:";
	private Logger logger = Logger.getLogger("FileLog");
	private FileHandler fHandler;
	private String finalDestinationString;

	public Finder(ExecutorService executorService)
	{
		this.executorService = executorService;
		try
		{
			fHandler = new FileHandler("FileLog.log");
			logger.addHandler(fHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fHandler.setFormatter(simpleFormatter);
		} catch (SecurityException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// Starts main transfer method
	public void transferFiles() throws IOException, InterruptedException
	{
		System.out.println(DIRECTORY_TO_SEARCH);
		String directory = reader.readLine();
		System.out.println(MOVE_DESTINATION);
		String destination = reader.readLine();
		finalDestinationString = destination;
		lookForFiles(directory, destination);
	}

	// Performs checks and starts a file moving thread if files are present
	public void lookForFiles(String location, String destination) throws InterruptedException, IOException
	{
		File folder = new File(location);
		if (!folder.exists())
		{
			System.out.println("Directory doesn't exist! Try again with a new directory:");
			location = reader.readLine();
			lookForFiles(location, destination);
			return;
		}
		File[] files = folder.listFiles();
		if (!filesPresent(files))
		{
			if (!emptyFolder)
			{
				System.out.println("Location is empty. Waiting for files to arrive.");
			}
			emptyFolder = true;
			Thread.currentThread();
			Thread.sleep(100);
			lookForFiles(location, destination);
			return;
		}
		emptyFolder = false;
		RunnableClass runnableClass = new RunnableClass(location, destination, logger);
		LinkedList<String> fileByteStrings = getFileBytes(files);
		ConsistencyChecker consistencyChecker = new ConsistencyChecker(finalDestinationString, runnableClass,
				fileByteStrings);
		System.out.println("Moving file/s..");
		executorService.execute(runnableClass);
		executorService.execute(consistencyChecker);
		continueOperations(runnableClass);
	}

	// Main thread waits for user input to start a new operation or end
	private void continueOperations(RunnableClass runnableClass) throws IOException, InterruptedException
	{
		System.out.println("Enter 'end' to terminate the operation or enter 'y' to look for other files.");

		String toContinue = reader.readLine();
		if (toContinue.equalsIgnoreCase("y"))
		{
			System.out.println(DIRECTORY_TO_SEARCH);
			String loc = reader.readLine();
			System.out.println(MOVE_DESTINATION);
			String dest = reader.readLine();
			finalDestinationString = dest;
			lookForFiles(loc, dest);
			return;
		} else if (toContinue.equalsIgnoreCase("end"))
		{
			System.out.println("Closing...");
			runnableClass.setToStop(true);
		} else
		{
			System.out.println("Incorrect input");
			continueOperations(runnableClass);
			return;
		}
	}

	private boolean filesPresent(File[] files)
	{
		for (File file : files)
		{
			if (!file.isDirectory())
			{
				return true;
			}
		}
		return false;
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

	private LinkedList<String> getFileBytes(File[] files)
	{
		LinkedList<String> fileBytesArrayList = new LinkedList();
		for (File file1 : files)
		{
			String result = "";
			byte[] b1;
			try
			{
				b1 = createChecksum(file1.getAbsolutePath());
				for (int j = 0; j < b1.length; j++)
				{
					result += Integer.toString((b1[j] & 0xff) + 0x100, 16).substring(1);
				}
				fileBytesArrayList.add(result);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return fileBytesArrayList;
	}
}
