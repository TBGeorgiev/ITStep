package com.seeburger.sort;
import com.seeburger.sort.MinAndMax;

public class BubbleSort
{
	
	public static int[] generateRandomArray()
	{
		int[] toSort = new int[100];
		MinAndMax.populateArrayWithRandomNums(toSort);
		return toSort;
	}

	public static int[] sortArrayUsingBubbleSort(int[] toSort)
	{
		boolean isSorted = false;
		
		while (!isSorted)
		{
			isSorted = true;
			for (int i = 1; i < toSort.length; i++)
			{
				int temp = toSort[i - 1];
				if (toSort[i - 1] > toSort[i]) {
					toSort[i - 1] = toSort[i];
					toSort[i] = temp;
					isSorted = false;
				}
			}
			
		}
		
		return toSort;
		
	}
	
	public static void printSortedArray(int[] sortedArray)
	{
		for (int a : sortedArray) {
			System.out.print(a + " ");
		}
	}
}
