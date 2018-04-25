package com.seeburger.oop;

import javax.xml.bind.annotation.XmlList;

import com.seeburger.exceptions.InvalidAgeException;

public class UniversityTeacher extends Teacher
{
	
	private String universityName;

	public UniversityTeacher(String name, int age, char gender, String uniName) throws InvalidAgeException
	{
		super(name, age, gender);
		this.universityName = uniName;
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void printResults() throws InvalidAgeException
	{
		// TODO Auto-generated method stub
		super.printResults();
		System.out.println(" Type: University Teacher University: " + universityName);
	}
	
	

}
