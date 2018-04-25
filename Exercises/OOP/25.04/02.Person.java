package com.seeburger.oop;

import com.seeburger.exceptions.InvalidAgeException;

public class Person
{
	private String name;
	private int age;
	private char gender;
	
	
	
	
	
	public String getName()
	{
		return name;
	}

	public Person(String name, int age, char gender) {
		
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	
	public void printResults() throws InvalidAgeException 
	{
		if (age < 18) {
			throw new InvalidAgeException("Invalid age.");
		}
		System.out.print("Name: " + name + " Age: " + age + " Gender: " + gender);
	}
	

	

}
