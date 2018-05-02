import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RunnableClass implements Runnable {
	private int port;
	private Socket socket;
	private ServerSocket serverSocket;
	
	
	public RunnableClass(int port, Socket socket, ServerSocket serverSocket) {
		this.port = port;
		this.socket = socket;
		this.serverSocket = serverSocket;
	}

	@Override
	public void run() {
		startServer(this.socket, this.serverSocket);
		
	}
	
	
	public void startServer(Socket socket, ServerSocket serverSocket) {
		try {
			
			String mString = "";
			
			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
			mString = (String)dataInputStream.readUTF();
//			if (mString.equals("stop")) {
//				break;
//			}
			System.out.println(socket.getInetAddress().toString() + ": " + Thread.currentThread().getName() + "message= " +  mString);
			dataInputStream.close();
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
