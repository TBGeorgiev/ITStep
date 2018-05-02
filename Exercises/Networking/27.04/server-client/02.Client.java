import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public Client() {
		
	}
	
	public void connectToServer(String ipAddress, int port) throws IOException {
		try {
			String toSend = "";
			while (!(toSend = reader.readLine()).equals("stop")) {
				
				Socket socket = new Socket(ipAddress, port);
				DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
				
				dataOutputStream.writeUTF(toSend);
				dataOutputStream.flush();
				dataOutputStream.close();
				socket.close();
				
				
			}
			
			
			
			
		} catch (UnknownHostException e) {
			// TODO: handle exception
		}
	}

}
