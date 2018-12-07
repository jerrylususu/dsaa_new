package lab6;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class lab6g2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();
        while(totalnum-->0){
            int n = in.nextInt();
            int[] weights = new int[n];
            for (int i = 0; i < n; i++) {
                weights[i] = in.nextInt();
            }
            int[] maxSize = new int[n];
            LinkedList<Integer>[] out = new LinkedList[n];
            for (int i = 0; i < n - 1; i++) {
                int p1 = in.nextInt()-1, p2 = in.nextInt()-1,s=-1,b=-1;
                // p for position, s=small, b=big
                if(weights[p1]<weights[p2]){
                    s=p1;b=p2;
                } else {
                    s=p2;b=p1;
                }
                if(out[s]==null){
                    out[s] = new LinkedList<Integer>();
                }
                out[s].add(b);
            }
            for (int i = 0; i < n; i++) {
                findMax(i,weights,out,maxSize);
            }

//            System.out.println(Arrays.toString(maxSize));

            int max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                if(maxSize[i]>max){
                    max=maxSize[i];
                }
            }

            System.out.println(Arrays.stream(maxSize).max().getAsInt());

        }
    }

    public static void findMax(int i, int[] weights, LinkedList<Integer>[] out, int[] maxSize){
        if(maxSize[i]!=0){
            return;
        }

        int cnt=1;
        if(out[i]!=null){
            for(Integer c:out[i]){
                findMax(c,weights,out,maxSize);
                cnt+=maxSize[c];
            }
        }

        maxSize[i]=cnt;
    }
}
