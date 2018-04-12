import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the gradingStudents function below.
     */
    static int[] gradingStudents(int[] grades) {
        int[] toReturn = new int[grades.length];
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] < 38) {
                toReturn[i] = grades[i];
                continue;
            }
            if (grades[i] % 5 == 0) {
                toReturn[i] = grades[i];
                
            } else {
                int value = grades[i];
                int secondValue = 0;
                for (int j = 1; j <= 3; j++) {
                    if ((grades[i] + j) % 5 == 0) {
                        secondValue = grades[i] + j;
                    }
                }
                if (secondValue > 0 && secondValue - value < 3) {
                    toReturn[i] = secondValue;
                } else {
                    toReturn[i] = value;
                }
                
            }
        }
        
        return toReturn;

    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scan.nextLine().trim());

        int[] grades = new int[n];

        for (int gradesItr = 0; gradesItr < n; gradesItr++) {
            int gradesItem = Integer.parseInt(scan.nextLine().trim());
            grades[gradesItr] = gradesItem;
        }

        int[] result = gradingStudents(grades);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bw.write(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bw.write("\n");
            }
        }

        bw.newLine();

        bw.close();
    }
}
