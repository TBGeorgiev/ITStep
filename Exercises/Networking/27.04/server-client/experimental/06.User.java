package com.seeburger.networking;

import java.io.Serializable;

public class User implements Serializable
{
	private String ipAddress;
	private String name;
	private String password;
	private String email;
	
	
	public User(String ipAddress, String name, String password, String email)
	{
		this.name = name;
		this.password = password;
		this.email = email;
		this.ipAddress = ipAddress;
	}

	
	public String getIP() {
		return ipAddress;
	}

	public String getName()
	{
		return name;
	}





	public String getPassword()
	{
		return password;
	}


	

	public String getEmail()
	{
		return email;
	}


	@Override
	public String toString()
	{
		return this.ipAddress + "," + this.name + "," + this.password + "," + this.email;
	}


	
	
	
	

}
