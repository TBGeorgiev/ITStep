package com.seeburger.sort;


import java.util.Random;
import java.util.Scanner;

public class MinAndMax
{
	public static Random random = new Random(System.currentTimeMillis());
	
	public static void main(String[] args)
	{
		
		
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("How many times do you want to generate random numbers?");
		int cycles = scanner.nextInt();
		scanner.close();
		
		int[] a = new int[100];
		
		
		for (int i = 0; i < cycles; i++) {
			System.out.println(i + 1 + " cycle:");
			populateArrayWithRandomNums(a);
			findMinAndMaxAndPrint(a);
			System.out.println();
			
		}
		
	}

	private static void findMinAndMaxAndPrint(int[] a)
	{
		
		int min = a[0];
		int max = a[0];
		
		for (int i = 1; i < a.length; i++)
		{
			if (min > a[i]) {
				min = a[i];
			}
			if (max < a[i]) {
				max = a[i];
			}
		}
		
		System.out.println("Min: " + min);
		System.out.println("Max: " + max);
	}

	public static void populateArrayWithRandomNums(int[] a)
	{
		for (int i = 0; i < 100; i++) 
		{
			a[i] = random.nextInt(100);
//			a[i] = (int) (Math.random() * 101) + 1;
			if (a[i] == 0) 
			{
				a[i] = 1;
			}
		}
	}

}
