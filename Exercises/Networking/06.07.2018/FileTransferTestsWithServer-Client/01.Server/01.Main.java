package com.seeburger.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Moves files from one location to another
 * and logs the details of the moved files in
 * a log file and also displays a logger on the console.
 * It's possible to perform various tests, which include:
 * 1: Consistency check - compares the MD5
 * checksum of the files before and after moving.
 * 2: Location check - checks if the files have been moved
 * to the proper location and if they are the correct amount.
 * The tests can be enabled/disabled in the initialization of the 
 * Finder class in the main method below.
 * Each operation - file moving / consistency check / location check - 
 * is done by a separate thread.
 * 
 * Instructions on how to use the program:
 * 1: Insert the absolute path of a directory you want to move files from.
 * 2: Insert the absolute path of the destination directory you want to move the files to.
 * 3: If the source directory is empty - the program will wait for files to arrive. 
 * After the file transfer is executed - you can enter 'y' to start a new file moving operation
 * done by a separate thread or you can enter 'end' to stop the program.
 */

public class Main
{
	public static void main(String[] args)
	{
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		
		//first boolean is for file integrity tests (MD5 checksum test)
		//second boolean is for location and destination tests
		try
		{
			ServerSocket serverSocket = new ServerSocket(21000);
			
			while (!RunnableClass.getToStop())
			{
				Socket socket = serverSocket.accept();
				if (socket.isConnected()) {
					DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
					DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
					
					dataOutputStream.writeUTF(printMainMenu());
					Finder finder = new Finder(executorService, false, false, dataOutputStream, dataInputStream);
					int choice = Integer.parseInt(dataInputStream.readUTF());
					
					finder = initializeFinder(executorService, choice, finder, dataOutputStream, dataInputStream);
					try
					{
						finder.transferFiles();
					} catch (IOException e)
					{
						e.printStackTrace();
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					
				}
			}
			serverSocket.close();
			executorService.shutdown();
			
						
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	private static Finder initializeFinder(ExecutorService executorService, int choice, Finder finder, DataOutputStream dataOutputStream, DataInputStream dataInputStream)
	{
		switch (choice)
		{
		case 1:
			finder = new Finder(executorService, false, false, dataOutputStream, dataInputStream);
			break;
		case 2:
			finder = new Finder(executorService, true, false, dataOutputStream, dataInputStream);
			break;
		case 3:
			finder = new Finder(executorService, false, true, dataOutputStream, dataInputStream);
			break;
			
		case 4:
			finder = new Finder(executorService, true, true, dataOutputStream, dataInputStream);
			break;

		default:
			try
			{
				dataOutputStream.writeUTF("Incorrect input. Try again.");
				initializeFinder(executorService, choice, finder, dataOutputStream, dataInputStream);
				
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			break;
		}
		return finder;
	}
	
	private static String printMainMenu() {
		return("1: Transfer file with no tests.\n2: Transfer files with MD5 Checksum tests only.\n3: Transfer files with location tests only.\n4: Transfer files with both tests enabled.");
	}
}
