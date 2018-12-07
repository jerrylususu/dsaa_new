package lab5;

import java.util.Scanner;

public class lab5gtmp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(true) {
            String s1 = in.next();
            String s2 = in.next();
            int[] next = buildNext(s1);
            char[] p = s1.toCharArray();
            char[] t = s2.toCharArray();

            int c1 = 0, c2 = 0;
            int cnt = 0;
            while (c1 < t.length) {
                if (c2 == -1 || t[c1] == p[c2]) {
                    c1++;
                    c2++;
                } else {
                    c2 = next[c2];
                }
                if(c2==p.length){
                    System.out.println("FULL!");
                    break;
                }
            }
            System.out.println("current c2:"+c2+" remain:"+(p.length-c2));

            c1=t.length-p.length>0?t.length-p.length:0;
            System.out.println(c1);
            c2=0;
            while (c1 < t.length) {
                if (c2 == -1 || t[c1] == p[c2]) {
                    c1++;
                    c2++;
                } else {
                    c2 = next[c2];
                }
                if(c2==p.length){
                    System.out.println("FULL!");
                    break;
                }
            }
            System.out.println("current c2:"+c2+" remain:"+(p.length-c2));
        }

    }

    public static int[] buildNext(String s1){
        char[] p = s1.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0, k = -1;

        // build next
        while (j < p.length-1) {
            if (k == -1 || p[j] == p[k]) {
//                next[j + 1] = k + 1; // next[j+1] = next[j]+1

                // optimized version
                if(p[j+1]==p[k+1]){
                    next[j+1]=next[k+1];
                }else{
                    next[j+1]=k+1;
                }
                j++;k++;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
