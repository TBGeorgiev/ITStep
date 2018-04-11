import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PythagoreanNumbers {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		
		List<Integer> listOfNumbersToCheck = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			listOfNumbersToCheck.add(Integer.parseInt(reader.readLine()));
		}
		
		boolean isFound = false;
		
		for (int i = 0; i < listOfNumbersToCheck.size(); i++) {
			for (int j = 0; j < listOfNumbersToCheck.size(); j++) {
				for (int k = 0; k < listOfNumbersToCheck.size(); k++) {
					int num1 = listOfNumbersToCheck.get(i);
					int num2 = listOfNumbersToCheck.get(j);
					int num3 = listOfNumbersToCheck.get(k);
					
					if (num2 * num2 <= num3 * num3  && (num2 * num2) + (num3 * num3) == num1 * num1) {
						isFound = true;
						System.out.printf("%d * %d + %d * %d = %d * %d%n", num2, num2, num3, num3, num1 , num1);
					}
					
				}
			}
		}
		
		if (!isFound) {
			System.out.println("No");
		}
	}

}
