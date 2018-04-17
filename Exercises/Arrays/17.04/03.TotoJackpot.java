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
		
		int[] winningNumbers = generateWinningNumbers();
		
		int[] ticket = new int[winningNumbers.length];
		
		
		
		scanner.close();
		
		startToto(winningNumbers, ticket, numberOfTickets);
		
	}
	
	
	private static int[] generateWinningNumbers() 
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("How many winning numbers do you want to set?");
		int number = scanner.nextInt();
		int[] winningNumbers = new int[number];
		
		
		
		Random random = new Random(System.currentTimeMillis());
		
		for (int i = 0; i < winningNumbers.length; i++)
		{
			winningNumbers[i] = random.nextInt(50);
		}
		
		boolean uniqueNumbers = false;
		
		while (!uniqueNumbers)
		{
			uniqueNumbers = true;
			for (int i = 0; i < winningNumbers.length; i++)
			{
				for (int j = i + 1; j < winningNumbers.length; j++)
				{
					if (winningNumbers[i] == winningNumbers[j]) {
						winningNumbers[i] = random.nextInt(50);
						uniqueNumbers = false;
					}
				}
			}
		}
		
		return winningNumbers;
		
	}
	
	

	private static void startToto(int[] winningNumbers, int[] ticket, int numberOfTickets)
	{
		System.out.println("Random winning ticket combination: " + Arrays.toString(winningNumbers));
		
		int duplicates = 0;
		
		List<String> duplicatesList = new ArrayList<>();
		
		
		
		Random random = new Random(System.currentTimeMillis() + System.currentTimeMillis());
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
