import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		int[] array = new int[n];
		
		for (int i = 0; i < array.length; i++) {
			array[i] = Integer.parseInt(reader.readLine());
			if (array[i] % 2 == 0) {
				array[i] = 0;
			}
		}
		
		System.out.println(Arrays.toString(array).replace("[", "").replace("]", "").replaceAll(",", ""));
	}

}
