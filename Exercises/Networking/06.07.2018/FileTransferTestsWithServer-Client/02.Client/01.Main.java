package com.seeburger.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("192.168.0.103", 21000);
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		
		ListenerThread listenerRunnable = new ListenerThread(dataInputStream, socket);
		Thread listenerThread = new Thread(listenerRunnable);
		CommandsThread commandsRunnable = new CommandsThread(dataOutputStream);
		Thread commandsThread = new Thread(commandsRunnable);
		
		listenerThread.start();
		commandsThread.start();
		
	}
}
