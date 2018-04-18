package com.seeburger.sort;

import java.util.Scanner;

public class StringsComparison
{
	private static Scanner scanner = new Scanner(System.in);
	
	
	public static String[] generateArrayToCheck()  //set the size and populate with input strings
	{
		System.out.println("Enter the size of the array: ");
		int size = scanner.nextInt();
		String[] toReturn = new String[size];
		
		for (int i = 0; i < toReturn.length; i++)
		{
			System.out.println("Enter string " + (i + 1) + ": ");
			toReturn[i] = scanner.nextLine();  // get input string from console and set it inside the array
			
		}
		System.out.println("Entry complete.");
		
		return toReturn;
	}
	
	
	public static String enterStringToCheck()
	{
		
		System.out.print("Enter the string you want to check: ");
		return scanner.nextLine();
		
	}
	
	
	public static void checkArrayForString(String[] arrayToCheck, String toCheck)  // checks the array for a certain string
	{
		int count = 0;
		for (int i = 0; i < arrayToCheck.length; i++)
		{
			if (arrayToCheck[i].equals(toCheck)) {
				System.out.println("String: " + toCheck + " found at index: " + i);
				count++;
			}
		}
		if (count > 0) {
			System.out.println("String: " + toCheck + " found " + count + " times.");
		} else {
			System.out.println("No records of " + toCheck + " have been found.");
		}
	}
}
