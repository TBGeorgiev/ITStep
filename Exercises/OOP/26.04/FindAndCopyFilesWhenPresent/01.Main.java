import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws InterruptedException, IOException {
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		Finder finder = new Finder(executorService);
		System.out.println("Enter a directory to search:");
		String directory = reader.readLine();
		System.out.println("Enter the destination you want to copy the files to: ");
		String destination = reader.readLine();
		
		finder.lookForFiles(directory, destination);
		executorService.shutdown();
	}
	

}
