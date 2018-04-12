import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the diagonalDifference function below.
     */
    static int diagonalDifference(int[][] a) {
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < a.length; i++) {
            sum1 += a[i][i];
            sum2 += a[i][a.length - 1 - i];
        }
        int result = Math.abs(sum1 - sum2);
        return result;

    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scan.nextLine().trim());

        int[][] a = new int[n][n];

        for (int aRowItr = 0; aRowItr < n; aRowItr++) {
            String[] aRowItems = scan.nextLine().split(" ");

            for (int aColumnItr = 0; aColumnItr < n; aColumnItr++) {
                int aItem = Integer.parseInt(aRowItems[aColumnItr].trim());
                a[aRowItr][aColumnItr] = aItem;
            }
        }

        int result = diagonalDifference(a);

        bw.write(String.valueOf(result));
        bw.newLine();

        bw.close();
    }
}
