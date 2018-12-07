package lab5;

import java.util.Arrays;
import java.util.Scanner;

public class lab5d {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();

        while(total-->0){
            //int l = in.nextInt();
            String s = in.next();
            char[] a = s.toCharArray();

            int[] half = buildNext(s.substring(0,a.length/2));
            char[] h = s.substring(0,a.length/2).toCharArray();
            //System.out.println(Arrays.toString(half));

            // build kmp next to get the max length
            int cur1 = a.length/2, cur2=0;
            while(cur1<a.length){
                if(cur2==-1||a[cur1]==h[cur2]){
                    cur1++;cur2++;
                } else {
                    cur2 = half[cur2];
                }
            }

            //System.out.println(cur2);
            int maxlen = cur2;
            int cur3 = -1;
            boolean found = false;
            int finallen = -1;
            for(int i=maxlen;i>0;i--){
                cur3 = i;
                int end = a.length-i;
                int cur4=0;
                while(cur3<end){
                    //System.out.println(cur3+" "+cur4);
                    if(cur4==-1||a[cur3]==h[cur4]){
                        cur3++;cur4++;
                    } else {
                        cur4=half[cur4];
                    }
                    if(cur4==i){
                        if(cur3<=end){
                            found = true;
                            break;
                        } else {
                            cur4=half[cur4];
                        }

                    }
                }
                if(found){

                    //check
                    cur1 = a.length-i;
                    cur2=0;
                    while(cur1<a.length){
                        if(cur2==-1||a[cur1]==h[cur2]){
                            cur1++;cur2++;
                        } else {
                            cur2 = half[cur2];
                        }
                    }
                    if(cur2==i){
                        finallen=i;
                        break;
                    }
                }
            }
            System.out.println(finallen<0?0:finallen);

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
