package lab5;

import java.util.Arrays;
import java.util.Scanner;

public class lab5c2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        System.out.println(Arrays.toString(generateNext(s)));
    }

    public static int doKMP(String all, String ps){
        char[] a = all.toCharArray();
        char[] p = ps.toCharArray();
        int[] next = generateNext(ps);
        int i=0,j=0;
        while(i<a.length&&j<p.length){
           if(j==-1||a[i]==p[j]){
               i++;j++; // if success, move both
           } else {
               j=next[j]; // if fail, only move pattern cursor
           }
        }
        if(j==p.length){
            return i-j;
        }else {
            return -1;
        }
    }

    public static int[] generateNext(String s){
        char[] p = s.toCharArray();
        int[] next = new int[p.length+1];
        next[0] = -1;
        int j=1, k=-1;
        while(j<p.length-1){
            if(k==-1||p[j]==p[k]){
                // next[j]=k
//                if(p[j+1]==p[k+1]){ // if the same, if failed, must be failed again
//                    next[j+1] = next[k+1]; // just jump and go deeper 1 layer
//                } else {
//                    next[j+1] = k + 1; // next[j+1] = next[j]+1
//                }
//                j++;
//                k++;

//                if(p[++j]==p[++k]){ // if the same, if failed, must be failed again
//                    next[j] = next[k]; // just jump and move forward
//                } else {
//                    next[j] = k; // next[j+1] = next[j]+1
//                }

                next[j+1] = k + 1;
                j++;
                k++;
            } else {
                k = next[k];
            }
        }

        k=next[p.length-1];
        while(true){
            if(k==-1||p[p.length-1]==p[k]){
                next[p.length] = k+1;
                break;
            } else {
                k = next[k];
            }
        }


        return next;
    }
}
