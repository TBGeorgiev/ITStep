import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the plusMinus function below.
     */
    static void plusMinus(int[] arr) {
        int countOfPositives = 0;
        int countOfNegatives = 0;
        int countOfZeroes = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                countOfPositives++;
            }
            else if (arr[i] < 0) {
                countOfNegatives++;
            } else {
                countOfZeroes++;
            }
        }
        double positives = (double) countOfPositives / arr.length;
        double negatives =  (double) countOfNegatives / arr.length;
        double zeroes = (double) countOfZeroes / arr.length;
        
        System.out.println(positives);
        System.out.println(negatives);
        System.out.println(zeroes);
        
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int n = Integer.parseInt(scan.nextLine().trim());

        int[] arr = new int[n];

        String[] arrItems = scan.nextLine().split(" ");

        for (int arrItr = 0; arrItr < n; arrItr++) {
            int arrItem = Integer.parseInt(arrItems[arrItr].trim());
            arr[arrItr] = arrItem;
        }

        plusMinus(arr);
    }
}
