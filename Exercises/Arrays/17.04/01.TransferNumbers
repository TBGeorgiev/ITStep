
import java.io.IOException;

import java.util.Scanner;

public class transferNumbers
{
	public static void main(String[] args) throws NumberFormatException, IOException
	{		
		short number = getInputNum();
		
		convertToBinary(number);
		
	}
	
	
	
	
	private static short getInputNum() // gets input and checks if it's valid - if not - retry
	{
		Scanner scanner = new Scanner(System.in);
		
		short number = 0;
		
		try
		{
			number = scanner.nextShort();
			scanner.close();
		} 
		catch (Exception e)
		{
			System.out.println("Wrong input!");
			return getInputNum();
		}
		return number;
		
	}
	
	
	
	
	private static void convertToBinary(short number) //converts a short to binary if possible
	{
		if (number < 256 && number > 0) {
			short[] bytes = {128, 64, 32, 16, 8, 4, 2, 1};
			
			for (int i = 0; i < bytes.length; i++) {
				if (number >= bytes[i]) {
					number = (short) (number - bytes[i]);
					System.out.print(1);
				} else {
					System.out.print(0);
				}
			}
			
		} else {
			System.out.println("No");
		}
	}

}
