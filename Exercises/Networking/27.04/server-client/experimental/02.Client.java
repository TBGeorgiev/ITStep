package com.seeburger.networking;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
	
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private ExecutorService executorService = Executors.newFixedThreadPool(100);
	
	public Client() {
		
	}
	
	public void connectToServer(String ipAddress, int port) throws IOException {
		Socket s = new Socket(ipAddress , port);  
		DataInputStream din = new DataInputStream(s.getInputStream());  
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());  
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		RunnableForClient runnableForClient = new RunnableForClient(din, s);
		executorService.execute(runnableForClient);
		  
		String str="",str2="";  
		while(!str.equals("stop")){  
			str=br.readLine();  
			dout.writeUTF(str);  
			dout.flush();


			
//			str2=din.readUTF();  
//			System.out.println("Server says: "+str2);  
		}  
		executorService.shutdown();  
		dout.close();  
		din.close();
		s.close();  
		
		
		
		
		
		
//		try {
//			String toSend = "";
//			String toSend2 = "";
//			while (!(toSend = reader.readLine()).equals("stop")) {
//				
//				Socket socket = new Socket(ipAddress, port);
//				DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
//				DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//				
//				toSend2 = dataInputStream.readUTF();
//				System.out.println(toSend2);
//				
//				dataOutputStream.writeUTF(toSend);
//				dataOutputStream.flush();
//				dataOutputStream.close();
//				socket.close();
//				
//				
//			}
			
			
			
//			
//		} catch (UnknownHostException e) {
//			// TODO: handle exception
//		}
	}

}
