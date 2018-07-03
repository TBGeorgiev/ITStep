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

/**
 * Performs various checks about 
 * the files and the location before starting
 * a new thread from the pool to initiate
 * the file moving and/or tests depending on
 * the boolean statements in the main initialization.
 */

public class Finder
{
	private ExecutorService executorService;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private boolean emptyFolder;
	private static String DIRECTORY_TO_SEARCH = "Enter a directory to search:";
	private static String MOVE_DESTINATION = "Enter the destination you want to move the files to:";
	private Logger logger = Logger.getLogger("FileLog");
	private FileHandler fHandler;
	private String finalDestinationString;
	private boolean fileIntegrityTest;
	private boolean locationAndDestinationTest;

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

	//starts the main transfer method
	public void transferFiles() throws IOException, InterruptedException
	{
		System.out.println(DIRECTORY_TO_SEARCH);
		String directory = reader.readLine();
		System.out.println(MOVE_DESTINATION);
		String destination = reader.readLine();
		finalDestinationString = destination;
		lookForFiles(directory, destination);
	}

	//performs checks and starts a file moving thread if files are present
	public void lookForFiles(String location, String destination) throws InterruptedException, IOException
	{
		File folder = new File(location);
		//checks if location exists
		if (!folder.exists())
		{
			System.out.println("Directory doesn't exist! Try again with a new directory:");
			location = reader.readLine();
			lookForFiles(location, destination);
			return;
		}
		File[] files = folder.listFiles();
		//if no files are present in the directory
		//it prints to the console once and checks
		//for new files every 100 ms
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
		//when files are present it resumes here
		emptyFolder = false;
		//initiates file mover thread
		RunnableClass runnableClass = new RunnableClass(location, destination, logger);
		
		//starts MD5 checksum test if enabled
		if (fileIntegrityTest)
		{
			LinkedHashMap<String, String> fileByteStrings = getFileBytes(files);
			ConsistencyChecker consistencyChecker = new ConsistencyChecker(finalDestinationString, runnableClass,
					fileByteStrings);
			executorService.execute(consistencyChecker);
		}
		
		//starts location and destination test if enabled
		if (locationAndDestinationTest)
		{
			int numberOfFiles = numberOfFilesInLocation(location);
			DestinationChecker destChecker = new DestinationChecker(runnableClass, location, destination,
					numberOfFiles);
			executorService.execute(destChecker);
		}
		System.out.println("Moving file/s..");
		//starts file mover thread
		executorService.execute(runnableClass);
		continueOperations(runnableClass);
	}

	//main thread asks the user if he wants to start a new
	//file moving thread while the other one is still moving a file/s
    //or if he wants to stop the current operation
	//if the user decides to stop (by typing 'end') - the program
	//will stop after the moving of the current file is finished
	//to prevent file corruption
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
