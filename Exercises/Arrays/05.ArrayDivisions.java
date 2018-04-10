import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Divisions {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(reader.readLine());
		
		
		int[] arrayToCheck = new int[size];
		
		for (int i = 0; i < arrayToCheck.length; i++) {
			arrayToCheck[i] = Integer.parseInt(reader.readLine());
		} 
		
		int minValue = arrayToCheck[0];
		for (int i = 1; i < arrayToCheck.length; i++) {
			if (minValue > arrayToCheck[i]) {
				minValue = arrayToCheck[i];
			}
		}
		
		
		int count = 0;
		int divisionNumber = 0;
		
		
		for (int i = 2; i < minValue; i++) {
			count = 0;
			for (int j = 0; j < arrayToCheck.length; j++) {
				if (arrayToCheck[j] % i == 0) {
					divisionNumber = i;
					count++;
				} else {
					break;
				}
			}
			if (count == arrayToCheck.length) {
				System.out.println(divisionNumber);
			}
		}
	}
}
