package com.seeburger.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * Utility class which contains methods for MD5 checksum tests
 * so they can be used multiple times with ease.
 */

public final class ChecksumUtilities
{
	public static byte[] createChecksum(String filename) throws Exception
	{
		InputStream fis = new FileInputStream(filename);

		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("MD5");
		int numRead;

		do
		{
			numRead = fis.read(buffer);
			if (numRead > 0)
			{
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);

		fis.close();
		return complete.digest();
	}
	
	public static String getMD5(File fileLocation) {
		String result = "";
		byte[] b1;
		try
		{
			b1 = createChecksum(fileLocation.getAbsolutePath());
			for (int j = 0; j < b1.length; j++)
			{
				result += Integer.toString((b1[j] & 0xff) + 0x100, 16).substring(1);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
