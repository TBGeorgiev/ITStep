package com.seeburger.oop;

import com.seeburger.exceptions.InvalidAgeException;

public class Main
{
	public static void main(String[] args)
	{
		UniversityStudent universityStudent;
		try
		{
			universityStudent = new UniversityStudent("George", 33, 'm');
			universityStudent.printResults();
			Person uniTeacher = new UniversityTeacher("Michael", 50, 'm', "Westpoint");
			uniTeacher.printResults();
			
			Person uniTeacherTwo = new SchoolTeacher("Carol", 17, 'f', "Hollywood");
			uniTeacherTwo.printResults();
			
			UniversityStudent student = new UniversityStudent("Didi", 23, 'f');
			student.printResults();
			student.study();
		} catch (InvalidAgeException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
