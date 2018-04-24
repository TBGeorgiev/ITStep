package com.seeburger.students;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ListOfStudents
{

	private TreeMap<Integer, ArrayList<Student>> mapWithStudents = new TreeMap<>();
	private ArrayList<Student> studentsList = new ArrayList<>();
	private Scanner scanner = new Scanner(System.in);
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public ArrayList<Student> sortStudents()
	{
		ArrayList<Student> studentsSorted = (ArrayList<Student>) this.studentsList.stream().sorted((e1, e2) ->
		{
			Integer result = e1.getFirstName().compareTo(e2.getFirstName());
			if (result == 0)
			{
				result = e1.getLastName().compareTo(e2.getLastName());
			}

			return result;
		}).collect(Collectors.toList());

		return studentsSorted;
	}
	
	
	
	
	public ArrayList<Student> sortByAverageGrade()
	{
		ArrayList<Student> sortedStudents = (ArrayList<Student>) this.studentsList.stream().sorted((e1, e2) -> {
			int averages1 = (e1.getGrade1() + e1.getGrade2() + e1.getGrade3() + e1.getGrade4()) / 4;
			int averages2 = (e2.getGrade1() + e2.getGrade2() + e2.getGrade3() + e2.getGrade4()) / 4;
			Integer result = averages1 - averages2;
			if (result > 0) {
				result = -1;
			}
			else if (result < 0) {
				result = 1;
			} else {
				result = 0;
			}
			return result;
		}).collect(Collectors.toList());
		
		return sortedStudents;
	}

	public void saveStudentsToFile(String fileLocation) throws IOException
	{
		File file = new File(fileLocation);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));

		writer.write("FN	First name	Last Name	Email	Age	Group	Grade1	Grade2	Grade3	Grade4	Phones");
		writer.newLine();
		for (Student student : this.studentsList)
		{
			writer.write(student.getfNum() + " " + student.getFirstName() + " " + student.getLastName() + " " + student.getEmail() + " " + student.getAge() + " " + student.getGroup() + " " + student.getGrade1() + " " + student.getGrade2() + " " + student.getGrade3() + " " + student.getGrade4() + " " + student.getPhone());
			writer.newLine();
			
		}
		
		writer.close();
	}

	public void addStudentsFromFile(String fileLocation) throws IOException
	{

		File file = new File(fileLocation);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		reader.readLine();
		int index = 0;
		while ((line = reader.readLine()) != null)
		{
			String[] split = line.split("\\s+");
			int facNum = Integer.parseInt(split[0]);
			String firstName = split[1];
			String lastName = split[2];
			String email = split[3];
			int age = Integer.parseInt(split[4]);
			int group = Integer.parseInt(split[5]);
			int grade1 = Integer.parseInt(split[6]);
			int grade2 = Integer.parseInt(split[7]);
			int grade3 = Integer.parseInt(split[8]);
			int grade4 = Integer.parseInt(split[9]);
			String phone = split[10];
			addStudent(facNum, firstName, lastName, email, age, group, grade1, grade2, grade3, grade4, phone);
			if (!mapWithStudents.containsKey(group)) {
				mapWithStudents.put(group, new ArrayList<>());
			}
			mapWithStudents.get(group).add(studentsList.get(index));
			index++;
		}

		reader.close();

	}
	
	public void sortyMapStudentsByGroup()
	{
		
	}
	
	

	public TreeMap<Integer, ArrayList<Student>> getMapWithStudents()
	{
		return mapWithStudents;
	}




	public void setMapWithStudents(TreeMap<Integer, ArrayList<Student>> mapWithStudents)
	{
		this.mapWithStudents = mapWithStudents;
	}




	public ListOfStudents()
	{
		this.studentsList = new ArrayList<>();
	}

	public void printAllStudentsToConsole(ArrayList<Student> students)
	{
		System.out.println();
		System.out.println("FN | First name | Last Name | Email | Age | Group | Grade1 | Grade2 | Grade3 | Grade4 | Phone");
		System.out.println();
		for (Student student : students)
		{
			// System.out.println(student.getFirstName() + " " + student.getLastName());
			// System.out.println("FacNum: " + student.getfNum());
			System.out.println(student.toString());
		}
	}

	public void addStudent(int facNum, String firstName, String lastName, String email, int age, int group, int grade1,
			int grade2, int grade3, int grade4, String phone)
	{
		Student student = new Student();
		student.setfNum(facNum);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setAge(age);
		student.setGroup(group);
		student.setGrade1(grade1);
		student.setGrade2(grade2);
		student.setGrade3(grade3);
		student.setGrade4(grade4);
		student.setPhone(phone);
		addStudentToList(student);
	}
	
	

	public void addMultipleStudents()
	{
		System.out.println("How many students do you want to add?");
		int numberOfStudents = Integer.parseInt(scanner.nextLine());

		for (int i = 0; i < numberOfStudents; i++)
		{
			Student student = new Student();
			System.out.println("Enter first name: ");
			student.setFirstName(scanner.nextLine());
			System.out.println("Enter last name: ");
			student.setLastName(scanner.nextLine());
			System.out.println("Enter ID: ");
			student.setfNum(Integer.parseInt(scanner.nextLine()));
			addStudentToList(student);
			System.out.println("Student added.");
		}
		System.out.println("Entry complete.");

	}

	public ArrayList<Student> getStudentsList()
	{
		return studentsList;
	}

	public void addStudentToList(Student student)
	{
		studentsList.add(student);
	}

}
