package com.seeburger.networking;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableClass implements Runnable {
	private Socket socket;
	private ServerSocket serverSocket;
	private String name;
	
	private ArrayList<Socket> sockets;
	private LinkedHashMap<String, Socket> mapWithConnectedUsers;

	
	
	public RunnableClass(int port, Socket socket, ServerSocket serverSocket, ArrayList<Socket> sockets, String name, LinkedHashMap<String, Socket> mapWithConnectedUsers) {
		this.name = name;
		this.socket = socket;
		this.serverSocket = serverSocket;
		this.sockets = sockets;
		this.mapWithConnectedUsers = mapWithConnectedUsers;
	}

	@Override
	public void run() {
		startServerThread(this.socket, this.serverSocket);
		
	}
	
	
	public void startServerThread(Socket socket, ServerSocket ss) {
		try { 
			DataInputStream din = new DataInputStream(socket.getInputStream());  
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());  
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
			String joined = name + " has joined the chat.";
			System.out.println(joined);
			notifyAllUsers(dout, joined);
			  
			String str = ""; 
			
			boolean quit = false;
		
			while (!quit) {
				str = din.readUTF();
				String toSend = "";
				if (str.equals("exit")) {
					toSend = name + " has left the chat room.";
					quit = true;
				}
				else if (str.equals("/admin") && name.equals("admin")) {
					showAdminCommands(socket);
					
					
				} else {
					toSend = socket.getInetAddress() + ": " + name + ": " + str;
				}
				System.out.println(toSend);
				
				for (int i = 0; i < sockets.size(); i++) {
					if (sockets.get(i).isClosed()) {
						sockets.remove(i);
						i--;
					} else {
						dout = new DataOutputStream(sockets.get(i).getOutputStream());
						dout.writeUTF(toSend);
						dout.flush();
						
					}
					
				}
				
			}	
			dout.close();
			din.close();  
			socket.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
		
	}
	
	private void notifyAllUsers(DataOutputStream dout, String message) throws IOException {
		for (int i = 0; i < sockets.size(); i++) {
			if (sockets.get(i).isClosed()) {
				sockets.remove(i);
				i--;
			} else {
				dout = new DataOutputStream(sockets.get(i).getOutputStream());
//				dout.writeUTF(socket.getInetAddress() + ": " + name + ": " + str);
				dout.writeUTF(message);
				dout.flush();
				
			}
			
		}
	}
	
	private void showAdminCommands(Socket socket) throws NumberFormatException, IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		
		dataOutputStream.writeUTF("Available commands: \n1:List all users.\n2.Kick user.\n3.Exit menu.");
		
		int choice = Integer.parseInt(dataInputStream.readUTF());
		switch (choice)
		{
		case 1:
			for (String name : mapWithConnectedUsers.keySet()) {
				dataOutputStream.writeUTF(name);
			}
			showAdminCommands(socket);
			return;
		
		case 2:
			dataOutputStream.writeUTF("Which user do you want to kick?");
			String userToKick = dataInputStream.readUTF();
			kickUser(userToKick);
			notifyAllUsers(dataOutputStream, "User " + userToKick + " has been kicked.");
			break;

		default:
			break;
		}
		
	}
	
	private void kickUser(String name) throws IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(mapWithConnectedUsers.get(name).getOutputStream());
		dataOutputStream.writeUTF("exit");
		dataOutputStream.flush();
		mapWithConnectedUsers.get(name).close();
		mapWithConnectedUsers.remove(name);
	}
	
	

}
