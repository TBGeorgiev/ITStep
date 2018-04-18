package com.seeburger.sort;

public class SelectionSort
{
	public static int[] selectionSortOnArrayOfIntegers(int[] arr)
	{
		for (int i = 0; i < arr.length - 1; i++)
        {
            int index = i;
            
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[index]) 
                    index = j;
      
            int smallerNumber = arr[index];  
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
        return arr;
	}
	
	
	public static void printArray(int[] array)
	{
		for (int a : array) {
			System.out.print(a + " ");
		}
	}
}
