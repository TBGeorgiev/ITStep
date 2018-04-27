package com.seeburger.networking;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
	
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	
	
	public Server() {
		
	}
	
	public void startServer(int port)
	{
		try
		{
			ServerSocket serverSocket = new ServerSocket(port);
			String mString = "";
			while (true)
			{
				Socket socket = serverSocket.accept();
				DataInputStream dInputStream = new DataInputStream(socket.getInputStream());
				mString = (String)dInputStream.readUTF();
				if (mString.equals("zdr kp")) {
					break;
				}
				System.out.println("message= " + mString);
			}
			serverSocket.close();
			
		} catch (Exception e)
		{
			// TODO: handle exception
		}
	}

}
