import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FibonacciQuiz {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		long toCheck = Long.parseLong(reader.readLine());
		System.out.println(fibCheck(toCheck));
		printFibNums(toCheck);
		
		
	}
	
	private static void printFibNums(long num) {
		long fib1 = 0;
		long fib2 = 1;
		
		for (int i = 0; i < num; i++) {
			long fibSave = fib1;
			fib1 = fib2;
			fib2 = fib1 +fibSave;
			if (i < num - 1) {
				System.out.print(fib2 + ", ");				
			} else {
				System.out.println(fib2);
			}
			
		}
	}

	private static boolean fibCheck(long toCheck) {
		long fib1 = 0;
		long fib2 = 1;
		
		do {
			long fibSave = fib1;
			fib1 = fib2;
			fib2 = fib1 + fibSave;
		} while (fib2 < toCheck);
		
		if (fib2 == toCheck) {
			return true;
		} else {
			return false;
		}
	}

}
