import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the staircase function below.
     */
    static void staircase(int n) {
        int temp = n;
        for (int i = 0; i < n; i++) {
            for (int j = temp - 1; j > 0; j--) {
                System.out.print(" ");
            }
            temp--;
            for (int k = 0; k < n - temp; k++) {
                System.out.print("#");
            }
            System.out.println();
        }
    

    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int n = Integer.parseInt(scan.nextLine().trim());

        staircase(n);
    }
}
