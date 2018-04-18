package com.seeburger.filemanipulation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadFromPropertyFile
{
	
	public static void readPropertyFile() throws IOException
	{
		Properties properties = new Properties();
		String propsFile = "C:\\Users\\MKA\\Documents\\eclipse\\properties.txt"; // path to file
		InputStream iStream = null;
		
		try
		{
			iStream = new FileInputStream(propsFile);
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		properties.load(iStream);
		
		String propertiesString = properties.getProperty("ip_address");
		System.out.println(propertiesString);
		iStream.close();
	}

}
