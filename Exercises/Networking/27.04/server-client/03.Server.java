import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	
	private ExecutorService service = Executors.newFixedThreadPool(100);
	private ArrayList<String> ipAddresses;
	
	
	public Server() {
		this.ipAddresses = new ArrayList<String>();
	}
	
	public void startMultiThreadedServer(int port) throws InterruptedException {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			
			while (true) {
				
				Socket socket = serverSocket.accept();
				if (socket.isConnected()) {
					RunnableClass runnableClass = new RunnableClass(port, socket, serverSocket);
					service.execute(runnableClass);
					if (!ipAddresses.contains(socket.getInetAddress().toString())) {
						ipAddresses.add(socket.getInetAddress().toString());
						
					}
					
//					System.out.println("Socket is connected multithread");
				}
//				Thread.currentThread().sleep(1000);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	public void startServer(int port) {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			String mString = "";
			
			while (true) {
				Socket socket = serverSocket.accept();
				DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
				mString = (String)dataInputStream.readUTF();
				if (mString.equals("stop")) {
					break;
				}
				System.out.println("message= " + mString);
				
				
			}
			serverSocket.close(); //test
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}