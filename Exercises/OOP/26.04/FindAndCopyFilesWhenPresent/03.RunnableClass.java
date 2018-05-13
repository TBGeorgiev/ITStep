import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RunnableClass implements Runnable {
	
	private File file;
	private File destination;
	
	public  RunnableClass(File file, File destination) {
		this.file = file;
		this.destination = destination;
	}

	@Override
	public void run() {
		try {
			copyFileUsingStream(this.file, this.destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private void copyFileUsingStream(File source, File dest) throws IOException {
		
		InputStream iStream = null;
		OutputStream oStream = null;
		
//		if (!dest.exists()) {
//			dest.mkdir();
//		}
		
		try {
			iStream = new FileInputStream(source);
			oStream = new FileOutputStream(dest);
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
