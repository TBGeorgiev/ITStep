package com.seeburger.networking;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class RunnableForClient implements Runnable {
	private DataInputStream dataInputStream;
	private Socket socket;

	public RunnableForClient(DataInputStream dataInputStream, Socket socket) {
		this.dataInputStream = dataInputStream;
		this.socket = socket;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		String string = null;
		
		while (true) {
			try {
				
				string = dataInputStream.readUTF();
				if (string != null && string.equals("exit")) {
//					socket.close();
//					dataInputStream.close();
					break;
				}
				else if (string != null) {
					System.out.println(string);
					string = null;
					
				}
//				if (string.length() > 0) {
//					string = "";
//				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("IOFException inside RunnableClient");
				e.printStackTrace();
				break;
			}
		}
		
	}

}
