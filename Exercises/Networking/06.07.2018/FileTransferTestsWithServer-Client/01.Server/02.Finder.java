package com.seeburger.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Performs various checks about the files and the location before starting a
 * new thread from the pool to initiate the file moving and/or tests depending
 * on the boolean statements in the main initialization.
 */

public class Finder {
	private static String DIRECTORY_TO_SEARCH = "Enter a directory to search:";
	private static String MOVE_DESTINATION = "Enter the destination you want to move the files to:";
	private ExecutorService executorService;
	private boolean emptyFolder;
	private Logger logger = Logger.getLogger("FileLog");
	private FileHandler fHandler;
	private String finalDestinationString;
	private boolean fileIntegrityTest;
	private boolean locationAndDestinationTest;
	private DataOutputStream dout;
	private DataInputStream dataInputStream;

	public Finder(ExecutorService executorService, boolean fileIntegrityTest, boolean locationAndDestinationTest,
			DataOutputStream dout, DataInputStream dataInputStream) {
		this.executorService = executorService;
		this.fileIntegrityTest = fileIntegrityTest;
		this.locationAndDestinationTest = locationAndDestinationTest;
		this.dout = dout;
		this.dataInputStream = dataInputStream;
		try {
			fHandler = new FileHandler("FileLog.log");
			logger.addHandler(fHandler);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fHandler.setFormatter(simpleFormatter);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// starts the main transfer method
	public void transferFiles() throws IOException, InterruptedException {
		this.dout.writeUTF(DIRECTORY_TO_SEARCH);
		String directory = dataInputStream.readUTF();
		this.dout.writeUTF(MOVE_DESTINATION);
		String destination = dataInputStream.readUTF();
		finalDestinationString = destination;
		lookForFiles(directory, destination);
	}

	// performs checks and starts a file moving thread if files are present
	public void lookForFiles(String location, String destination) throws InterruptedException, IOException {
		File folder = new File(location);
		// checks if location exists
		if (!folder.exists()) {
			this.dout.writeUTF("Directory doesn't exist! Try again with a new directory:");
			location = dataInputStream.readUTF();
			lookForFiles(location, destination);
			return;
		}
		File[] files = folder.listFiles();
		// if no files are present in the directory
		// it prints to the console once and checks
		// for new files every 100 ms
		if (!filesPresent(files)) {
			if (!emptyFolder) {
				this.dout.writeUTF("Location is empty. Waiting for files to arrive.");
			}
			emptyFolder = true;
			Thread.currentThread();
			Thread.sleep(100);
			lookForFiles(location, destination);
			return;
		}
		// when files are present it resumes here
		emptyFolder = false;
		// initiates file mover thread
		RunnableClass runnableClass = new RunnableClass(location, destination, logger, this.dout);

		// starts MD5 checksum test if enabled
		if (fileIntegrityTest) {
			LinkedHashMap<String, String> fileByteStrings = getFileBytes(files);
			ConsistencyChecker consistencyChecker = new ConsistencyChecker(finalDestinationString, runnableClass,
					fileByteStrings, this.dout);
			executorService.execute(consistencyChecker);
		}

		// starts location and destination test if enabled
		if (locationAndDestinationTest) {
			int numberOfFiles = numberOfFilesInLocation(location);
			DestinationChecker destChecker = new DestinationChecker(runnableClass, location, destination, numberOfFiles,
					this.dout);
			executorService.execute(destChecker);
		}
		dout.writeUTF("Moving file/s..");
		// starts file mover thread
		executorService.execute(runnableClass);
		continueOperations(runnableClass);
	}

	// main thread asks the user if he wants to start a new
	// file moving thread while the other one is still moving a file/s
	// or if he wants to stop the current operation
	// if the user decides to stop (by typing 'end') - the program
	// will stop after the moving of the current file is finished
	// to prevent file corruption
	private void continueOperations(RunnableClass runnableClass) throws IOException, InterruptedException {
		this.dout.writeUTF("Enter 'end' to terminate the operation or enter 'y' to look for other files.");

		String toContinue = this.dataInputStream.readUTF();
		if (toContinue.equalsIgnoreCase("y")) {
			this.dout.writeUTF(DIRECTORY_TO_SEARCH);
			String loc = this.dataInputStream.readUTF();
			this.dout.writeUTF(MOVE_DESTINATION);
			String dest = this.dataInputStream.readUTF();
			finalDestinationString = dest;
			lookForFiles(loc, dest);
			return;
		} else if (toContinue.equalsIgnoreCase("end")) {
			this.dout.writeUTF("Closing..");
			runnableClass.setToStop(true);
		} else {
			this.dout.writeUTF("Incorrect input.");
			continueOperations(runnableClass);
			return;
		}
	}

	private boolean filesPresent(File[] files) {
		for (File file : files) {
			if (!file.isDirectory()) {
				return true;
			}
		}
		return false;
	}

	private int numberOfFilesInLocation(String location) {
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

	private LinkedHashMap<String, String> getFileBytes(File[] files) {
		LinkedHashMap<String, String> fileBytesArrayList = new LinkedHashMap<String, String>();
		for (File file1 : files) {
			if (!file1.isDirectory()) {
				String result = ChecksumUtilities.getMD5(file1);
				fileBytesArrayList.put(file1.getName(), result);
			}
		}
		return fileBytesArrayList;
	}
}
