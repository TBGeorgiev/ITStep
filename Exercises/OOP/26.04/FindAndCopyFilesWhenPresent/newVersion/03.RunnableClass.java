package com.seeburger.fileTransfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class RunnableClass implements Runnable {
	private volatile boolean toStop = false;
	private String destination;
	private String location;
	private Logger logger;

	public RunnableClass(String location, String dest, Logger logger) {
		this.destination = dest;
		this.location = location;
		this.logger = logger;
	}

	public void setToStop(boolean toStop) {
		this.toStop = toStop;
	}

	@Override
	public void run() {
		File source = new File(location);
		File[] files = source.listFiles();
		File dest = new File(destination);
		if (!dest.exists()) {
			dest.mkdir();
		}
		for (File file : files) {
			if (!file.isDirectory()) {
				try {
					copyFileUsingStream(file, dest);
					Files.delete(Paths.get(file.getAbsolutePath()));
					if (toStop) {
						System.out.println("Operation stopped.");
						return;
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Moving complete." + Thread.currentThread().getName());
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
			logger.info(source.getName() + " size: " + source.length());
		} finally {
			iStream.close();
			oStream.close();
		}
	}
}

