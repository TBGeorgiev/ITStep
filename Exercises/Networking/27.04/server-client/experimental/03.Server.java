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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jws.soap.SOAPBinding.Use;
import javax.print.attribute.standard.MediaSize.NA;

public class Server {
	
	private ExecutorService service = Executors.newFixedThreadPool(100);
	private ArrayList<String> ipAddresses;
	private ArrayList<Socket> sockets;
	private static ArrayList<User> registeredUsers = new ArrayList<>();
	private User user;
	
	static {
		File file = new File("registeredUsers.txt");
		if (!file.exists()) {
			file.mkdir();
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
		
//		for (int i = 0; i < registeredUsers.size(); i++)
//		{
//			System.out.println(registeredUsers.get(i).toString());
//		}
	}
	
	
	public Server() {
		this.ipAddresses = new ArrayList<String>();
		this.sockets = new ArrayList();
		
	}
	
	public void startMultiThreadedServer(int port) throws InterruptedException {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			
			while (true) {
				
				Socket socket = serverSocket.accept();
				if (socket.isConnected()) {
					sockets.add(socket);
					logInOrRegister(socket);
					saveUsers();
					
					RunnableClass runnableClass = new RunnableClass(port, socket, serverSocket, this.sockets, this.user.getName());
					service.execute(runnableClass);
					if (!ipAddresses.contains(socket.getInetAddress().toString())) {
						ipAddresses.add(socket.getInetAddress().toString());
						
					}
					
//					System.out.println("Socket is connected multithread");
				}
//				Thread.currentThread().sleep(1000);
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
		
		dataOutputStream.writeUTF("Press 1 to log in.\nPress 2 to create a new account.");
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
				User user = new User(socket.getInetAddress().toString(), name, password, email);
				registeredUsers.add(user);
				dataOutputStream.writeUTF("User " + name  + " added.");
				this.user = user;
				return;
			
		default:
			break;
		}
	
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
	
	
	
	
	
	public void startServerNew(int port) throws IOException
	{
		ServerSocket ss=new ServerSocket(port);  
		Socket s=ss.accept();  
		DataInputStream din=new DataInputStream(s.getInputStream());  
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
		  
		String str="",str2="";  
		while(!str.equals("stop")){  
			str=din.readUTF();  
			System.out.println("client says: " + str);  
			str2 = str;
//			str2=br.readLine();  
			dout.writeUTF(str2);  
			dout.flush();  
		}  
		din.close();  
		s.close();  
		ss.close();  
	}
	
	
	
	
	public void startServer(int port) {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			String mString = "";
			
			while (true) {
				Socket socket = serverSocket.accept();
				DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
				mString = (String)dataInputStream.readUTF();
				if (mString.equals("stop")) {
					break;
				}
				System.out.println("message= " + mString);
				
				
			}
			serverSocket.close(); //test
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
