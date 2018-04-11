import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RemoveElementsFromArray {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		 int[] array = new int[n];
		 for (int i = 0; i < array.length; i++) {
			array[i] = Integer.parseInt(reader.readLine());
		}
		 int x = Integer.parseInt(reader.readLine());
		 for (int i = 0; i < array.length; i++) {
			if (array[i] != x) {
				System.out.print(array[i] + " ");
			}
		}
	}

}
