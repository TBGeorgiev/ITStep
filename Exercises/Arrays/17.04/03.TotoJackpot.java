import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Toto
{

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("How many random tickets do you want to test?");
		
		int numberOfTickets = scanner.nextInt();
		
		scanner.close();
		
//		int[] winningNumbers = {2, 46, 31, 16, 33, 5};
		int[] winningNumbers = {2, 46};
		
		int[] ticket = new int[2];
		
		int[][] tickets = new int[numberOfTickets][2];
		
		
		startToto(winningNumbers, ticket,tickets, numberOfTickets);
		
	}

	private static void startToto(int[] winningNumbers, int[] ticket, int[][] tickets, int numberOfTickets)
	{
		int duplicates = 0;
		
		List<String> duplicatesList = new ArrayList<>();
		
		
		
		Random random = new Random(System.currentTimeMillis());
		StringBuilder stringBuilder = new StringBuilder();
		
		for (int i = 0; i < numberOfTickets; i++) {  // defines number of tickets it will test
			
			for (int j = 0; j < winningNumbers.length; j++) {  // fills up first ticket with random nums
				int randomNum = random.nextInt(50);
				if (randomNum == 0) {
					randomNum = j + 1;
				}
				ticket[j] = randomNum;
				if (j + 1 < winningNumbers.length) {
					stringBuilder.append(String.valueOf(randomNum) + ",");					
				} else {
					stringBuilder.append(String.valueOf(randomNum));
				}
				
			}
			
			boolean isDuplicate = false;
			
			
			if (i > 0) {  // performs check of previous tickets is there is a duplicate
				
				String toInt = stringBuilder.toString();
				
				
				if (duplicatesList.contains(toInt)) {
					System.out.println("Duplicate found!: " + toInt);
					isDuplicate = true;
					duplicates++;
				}
				
				duplicatesList.add(toInt);
			}
			
			
			boolean winner = true;
			for (int k = 0; k < ticket.length; k++)  // checks if ticket is the same as the winning ticket
			{
				if (ticket[k] != winningNumbers[k]) {
					winner = false;
					break;
				}
			}
			
			
			if (winner) {
				System.out.println("=================================");
				System.out.println(i + 1 + ": Jackpot! " + Arrays.toString(ticket));
				System.out.println("=================================");
				
				checkForNumberOfDuplicates(duplicates);
				
				return;
			}
			else {
				if (!isDuplicate) {
					System.out.println(i + 1 + ": Try again " + Arrays.toString(ticket));					
				}
			}
			stringBuilder.setLength(0);
		}
		
		checkForNumberOfDuplicates(duplicates);
	}

	private static void checkForNumberOfDuplicates(int duplicates)
	{
		if (duplicates > 0) {
			System.out.println("Duplicates found: " + duplicates);
		} else {
			System.out.println("No duplicates found.");
		}
	}
	
	
}
