import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RunnableClass implements Runnable {
	private File[] files;
	private String destination;

	public RunnableClass(File[] files, String dest) {
		this.destination = dest;
		this.files = files;
	}

	@Override
	public void run() {
		File dest = new File(destination);
		if (!dest.exists()) {
			dest.mkdir();
		}
		for (File file : files) {
			if (!file.isDirectory()) {
				try {
					copyFileUsingStream(file, dest);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Copying complete." + Thread.currentThread().getName());
		System.out.println("Look for other files? Use y / n");
	}

	private void copyFileUsingStream(File source, File dest) throws IOException {
		InputStream iStream = null;
		OutputStream oStream = null;

		try {
			iStream = new FileInputStream(source);
			oStream = new FileOutputStream(dest + File.separator + source.getName());
			byte[] buffer = new byte[512];
			int length;
			while ((length = iStream.read(buffer)) > 0) {
				oStream.write(buffer, 0, length);
			}

		} finally {
			iStream.close();
			oStream.close();
		}
	}
}
