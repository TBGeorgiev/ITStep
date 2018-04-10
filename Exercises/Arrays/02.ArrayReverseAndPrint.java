import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ArrayReverse {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		
		int[] arrayToReverse = new int[n];
		
		for (int i = 0; i < arrayToReverse.length; i++) {
			arrayToReverse[i] = Integer.parseInt(reader.readLine());
		}
		
		for (int i = 0; i < arrayToReverse.length / 2; i++) {
			int temp = arrayToReverse[i];
			arrayToReverse[i] = arrayToReverse[arrayToReverse.length - 1 - i];
			arrayToReverse[arrayToReverse.length - 1 - i] = temp;
		}
		
		System.out.println(Arrays.toString(arrayToReverse).replace("[", "").replace("]", "").replaceAll(",", ""));
	}

}
