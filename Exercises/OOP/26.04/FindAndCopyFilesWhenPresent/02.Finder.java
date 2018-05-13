import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;

public class Finder {
	
	private ExecutorService executorService;
	private boolean emptyFolder;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public Finder(ExecutorService executorService) {
		this.executorService = executorService;
	}
	
	public void lookForFiles(String location, String destination) throws InterruptedException, IOException {
		File folder = new File(location);
		if (!folder.exists()) {
			System.out.println("Directory doesn't exist! Try again with a new directory:");
			location = reader.readLine();
			lookForFiles(location, destination);
			return;
		}
		
		File[] files = folder.listFiles();
		if (files.length == 0) {
			if (!emptyFolder) {
				System.out.println("Location is empty. Waiting for files to arrive.");
			}
			emptyFolder = true;
//			System.out.println("Directory is empty.");
			Thread.currentThread().sleep(100);
			lookForFiles(location, destination);
			return;
		}
		for (File file : files) {
			emptyFolder = false;
			if (!file.isDirectory()) {
				File dest = new File(destination);
				if (!dest.exists()) {
					dest.mkdir();
				}
				RunnableClass runnableClass = new RunnableClass
						(file, new File(dest + File.separator + file.getName()));
				executorService.execute(runnableClass);
				
			}
		}
		System.out.println("Copying complete.");
		System.out.println("Look for other files? Use y / n");
		String toWait = reader.readLine();
		if (toWait.equalsIgnoreCase("y")) {
			System.out.println("Enter a directory to search:");
			String loc = reader.readLine();
			lookForFiles(loc, destination);
			return;
		}
		
	}
	
	

}
