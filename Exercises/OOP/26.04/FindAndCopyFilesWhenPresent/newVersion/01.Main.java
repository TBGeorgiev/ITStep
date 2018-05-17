import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		Finder finder = new Finder(executorService);
		finder.transferFiles();		
		executorService.shutdown();
	}
}
