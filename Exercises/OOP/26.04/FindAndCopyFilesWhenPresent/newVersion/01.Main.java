import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		Finder finder = new Finder(executorService);
		try {
			finder.transferFiles();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		executorService.shutdown();
	}
}
