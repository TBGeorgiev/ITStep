package com.seeburger.sort;

import java.io.File;

public class FileFinder
{
	
	public static void listAllFilesFromDirectory(String directory)
	{
		File folder = new File(directory);
		
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
			if (file.isDirectory()) {
				System.out.println(file.getAbsolutePath());
				listAllFilesFromDirectory(file.getAbsolutePath());
				
			} else {
				System.out.println(file.getName());				
			}
		}
	}

}
