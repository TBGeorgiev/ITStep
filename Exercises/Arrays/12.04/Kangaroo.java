import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static String kangaroo(int x1, int v1, int x2, int v2) {
        int kangaroo1Start = x1;
        int kangaroo2Start = x2;
        
        int kangaroo1Step = v1;
        int kangaroo2Step = v2;
        
        String toReturn = "";
        
        if (kangaroo2Step >= kangaroo1Step) {
            toReturn = "NO";
            return toReturn;
        } else {
            while (true) {
                kangaroo1Start += kangaroo1Step;
                kangaroo2Start += kangaroo2Step;
                if (kangaroo1Start > kangaroo2Start) {
                    toReturn = "NO";
                    break;
                }
                else if (kangaroo1Start == kangaroo2Start) {
                    toReturn = "YES";
                    break;
                }
            }
            return toReturn;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x1 = in.nextInt();
        int v1 = in.nextInt();
        int x2 = in.nextInt();
        int v2 = in.nextInt();
        String result = kangaroo(x1, v1, x2, v2);
        System.out.println(result);
    }
}
