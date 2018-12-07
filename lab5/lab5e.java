package lab5;

import java.util.Scanner;

public class lab5e {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();

        while(total-->0) {
            int l1 = in.nextInt(), l2 = in.nextInt();
            String s1 = in.next(), s2 = in.next();

            int[] next = buildNext(s1);
            char[] p = s1.toCharArray();
            char[] a = s2.toCharArray();

            // try to match
            int i=a.length>=p.length?(a.length-p.length):0;
            int j=0;
            while(i<l2){
                if(j==-1||a[i]==p[j]){
                    i++;j++;
                } else {
                    j=next[j];
                }
            }

            System.out.println(j+" "+s1.substring(0,j));




        }
    }

    public static int[] buildNext(String s1){
        char[] p = s1.toCharArray();
        int[] next = new int[p.length + 1];
        next[0] = -1;
        int j = 0, k = -1;

        // build next
        while (j < p.length) {
            if (k == -1 || p[j] == p[k]) {
                next[j + 1] = k + 1; // next[j+1] = next[j]+1
                j++;
                k++;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
