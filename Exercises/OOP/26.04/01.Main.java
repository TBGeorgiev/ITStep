package com.seeburger.run;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.activation.FileDataSource;
import javax.xml.transform.Source;

//import javax.swing.JOptionPane;

import com.seeburger.filemanipulation.ConnectionToDatabase;
import com.seeburger.filemanipulation.CopyFile;
import com.seeburger.filemanipulation.CreateTables;
import com.seeburger.filemanipulation.FileFinder;
import com.seeburger.filemanipulation.ReadFromPropertyFile;
import com.seeburger.sort.*;
import com.seeburger.students.ListOfStudents;
import com.seeburger.students.Student;


public class RunMain
{
	private static Scanner scanner = new Scanner(System.in);
	
	private static String[] args;
	
	private static final String fileSource = "C:\\Users\\Public\\Documents";
	
	private static volatile int count = 1;
	
	

	public static void main(String[] args) 
	{
		long startTime = System.currentTimeMillis();
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		
		CopyFile copyFile = new CopyFile(executorService);
		
		
		copyFile.findFileToCopy(fileSource, 50);
			
		executorService.shutdown();
		
		

		while (!executorService.isTerminated())
		{
			System.out.println(executorService.isTerminated());
			Runnable runnable = new Runnable()
			{
				
				@Override
				public void run()
				{
					System.out.println(count);
					count++;
					try
					{
						Thread.sleep(1000);
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			};
			
			Thread thread = new Thread(runnable);
//			thread.start();
			
			runnable.run();
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Total time: " + (endTime - startTime));
		
//		new CopyFile().findFileToCopy("C:\\Cisco Packet Tracer 7.0");
		
		
		
		
//		readStudentsFromFileAndPrint("C:\\Users\\MKA\\Documents\\eclipse\\StudentData.txt");
//		try
//		{
//			readStudentsFromFileAndPutInHashMapToPrint("C:\\Users\\MKA\\Documents\\eclipse\\StudentData.txt");
//		} catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		
//		Runnable runnable = new Runnable()
//		{
//			
//			@Override
//			public void run()
//			{
//				long start = System.currentTimeMillis();
//				System.out.println("==========================================================================================T1 - Start time: "
//				+ start);
//				File toCopy = CopyFile.findFileToCopy("C:\\Cisco Packet Tracer 7.0");
//				long end = System.currentTimeMillis();;
//				System.out.println("==========================================================================================T1 - End time: "
//				+ end);
//				System.out.println("==========================================================================================T1 - Total time: "
//				+ (end - start));
//			}
//		};
//		
//		
//		
//		Runnable runnable2 = new Runnable()
//		{
//			
//			@Override
//			public void run()
//			{
//				long start = System.currentTimeMillis();
//				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++T2 - Start time: "
//				+ start);
//				File toCopy = CopyFile.findFileToCopy("C:\\Cisco Packet Tracer 7.0");
//				long end = System.currentTimeMillis();;
//				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++T2 - End time: "
//				+ end);
//				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++T2 - Total time: " + (end - start));
//				
//			}
//		};
//		
//		runnable.run();
//		runnable2.run();
		
//		addAndListStudentsAndSaveToFile("C:\\Users\\MKA\\Documents\\eclipse\\students.txt");
//		readStudentsFromFileAndPrint("C:\\Users\\MKA\\Documents\\eclipse\\students.txt");
//		Connection connection = null;
//		try {
////			System.out.println(System.getProperty("user.dir"));
//			ConnectionToDatabase database = new ConnectionToDatabase();
//			connection = database.databaseConnection("Properties");
//			CreateTables tables = new CreateTables();
//			
//			
//			String sqlStatement = "CREATE TABLE IF NOT EXISTS Properties "
//					+ "(Key TEXT NOT NULL,"
//					+ " Value TEXT NOT NULL)";
//			
//			String sqlStatementFill = "INSERT INTO Properties VALUES ('ip:address')";
//			
//			printTable(connection, "Properties");
//			
//			
////			tables.executeSqlStatement(connection, sqlStatementFill);
//			
////			connection.commit();
//			connection.close();
//			
////			tables.createTable(connection);
////			tables.listDatabase(connection);
////			System.out.println(System.getProperty("java.version"));
//			
//			
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			System.out.println("Error");
//		}
		
	}
	
	private void findAndCopyFilesWithThreads(String searchLocation, String copyDestination)
	{
		
		
		
	}
	
	private static void printTable(Connection connection, String tableName) throws SQLException
	{
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM " + tableName;
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next())
		{
			System.out.println(resultSet.getString(1));
			System.out.println(resultSet.getString(2));
		}
		
		resultSet.close();
		statement.close();
	}
	
	
	private static void copyFile(String directoryToSearch, String destinationToCopyFile) throws IOException
	{
		
//		File toCopy = CopyFile.findFileToCopy(directoryToSearch);
//		File destFile = CopyFile.generateFileDest(destinationToCopyFile);
//		CopyFile.copeFileUsingStream(toCopy, destFile);
	}
	
	
	private static void readStudentsFromFileAndPutInHashMapToPrint(String location) throws IOException
	{
		ListOfStudents listOfStudents = new ListOfStudents();
		listOfStudents.addStudentsFromFile(location);
		TreeMap<Integer, ArrayList<Student>> toPrint = listOfStudents.getMapWithStudents();
		
		for (Map.Entry entry : toPrint.entrySet()) {
			System.out.println("Group " + entry.getKey());
			ArrayList<Student> students = (ArrayList<Student>) entry.getValue();
			for (Student student : students) {
				System.out.println(student.toString());
			}
		}
		
//		Iterator iterator = toPrint.entrySet().iterator();
//		while (iterator.hasNext())
//		{
//			Map.Entry pair = (Map.Entry)iterator.next();
//	        System.out.println("Group: " + pair.getKey());
//	        ArrayList<Student> students = (ArrayList<Student>) pair.getValue();
//	        Iterator<Student> iterator2 = students.iterator();
//	        while (iterator2.hasNext())
//			{
//				System.out.println(iterator2.next());
//			}
//	        iterator.remove();
//		}
		
		int answer = javax.swing.JOptionPane.showConfirmDialog(null, "Do you want to save the students?", null, javax.swing.JOptionPane.YES_NO_OPTION);
		if (answer == javax.swing.JOptionPane.YES_OPTION) {
			listOfStudents.saveStudentsToFile("C:\\Users\\MKA\\Documents\\eclipse\\test\\StudentsTest.txt");
			System.out.println("Save complete.");
		}
		
		
	}
	
	private static void readStudentsFromFileAndPrint(String location) throws IOException
	{
		
		ListOfStudents listOfStudents = new ListOfStudents();
		listOfStudents.addStudentsFromFile(location);
//		ArrayList<Student> sorted = listOfStudents.sortStudents();
		ArrayList<Student> sortedByAverages = listOfStudents.sortByAverageGrade();
//		ArrayList<Student> getStudents = listOfStudents.getStudentsList();
		listOfStudents.printAllStudentsToConsole(sortedByAverages);
	}
	
	private static void addAndListStudentsAndSaveToFile(String fileLoc) throws IOException
	{
		ListOfStudents listOfStudents = new ListOfStudents();
		
		listOfStudents.addMultipleStudents();
		
		ArrayList<Student> sorted = listOfStudents.sortStudents();
		
		listOfStudents.saveStudentsToFile(fileLoc);
		
		listOfStudents.printAllStudentsToConsole(sorted);
	}
	
	
	
	
	
	private static void readPropertyFile() throws IOException
	{
		ReadFromPropertyFile.readPropertyFile();
	}
	
	
	private static void listFilesFromDirectory()
	{
		System.out.println("Enter the directory's absolute path: ");
		String path = scanner.nextLine();
		FileFinder.listAllFilesFromDirectory(path, 0);
		
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
