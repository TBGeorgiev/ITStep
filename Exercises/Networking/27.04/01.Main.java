package com.seeburger.run;

import com.seeburger.networking.Client;
import com.seeburger.networking.Server;

public class Main
{
	public static void main(String[] args)
	{
		Runnable runnableClient = new Runnable()
		{
			
			@Override
			public void run()
			{
				Client client = new Client();
				client.connectToServer("192.168.100.52", 6000);
				
				// TODO Auto-generated method stub
				
			}
		};
		
		
		Runnable runnableServer = new Runnable()
		{
			
			@Override
			public void run()
			{
				Server server = new Server();
				server.startServer(6000);
				// TODO Auto-generated method stub
				
			}
		};
		
		Thread threadClient = new Thread(runnableClient);
		Thread threadServer = new Thread(runnableServer);
		
		threadClient.start();
		threadServer.start();
//		String ip = "192.168.100.52";
	}

}
