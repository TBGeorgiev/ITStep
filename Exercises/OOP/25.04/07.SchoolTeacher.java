package com.seeburger.oop;

import com.seeburger.exceptions.InvalidAgeException;

public class SchoolTeacher extends Teacher
{
	
	private String schoolName;

	public SchoolTeacher(String name, int age, char gender, String schoolName) throws InvalidAgeException
	{
		super(name, age, gender);
		this.schoolName = schoolName;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printResults() throws InvalidAgeException 
	{
		// TODO Auto-generated method stub
		super.printResults();
		System.out.println(" Type: School Teacher School: " + schoolName);
	}


}
