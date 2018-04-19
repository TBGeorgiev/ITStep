package com.seeburger.filemanipulation;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionToDatabase
{
	private static ConnectionToDatabase instance = null;
	
	public static ConnectionToDatabase getInstance() {
		if (instance == null) {
			instance = new ConnectionToDatabase();
		}
		return instance;
	}
	{
		
	}
	
	public Connection databaseConnection()
	{
		
		
		
		Connection connection = null;
		
		try
		{
			Class.forName("org.sqlite.JDBC");
			File f = new File(System.getProperty("user.dir") + File.separator + "lib");
			if (!f.exists()) {
				f.mkdir();
			}
			connection = DriverManager.getConnection("jdbc:sqlite:lib\\test.db");
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		System.out.println("Process complete.");
		return connection;
	}

}
