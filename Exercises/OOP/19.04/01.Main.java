package com.seeburger.run;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.seeburger.filemanipulation.CopyFile;
import com.seeburger.filemanipulation.ReadFromPropertyFile;
import com.seeburger.sort.*;
import com.seeburger.students.ListOfStudents;
import com.seeburger.students.Student;


public class RunMain
{
	private static Scanner scanner = new Scanner(System.in);
	
	private static String[] args;

	public static void main(String[] args) throws IOException
	{
		
		
	}
	
	
	private static void copyFile(String directoryToSearch, String destinationToCopyFile) throws IOException
	{
		File toCopy = CopyFile.findFileToCopy(directoryToSearch);
		File destFile = CopyFile.generateFileDest(destinationToCopyFile);
		CopyFile.copeFileUsingStream(toCopy, destFile);
	}
	
	
	private static void addAndListStudents()
	{
		ListOfStudents listOfStudents = new ListOfStudents();
		
		listOfStudents.addMultipleStudents();
		
		listOfStudents.printAllStudentsToConsole();
	}
	
	
	
	
	
	private static void readPropertyFile() throws IOException
	{
		ReadFromPropertyFile.readPropertyFile();
	}
	
	
	private static void listFilesFromDirectory()
	{
		System.out.println("Enter the directory's absolute path: ");
		String path = scanner.nextLine();
		FileFinder.listAllFilesFromDirectory(path);
		
	}
	
	
	private static void runSelectionSort()
	{
		int[] toSort = BubbleSort.generateRandomArray();
		SelectionSort.selectionSortOnArrayOfIntegers(toSort);
		SelectionSort.printArray(toSort);
	}
	
	private static void runBubbleSort()
	{
		int[] toSortAndPrint = BubbleSort.generateRandomArray();
		BubbleSort.sortArrayUsingBubbleSort(toSortAndPrint);
		BubbleSort.printSortedArray(toSortAndPrint);
	}
	
	private static void runStringComparison()
	{
		String[] arrayToCheck = StringsComparison.generateArrayToCheck();
		String toCheckForInArray = StringsComparison.enterStringToCheck();
		StringsComparison.checkArrayForString(arrayToCheck, toCheckForInArray);
	}
	
	
	private static void runMinAndMax()
	{
		MinAndMax.main(args);
	}
}
