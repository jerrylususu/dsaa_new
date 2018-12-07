package lab5;

import java.util.Arrays;
import java.util.Scanner;

public class ExtendKMP {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(true) {
            String s = in.next();
            System.out.println(Arrays.toString(buildNext(s)));
            String s2 = in.next();
            System.out.println(Arrays.toString(buildNext(String.format("%s%s", s, s2))));

//            char[] t = s2.toCharArray();
//            char[] p = s.toCharArray();
//            int[] next = buildNext(s);
//
//            int i = 0, j = 0;
//            while (i < t.length) {
//                if (j == -1 || t[i] == p[j]) {
//                    i++;
//                    j++;
//                } else {
//                    j = next[j];
//                }
//            }
//            System.out.println(j);
            int[] next = buildNext(s);
            int[] arr = new int[s.length()+s2.length()+1];
            System.arraycopy(next,0,arr,0,next.length);
            int j = next.length-1, k = arr[next.length-1];
            char[] p = String.format("%s%s", s, s2).toCharArray();
            while(j<arr.length-1){
//                System.out.println(j+" "+k);
                if (k == -1 || p[j] == p[k]) {
                    arr[j + 1] = k + 1; // next[j+1] = next[j]+1
                    j++;k++;
                } else {
                    k = arr[k];
                }
            }
            System.out.println(Arrays.toString(arr));
        }


//        int[] next = buildNext(s);
//        int[] arr = new int[s.length()+s2.length()];
//        System.arraycopy(next,0,arr,0,next.length);
//        int j = next.length-1, k = arr[next.length-1];
//        while(j<arr.length-1){
//            if (k == -1 || p[j] == p[k]) {
//                arr[j + 1] = k + 1; // next[j+1] = next[j]+1
//                j++;k++;
//            } else {
//                k = next[k];
//            }
//        }
//        System.out.println(Arrays.toString(arr));



    }

    public static int[] buildNext(String s1){
        char[] p = s1.toCharArray();
        int[] next = new int[p.length+1];
        next[0] = -1;
        int j = 0, k = -1;

        // build next
        while (j < p.length) {
            if (k == -1 || p[j] == p[k]) {
                next[j + 1] = k + 1; // next[j+1] = next[j]+1
                j++;k++;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
