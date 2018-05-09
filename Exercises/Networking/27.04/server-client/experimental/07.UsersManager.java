package com.seeburger.networking;

import java.io.*;
import java.util.ArrayList;

public class UsersManager implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ArrayList<User> users;

	public UsersManager()
	{
		this.users = new ArrayList<>();
	}
	
	static {
		try(ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("users.dat")))) {
			boolean eof = false;
			while (!eof)
			{
				try {
					User user = (User) locFile.readObject();
					users.add(user);
					
				} catch (ClassNotFoundException e) {
					eof = true;
					// TODO: handle exception
				}
			}
			
			
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void addUserToListOfUsers(User user) {
		this.users.add(user);
	}
	
	public User findUser(String email, String password) {
		for (int i = 0; i < users.size(); i++)
		{
			if (users.get(i).getEmail().equals(email) && users.get(i).getPassword().equals(password)) {
				return users.get(i);
			}
		}
		return null;
	}
	
	public void saveUserList() {
		try(ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("users.dat")))) {
			for (User user : users) {
				locFile.writeObject(user);
			}
	
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
