package com.seeburger.oop;

import com.seeburger.exceptions.InvalidAgeException;

public class UniversityStudent extends Student
{

	public UniversityStudent(String name, int age, char gender) throws InvalidAgeException
	{
		super(name, age, gender);
		// TODO Auto-generated constructor stub
	}
	
	public void study()
	{
		System.out.println("Student: " + super.getName() + " is studying.");
	}

	@Override
	public void printResults() throws InvalidAgeException
	{
		// TODO Auto-generated method stub
		super.printResults();
		System.out.println(" Type: Unistudent");
	}
	
	
	

}
