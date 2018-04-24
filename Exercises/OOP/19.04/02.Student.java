package com.seeburger.students;

public class Student
{
	private int fNum;
	private String firstName;
	private String lastName;
	private String email;
	private int age;
	private int group;
	private int grade1;
	private int grade2;
	private int grade3;
	private int grade4;
	private String phone;
	

	public Student(int fNum, String firstName, String lastName, String email, int age, int group, int grade1,
			int grade2, int grade3, int grade4, String phone)
	{
		super();
		this.fNum = fNum;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
		this.group = group;
		this.grade1 = grade1;
		this.grade2 = grade2;
		this.grade3 = grade3;
		this.grade4 = grade4;
		this.phone = phone;
	}








	@Override
	public String toString()
	{
		return fNum + " | " + firstName + " | " + lastName + " | " + email + " | " + age + " | " +
	group + " | " + grade1 + " | " + grade2 + " | " + grade3 + " | " + grade4 + " | " + phone;
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








	public String getEmail()
	{
		return email;
	}








	public void setEmail(String email)
	{
		this.email = email;
	}








	public int getAge()
	{
		return age;
	}








	public void setAge(int age)
	{
		this.age = age;
	}








	public int getGroup()
	{
		return group;
	}








	public void setGroup(int group)
	{
		this.group = group;
	}








	public int getGrade1()
	{
		return grade1;
	}








	public void setGrade1(int grade1)
	{
		this.grade1 = grade1;
	}








	public int getGrade2()
	{
		return grade2;
	}








	public void setGrade2(int grade2)
	{
		this.grade2 = grade2;
	}








	public int getGrade3()
	{
		return grade3;
	}








	public void setGrade3(int grade3)
	{
		this.grade3 = grade3;
	}








	public int getGrade4()
	{
		return grade4;
	}








	public void setGrade4(int grade4)
	{
		this.grade4 = grade4;
	}








	public String getPhone()
	{
		return phone;
	}








	public void setPhone(String phone)
	{
		this.phone = phone;
	}






	
	
	
	

}
