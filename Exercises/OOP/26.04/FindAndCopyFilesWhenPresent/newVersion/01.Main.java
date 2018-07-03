package com.seeburger.files;

import java.io.IOException;
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
 */

public class Main
{
	public static void main(String[] args)
	{
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		
		//first boolean is for file integrity tests (MD5 checksum test)
		//second boolean is for location and destination tests
		Finder finder = new Finder(executorService, true, true);
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
		executorService.shutdown();
	}
}
