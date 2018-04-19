package com.seeburger.students;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfStudents
{

	private ArrayList<Student> studentsList = new ArrayList<>();
	private Scanner scanner = new Scanner(System.in);
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	

	public ListOfStudents()
	{
		this.studentsList = new ArrayList<>();
	}
	
	
	public void printAllStudentsToConsole()
	{
		System.out.println();
		System.out.println("Listing all students: ");
		System.out.println();
		for (Student student : studentsList) {
//			System.out.println(student.getFirstName() + " " + student.getLastName());
//			System.out.println("FacNum: " + student.getfNum());
			System.out.println(student.toString());
		}
	}
	
	
	public void addMultipleStudents()
	{
		System.out.println("How many students do you want to add?");
		int numberOfStudents = Integer.parseInt(scanner.nextLine());
		
		
		for (int i = 0; i < numberOfStudents; i++) {
			Student student = new Student();
			System.out.println("Enter first name: ");
			student.setFirstName(scanner.nextLine());
			System.out.println("Enter last name: ");
			student.setLastName(scanner.nextLine());
			System.out.println("Enter ID: ");
			student.setfNum(Integer.parseInt(scanner.nextLine()));
			addStudentToList(student);
			System.out.println("Entry complete");
		}
		
	}
	
	public void addStudentToList(Student student)
	{
		studentsList.add(student);
	}
	
	
}
