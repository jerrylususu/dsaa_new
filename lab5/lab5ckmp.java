package lab5;

import java.util.Arrays;
import java.util.Scanner;

public class lab5ckmp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(true) {


            String s = in.next();

            char[] p = s.toCharArray();
            int[] next = new int[p.length + 1];
            next[0] = -1;
            // wrong implementation
            int j = 1, k = -1; // <- ignored the possibility of p[0]==p[1]

            // build next
            while (j < p.length) {
                if (k == -1 || p[j] == p[k]) {
//                    if(p[j+1]==p[k+1]){ // if the same, if failed, must be failed again
//                        next[j+1] = next[k+1]; // just jump and go deeper 1 layer
//                    } else {
                    next[j + 1] = k + 1; // next[j+1] = next[j]+1
                    //}
                    j++;
                    k++;
                } else {
                    k = next[k];
                }
            }

            System.out.println("wrong"+Arrays.toString(next));

            next = new int[p.length + 1];
            next[0] = -1;
            j = 0;
            k = -1;

            // build next
            while (j < p.length) {
                if (k == -1 || p[j] == p[k]) {
//                    if(p[j+1]==p[k+1]){ // if the same, if failed, must be failed again
//                        next[j+1] = next[k+1]; // just jump and go deeper 1 layer
//                    } else {
                    next[j + 1] = k + 1; // next[j+1] = next[j]+1
                    //}
                    j++;
                    k++;
                } else {
                    k = next[k];
                }
            }

            System.out.println("real "+Arrays.toString(next));
        }
    }
}
