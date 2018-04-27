package com.seeburger.networking;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client
{
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public Client() {
		
	}
	
	public void connectToServer(String ipAddress, int port)
	{
		try
		{
			String toSend = "";
			while (!(toSend = reader.readLine()).equals("end"))
			{
				Socket socket = new Socket(ipAddress, port);  //192.168.100.52               :               443
				DataOutputStream dOutputStream = new DataOutputStream(socket.getOutputStream());
						
				dOutputStream.writeUTF(toSend);
				dOutputStream.flush();
				dOutputStream.close();
				socket.close();					
				
			}
			
			
			
		} catch (UnknownHostException e)
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
