import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
//		System.out.println("Please enter the IP you want to connect to: ");
//		String ip = reader.readLine();
		System.out.println("Enter the port: ");
		int port = Integer.parseInt(reader.readLine());
		
		
//		Client client = new Client();
//		client.connectToServer(ip, port);
		
		
		Server server = new Server();
		try {
			server.startMultiThreadedServer(port);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		Runnable runnableClient = new Runnable() {
//			
//			@Override
//			public void run() {
//				Client client = new Client();
//				try {
//					client.connectToServer(ip, port);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//		};
//		
//		Runnable runnableServer = new Runnable() {
//			
//			@Override
//			public void run() {
//				Server server = new Server();
//				server.startServer(port);
//				
//			}
//		}; 
//		
//		Thread clientThread = new Thread(runnableClient);
//		Thread serverThread = new Thread(runnableServer);
//		
//		clientThread.start();
//		serverThread.start();
		

	}

}
