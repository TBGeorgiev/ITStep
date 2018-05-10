package com.seeburger.networking;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableClass implements Runnable {
	private Socket socket;
	private ServerSocket serverSocket;
	private String name;
	
	private ArrayList<Socket> sockets;
	
	
	public RunnableClass(int port, Socket socket, ServerSocket serverSocket, ArrayList<Socket> sockets, String name) {
		this.name = name;
		this.socket = socket;
		this.serverSocket = serverSocket;
		this.sockets = sockets;
	}

	@Override
	public void run() {
		startServerThread(this.socket, this.serverSocket);
		
	}
	
	
	public void startServerThread(Socket socket, ServerSocket ss) {
		try {
			
			
			
//			ServerSocket ss=new ServerSocket(port);  
//			Socket s=ss.accept();  
			DataInputStream din = new DataInputStream(socket.getInputStream());  
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());  
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
			String joined = name + " has joined the chat.";
			System.out.println(joined);
			for (int i = 0; i < sockets.size(); i++) {
				if (sockets.get(i).isClosed()) {
					sockets.remove(i);
					i--;
				} else {
					dout = new DataOutputStream(sockets.get(i).getOutputStream());
//					dout.writeUTF(socket.getInetAddress() + ": " + name + ": " + str);
					dout.writeUTF(joined);
					dout.flush();
					
				}
			}
			  
			String str = ""; 
			
			boolean quit = false;
			
//			while(!(str = din.readUTF()).equals("exit")){  
			while (!quit) {
				str = din.readUTF();
				String toSend = "";
				if (str.equals("exit")) {
					toSend = name + " has left the chat room.";
					quit = true;
				} else {
					toSend = socket.getInetAddress() + ": " + name + ": " + str;
				}
				System.out.println(toSend);
//				System.out.println(socket.getInetAddress() + " " + name + " : " + str);  
				
				for (int i = 0; i < sockets.size(); i++) {
					if (sockets.get(i).isClosed()) {
						sockets.remove(i);
						i--;
					} else {
						dout = new DataOutputStream(sockets.get(i).getOutputStream());
//						dout.writeUTF(socket.getInetAddress() + ": " + name + ": " + str);
						dout.writeUTF(toSend);
						dout.flush();
						
					}
					
				}
				
			}
//			System.out.println(name + " has left the chat room.");
			
			dout.close();
			din.close();  
//			socket.shutdownInput();
//			socket.shutdownOutput();
			socket.close();
//			ss.close();  
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	
	

}
