import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CompareElementsOfTwoArrays {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(reader.readLine());
		
		int[] firstArray = new int[size];
		int[] secondArray = new int[size];
		
		for (int i = 0; i < firstArray.length; i++) {
			firstArray[i] = Integer.parseInt(reader.readLine());
		}
		
		for (int i = 0; i < secondArray.length; i++) {
			secondArray[i] = Integer.parseInt(reader.readLine());
		}
		
		int[] finalArray = new int[size];
		
		for (int i = 0; i < secondArray.length; i++) {
			if (firstArray[i] >= secondArray[i]) {
				finalArray[i] = firstArray[i];
			} else {
				finalArray[i] = secondArray[i];
			}
		}
		
		System.out.println(Arrays.toString(finalArray).replace("[", "").replace("]", "").replaceAll(",", ""));
	}

}
