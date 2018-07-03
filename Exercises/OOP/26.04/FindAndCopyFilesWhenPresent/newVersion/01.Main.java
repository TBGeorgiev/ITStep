package com.seeburger.files;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main
{
	public static void main(String[] args)
	{
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		
		//first boolean is for file integrity tests
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
