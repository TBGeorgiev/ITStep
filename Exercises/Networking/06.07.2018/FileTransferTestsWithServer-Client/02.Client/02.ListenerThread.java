package com.seeburger.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ListenerThread implements Runnable {
	private DataInputStream dataInputStream;
	private Socket socket;

	public ListenerThread(DataInputStream dataInputStream, Socket socket) {
		this.dataInputStream = dataInputStream;
		this.socket = socket;
	}

	@Override
	public void run() {
		String str = "";
		while (!CommandsThread.getExitStatus()) {
			try {
				str = dataInputStream.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(str);
		}

	}

}
