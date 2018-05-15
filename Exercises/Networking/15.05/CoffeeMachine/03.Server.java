package com.seeburger.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server
{	
	private ExecutorService executorService = Executors.newFixedThreadPool(4);
	
	public Server() {
		try
		{
			ServerSocket serverSocket = new ServerSocket(1211);
			while (true)
			{
				Socket socket = serverSocket.accept();
				if (socket.isConnected()) { // kat se connectne
					RequestHandler requestHandler = new RequestHandler(socket); // inicializira nishkata sus socket-a 
					executorService.execute(requestHandler); //puska nishkata
				}
			}	
		} catch (IOException e)
		{
			e.printStackTrace();
		}	
	}
}
