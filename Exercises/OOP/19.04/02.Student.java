package com.seeburger.students;

public class Student
{
	private int fNum;
	private String firstName;
	private String lastName;
	


	public Student(int fNum, String firstName, String lastName)
	{
		this.fNum = fNum;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	
	
	
	


	@Override
	public String toString()
	{
		return firstName + " " + lastName + "\nFac Num: " + fNum;
	}








	public Student()
	{
		
	}






	public int getfNum()
	{
		return fNum;
	}
	
	
	public void setfNum(int fNum)
	{
		this.fNum = fNum;
	}
	
	
	public String getFirstName()
	{
		return firstName;
	}
	
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	
	public String getLastName()
	{
		return lastName;
	}
	
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	
	

}
