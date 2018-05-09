package com.seeburger.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.seeburger.networking.Client;
import com.seeburger.networking.Server;

public class Main implements Serializable {

	public static void main(String[] args) throws IOException, InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
//		System.out.println("Enter your name: ");
//		String name = reader.readLine();
		
//		System.out.println("Please enter the IP you want to connect to: ");
//		String ip = reader.readLine();
		System.out.println("Enter the port: ");
		int port = Integer.parseInt(reader.readLine());
//		
//		Client client = new Client();
//		client.connectToServer(ip, port);
//		
		Server server = new Server();
//		server.startServerNew(port);
		server.startMultiThreadedServer(port);
		
//		
	}

}
