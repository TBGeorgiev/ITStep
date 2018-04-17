import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ArraysFill
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		
		int sizeOfArray = scanner.nextInt();
		
		scanner.close();
		
		int[] array = new int[sizeOfArray];
		
		int numberOfDivisableByTwo = 0;
		int numberOfNonDivisableByTwo = 0;
		
		Random random = new Random();
		
		for (int i = 0; i < sizeOfArray; i++) {
			int randomNum = random.nextInt();
			
			if (randomNum % 2 == 0) {
				numberOfDivisableByTwo++;
			} else {
				numberOfNonDivisableByTwo++;
			}
			array[i] = randomNum;
			
		}
		
		
		int[] arrayOfDivisableByTwo = new int[numberOfDivisableByTwo];
		int[] arrayOfNotDivisableByTwo = new int[numberOfNonDivisableByTwo];
		
		int index1 = 0;
		int index2 = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] % 2 == 0) {
				arrayOfDivisableByTwo[index1] = array[i];
				index1++;
			} else {
				arrayOfNotDivisableByTwo[index2] = array[i];
				index2++;
			}
		}
		
		System.out.println("Divisable by 2:");
		System.out.println(Arrays.toString(arrayOfDivisableByTwo));
		System.out.println();
		
		System.out.println("Non divisable by 2:");
		System.out.println(Arrays.toString(arrayOfNotDivisableByTwo));
		
		
	}

}
