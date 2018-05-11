package com.seeburger.networking;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class RunnableForClient implements Runnable
{
	private DataInputStream dataInputStream;
	private Socket socket;

	public RunnableForClient(DataInputStream dataInputStream, Socket socket)
	{
		this.dataInputStream = dataInputStream;
		this.socket = socket;
	}

	@Override
	public void run()
	{
		String string = null;
		while (true)
		{
			try
			{
				string = dataInputStream.readUTF();
				if (string != null && string.equals("exit"))
				{
					System.out.println("Closing chat.");
					try
					{
						Thread.currentThread().sleep(500);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					System.exit(0);
					// socket.close();
					// dataInputStream.close();
					break;
				} else if (string != null)
				{
					System.out.println(string);
					string = null;

				}
			} catch (IOException e)
			{
				System.out.println("IOFException inside RunnableClient");
				e.printStackTrace();
				break;
			}
		}
	}
}
