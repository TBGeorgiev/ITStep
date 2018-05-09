package com.seeburger.networking;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.print.attribute.standard.MediaSize.NA;

public class Server {
	
	private ExecutorService service = Executors.newFixedThreadPool(100);
	private ArrayList<String> ipAddresses;
	private ArrayList<Socket> sockets;
	private static ArrayList<User> registeredUsers = new ArrayList<>();
	
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
					String name = logInOrRegister(socket);
					
					RunnableClass runnableClass = new RunnableClass(port, socket, serverSocket, this.sockets, name);
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
	
	private String logInOrRegister(Socket socket) throws IOException {
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());  
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		
		dataOutputStream.writeUTF("Press 1 to log in.\nPress 2 to create a new account.");
		dataOutputStream.flush();
		int choice = Integer.parseInt(dataInputStream.readUTF());
		
		switch (choice)
		{
			case 1:
			
			break;

			case 2:
				dataOutputStream.writeUTF("Enter a name: ");
				String name = dataInputStream.readUTF();
				dataOutputStream.writeUTF("Enter a password: ");
				String password = dataInputStream.readUTF();
				dataOutputStream.writeUTF("Enter your email: ");
				String email = dataInputStream.readUTF();
				User user = new User(socket.getInetAddress().toString(), name, password, email);
				registeredUsers.add(user);
				System.out.println("User " + name  + " added.");
				return name;
			
		default:
			break;
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
			System.out.println("client says: "+str);  
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
