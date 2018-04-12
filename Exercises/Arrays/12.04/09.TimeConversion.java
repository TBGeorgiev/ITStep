import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the timeConversion function below.
     */
    static String timeConversion(String s) {
        if (s.endsWith("AM")) {
            if (s.startsWith("12")) {
                String toSplit = s.substring(0, s.length() - 2);
                String[] split = toSplit.split(":");
                split[0] = "00";
                String toPrint = "";
                for (int i = 0; i < split.length; i++) {
                   toPrint = toPrint + split[i];
                    if (!(i + 1 >= split.length)) {
                    toPrint = toPrint + ":";
                }
                }
                
                return toPrint;
                    
            } else {
                return s.substring(0, s.length() - 2);
            }
                
            
            
        } else {
            String toSplit = s.substring(0, s.length() - 2);
            String[] split = toSplit.split(":");
            
            int time = Integer.parseInt(split[0]);
            if (time != 12) {
                time += 12;
            }
            
            split[0] = String.valueOf(time);
            String toPrint = "";
            for (int i = 0; i < split.length; i++) {
                toPrint = toPrint + split[i];
                if (!(i + 1 >= split.length)) {
                    toPrint = toPrint + ":";
                }
            }
            return toPrint;
        }

    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scan.nextLine();

        String result = timeConversion(s);

        bw.write(result);
        bw.newLine();

        bw.close();
    }
}
