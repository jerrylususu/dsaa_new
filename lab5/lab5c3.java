package lab5;

import java.util.Arrays;
import java.util.Scanner;

public class lab5c3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();

        while(total-->0){
            int l1 = in.nextInt();
            String as = in.next();
            int l2 = in.nextInt();
            String ps = in.next();

            char[] a = as.toCharArray();
            char[] p = ps.toCharArray();

            int[] next = new int[p.length+1];
            next[0] = -1;
//            int j=1, k=-1;
            int j=0, k=-1;

            // build next
            while(j<p.length){
                if(k==-1||p[j]==p[k]){
//                    if(p[j+1]==p[k+1]){ // if the same, if failed, must be failed again
//                        next[j+1] = next[k+1]; // just jump and go deeper 1 layer
//                    } else {
                        next[j+1] = k + 1; // next[j+1] = next[j]+1
                    //}
                    j++;
                    k++;
                } else {
                    k = next[k];
                }
            }
//            k=next[p.length-1];
//            j=p.length-1;
//            while(true){
//                if(k==-1||p[j]==p[k]){
//                    next[j+1] = k+1;
//                    break;
//                } else {
//                    k = next[k];
//                }
//            }
//            next[p.length]=-1;

            //System.out.println(Arrays.toString(next));

            // do match
            int cnt=0;
            int c1=0,c2=0;
            while(c1<a.length){
                if(c2==-1||a[c1]==p[c2]){
                    c1++;c2++;
                } else {
                    c2=next[c2];
                }
                if(c2==p.length){
                    cnt++;
                    //c1++;
                    c2=next[c2];
                }
            }
            System.out.println(cnt);



        }
    }



}
