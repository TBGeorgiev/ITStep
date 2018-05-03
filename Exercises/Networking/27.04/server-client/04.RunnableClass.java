package com.seeburger.networking;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableClass implements Runnable {
	private int port;
	private Socket socket;
	private ServerSocket serverSocket;
	private ExecutorService executorService = Executors.newFixedThreadPool(100);
	
	
	public RunnableClass(int port, Socket socket, ServerSocket serverSocket) {
		this.port = port;
		this.socket = socket;
		this.serverSocket = serverSocket;
	}

	@Override
	public void run() {
		startServer(this.socket, this.serverSocket);
		
	}
	
	
	public void startServer(Socket socket, ServerSocket ss) {
		try {
			
//			ServerSocket ss=new ServerSocket(port);  
//			Socket s=ss.accept();  
			DataInputStream din=new DataInputStream(socket.getInputStream());  
			DataOutputStream dout=new DataOutputStream(socket.getOutputStream());  
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
			  
			String str="",str2="";  
			while(!str.equals("stop")){  
				str=din.readUTF();  
				System.out.println("client says: "+str);  
				str2 = str;
//				str2=br.readLine();  
				dout.writeUTF(str2);  
				dout.flush();  
			}  
			din.close();  
			socket.close();  
			ss.close();  
			
//			String mString = "";
//			String string2 = "";
//			
//			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
//			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//			mString = (String)dataInputStream.readUTF();
//			
//			string2 = mString;
//			dataOutputStream.writeUTF(mString);
//			dataOutputStream.flush();
////			if (mString.equals("stop")) {
////				break;
////			}
//			System.out.println(socket.getInetAddress().toString() + ": " + Thread.currentThread().getName() + "message= " +  mString);
//			
////			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
////			dataOutputStream.writeUTF(mString);
////			dataOutputStream.flush();
////			dataOutputStream.close();
//			
//			
////			ChatWindowWatcher chatWindowWatcher = new ChatWindowWatcher(socket.getInetAddress().toString() + ": " + mString);
////			executorService.execute(chatWindowWatcher);
//			
//			
//			dataInputStream.close();
//			while (true) {
//				
//				
//				
//				
////				socket = serverSocket.accept();
//
//				
//				
//			}
//			System.out.println("broke out of while loop in runnable");
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	
	

}
