package com.seeburger.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
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
	private boolean fileIntegrityTest;
	private boolean locationAndDestinationTest;
	private int numberOfFilesInDirectory = 0;

	public Finder(ExecutorService executorService, boolean fileIntegrityTest, boolean locationAndDestinationTest)
	{
		this.executorService = executorService;
		this.fileIntegrityTest = fileIntegrityTest;
		this.locationAndDestinationTest = locationAndDestinationTest;
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
		if (fileIntegrityTest)
		{
			LinkedHashMap<String, String> fileByteStrings = getFileBytes(files);
			ConsistencyChecker consistencyChecker = new ConsistencyChecker(finalDestinationString, runnableClass,
					fileByteStrings);
			executorService.execute(consistencyChecker);
		}
		if (locationAndDestinationTest)
		{
			int numberOfFiles = numberOfFilesInLocation(location);
			DestinationChecker destChecker = new DestinationChecker(runnableClass, location, destination,
					numberOfFiles);
			executorService.execute(destChecker);
		}
		System.out.println("Moving file/s..");
		executorService.execute(runnableClass);
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

	private int numberOfFilesInLocation(String location)
	{
		int count = 0;
		File folder = new File(location);
		File[] files = folder.listFiles();
		for (File file : files) {
			if (!file.isDirectory()) {
				count++;
			}
		}
		return count;
	}

	private LinkedHashMap<String, String> getFileBytes(File[] files)
	{
		LinkedHashMap<String, String> fileBytesArrayList = new LinkedHashMap<String, String>();
		for (File file1 : files)
		{
			if (!file1.isDirectory())
			{
				String result = ChecksumUtilities.getMD5(file1);
				fileBytesArrayList.put(file1.getName(), result);
			}
		}
		return fileBytesArrayList;
	}
}
