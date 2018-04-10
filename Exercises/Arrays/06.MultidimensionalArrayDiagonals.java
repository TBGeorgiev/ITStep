import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TwoDimensionalArray {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] matrix = new int[3][3];
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = Integer.parseInt(reader.readLine());
			}
		}
		
		int sum1 = 0;
		int sum2 = 0;
		
		for (int i = 0; i < matrix.length; i++) {
			sum1 += matrix[i][i];
			sum2 += matrix[i][matrix.length - 1 - i];
		}
		
		System.out.println(sum1);
		System.out.println(sum2);
		System.out.println();
		int temp = 0;
		int temp2 = 0;
		
		
		for (int i = 0; i < matrix.length; i++) {
			temp = 0;
			temp2 = 0;
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[j][i] > temp) {
					temp = matrix[j][i];
				}
				if (matrix[i][j] > temp2) {
					temp2 = matrix[i][j];
				}
			}
			
			System.out.println(i + 1 + ":Largest column num: " + temp);
			System.out.println(i + 1 + ":Largest row num: " + temp2);
			
		/*
			3 1 2
			6 5 2
			3 8 4
			
			*/
		
		}
	}

}
