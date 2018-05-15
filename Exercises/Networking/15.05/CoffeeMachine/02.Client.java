package com.seeburger.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client
{
	/* 
	 * Client klasa ne e nujen, a se polzva samo za testovete
	 */
	
	public Client()
	{
		try
		{
			Socket socket = new Socket("192.168.100.33", 1211);
			DataInputStream din = new DataInputStream(socket.getInputStream());
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Enter the money");
			dout.writeUTF(br.readLine());
			System.out.println("Select your drink (1-5)");
			dout.writeUTF(br.readLine());
			dout.flush();

			int count = 0;
			String answer = null;

			while (count < 3)
			{
				answer = din.readUTF();
				if (answer != null)
				{
					System.out.println(answer);
					if (answer.equals("false")) {
						count = 3;
						break;
					}
					answer = null;
					count++;
				}
			}

			din.close();
			dout.close();
			socket.close();
			// System.exit(0);

		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
