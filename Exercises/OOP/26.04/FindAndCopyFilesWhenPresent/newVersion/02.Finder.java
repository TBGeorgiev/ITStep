import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;

public class Finder {
	private ExecutorService executorService;
	private boolean emptyFolder;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private final String enterDirectoryToSearch = "Enter a directory to search:";
	private final String enterCopyDestination = "Enter the destination you want to copy the files to:";

	public Finder(ExecutorService executorService) {
		this.executorService = executorService;
	}

	public void transferFiles() throws IOException, InterruptedException {
		System.out.println(enterDirectoryToSearch);
		String directory = reader.readLine();
		System.out.println(enterCopyDestination);
		String destination = reader.readLine();
		lookForFiles(directory, destination);
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
		if (!filesPresent(files)) {
			if (!emptyFolder) {
				System.out.println("Location is empty. Waiting for files to arrive.");
			}
			emptyFolder = true;
			Thread.currentThread().sleep(100);
			lookForFiles(location, destination);
			return;
		}
		emptyFolder = false;
		RunnableClass runnableClass = new RunnableClass(files, destination);
		executorService.execute(runnableClass);

		String toContinue = reader.readLine();
		if (toContinue.equalsIgnoreCase("y")) {
			System.out.println(enterDirectoryToSearch);
			String loc = reader.readLine();
			System.out.println(enterCopyDestination);
			String dest = reader.readLine();
			lookForFiles(loc, dest);
			return;
		}
	}

	private boolean filesPresent(File[] files) {
		for (File file : files) {
			if (!file.isDirectory()) {
				return true;
			}
		}
		return false;
	}
}
