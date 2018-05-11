package com.seeburger.networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jws.soap.SOAPBinding.Use;
import javax.print.attribute.standard.MediaSize.NA;

public class Server {
	
	private ExecutorService service = Executors.newFixedThreadPool(100);
	private ArrayList<String> ipAddresses;
	private ArrayList<Socket> sockets;
	private LinkedHashMap<String, Socket> mapOfConnectedUsers;
	private static ArrayList<User> registeredUsers = new ArrayList<>();
	private User user;
	
	static {
		File users = new File("registeredUsers.txt");
		try
		{
			users.createNewFile();
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try(BufferedReader reader = new BufferedReader(new FileReader("registeredUsers.txt"))) {
			
			while (true)
			{
				
				try
				{
					String[] variables = reader.readLine().split(",");
					User user = new User(variables[0], variables[1], variables[2], variables[3]);
//					System.out.println(variables[0] + variables[1] + variables[2] + variables[3]);
					registeredUsers.add(user);
					
				} catch (NullPointerException e)
				{
					break;
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
	
	
	public Server() {
		this.ipAddresses = new ArrayList<String>();
		this.sockets = new ArrayList();
		this.mapOfConnectedUsers = new LinkedHashMap<>();
		
	}
	
	public void startMultiThreadedServer(int port) throws InterruptedException {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			
			while (true) {
				
				Socket socket = serverSocket.accept();
				if (socket.isConnected()) {
					sockets.add(socket);
					logInOrRegister(socket);
					if (user == null) {
						continue;
					}
					mapOfConnectedUsers.putIfAbsent(this.user.getName(), socket);
					saveUsers();
					
					RunnableClass runnableClass = new RunnableClass(port, socket, serverSocket, this.sockets, this.user.getName(), mapOfConnectedUsers);
					service.execute(runnableClass);
					if (!ipAddresses.contains(socket.getInetAddress().toString())) {
						ipAddresses.add(socket.getInetAddress().toString());
						
					}
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void saveUsers() throws IOException {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("registeredUsers.txt"))) {
			for (int i = 0; i < registeredUsers.size(); i++)
			{
				writer.write(registeredUsers.get(i).toString());
				writer.newLine();
				
			}
		}
	}
	
	
	private void logInOrRegister(Socket socket) throws IOException {
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());  
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		
		dataOutputStream.writeUTF("Press 1 to log in.\nPress 2 to create a new account.\nPress 3 to exit.");
		dataOutputStream.flush();
		int choice = Integer.parseInt(dataInputStream.readUTF());
		String name;
		String email;
		String password;
		switch (choice)
		{
			case 1:
				dataOutputStream.writeUTF("Enter your email: ");
				email = dataInputStream.readUTF();
				dataOutputStream.writeUTF("Enter your password: ");
				password = dataInputStream.readUTF();
				User user2 = findUserToLogIn(email, password);
				if (user2 != null) {
					dataOutputStream.writeUTF("Login complete.");
					this.user = user2;
					return;
				} else {
					dataOutputStream.writeUTF("Wrong username or password. Try again.");
					logInOrRegister(socket);
					return;
					
				}

			case 2:
				dataOutputStream.writeUTF("Enter a name: ");
				name = dataInputStream.readUTF();
				dataOutputStream.writeUTF("Enter a password: ");
				password = dataInputStream.readUTF();
				dataOutputStream.writeUTF("Enter your email: ");
				email = dataInputStream.readUTF();
				if (checkForDuplicate(name, email) == null) {
					User user = new User(socket.getInetAddress().toString(), name, password, email);					
					registeredUsers.add(user);
					dataOutputStream.writeUTF("User " + name  + " added.");
					this.user = user;
					return;
				} else {
					dataOutputStream.writeUTF("User already exists. Try again.");
					logInOrRegister(socket);
					return;
				}
				
				
			case 3:
				dataOutputStream.writeUTF("exit");
//				socket.close();
				break;
				
			
		default:
			dataOutputStream.writeUTF("Wront input. Try again.");
			logInOrRegister(socket);
			return;
			
		}
	
	}
	
	private User checkForDuplicate(String name, String email) {
		for (User user : registeredUsers) {
			if (user.getName().equals(name) && user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}
	
	private User findUserToLogIn(String email, String password) {
		for (User user : registeredUsers) {
//			System.out.println(user.toString());
			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
		}

}
