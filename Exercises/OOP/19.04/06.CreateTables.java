package com.seeburger.filemanipulation;

import java.awt.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import com.seeburger.students.ListOfStudents;
import com.seeburger.students.Student;

public class CreateTables
{
	public void createTable(Connection connection) throws SQLException
	{
		Statement statement = connection.createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS FACULTY "
				+ "(ID INT PRIMARY KEY NOT NULL,"
				+ " NAME TEXT NOT NULL, "
				+ " FAMILY TEXT NOT NULL, "
				+ " F_NUM INT) ";
		
		statement.execute(sql);
		statement.close();
	}
	
	public void addInfo(Connection connection) throws SQLException
	{
		ListOfStudents students = new ListOfStudents();
		ArrayList<Student> list =students.getStudentsList();
		
		for (int i = 0; i < list.size(); i++)
		{
			int id = list.get(0).getfNum();
			
			
			
			Statement statement = connection.createStatement();
			
			String sql = "INSERT INTO FACULTY (ID, NAME, FAMILY, F_NUM) VALUES ('123123345', 'George', 'Macintosh', '3213123')";
			statement.execute(sql);
			statement.close();
		}
		
	}
	
	public void dropTable(Connection connection) throws SQLException
	{
		Statement statement = connection.createStatement();
		String sql = "DROP TABLE FACULTY";
		statement.execute(sql);
		statement.close();
		
		
	}
	
	public void listDatabase(Connection connection) throws SQLException
	{
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM FACULTY";
		ResultSet rSet = statement.executeQuery(sql);
		
		while (rSet.next())
		{
			int id = rSet.getInt(1);
			String name = rSet.getString(2);
			String familyName = rSet.getString(3);
			int facNum = rSet.getInt(4);
			
			System.out.println(id + " " + name + " " + familyName + " " + facNum);
		}
		rSet.close();
		
		statement.close();
		
	}
}
