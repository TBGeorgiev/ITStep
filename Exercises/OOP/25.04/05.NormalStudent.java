package com.seeburger.oop;

import com.seeburger.exceptions.InvalidAgeException;

public class NormalStudent extends Student
{

	public NormalStudent(String name, int age, char gender) throws InvalidAgeException
	{
		super(name, age, gender);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printResults() throws InvalidAgeException 
	{
		// TODO Auto-generated method stub
		super.printResults();
		System.out.println(" Type: Normal Student");
	}
	
	

}
