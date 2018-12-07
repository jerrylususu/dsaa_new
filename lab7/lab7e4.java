package lab7;

import java.util.PriorityQueue;
import java.util.Scanner;

public class lab7e4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while(totalnum-->0){
            int n = in.nextInt();
            int k = in.nextInt();
            int[] res = new int[n];
            PriorityQueue<Integer> pq = new PriorityQueue<>(k+1);
            int rescur = 0;
            for (int i = 0; i < n; i++) {
                int tmp = in.nextInt();
                if(pq.size()<k){
                    pq.add(tmp);
                } else {
                    pq.add(tmp);
                    res[rescur] = pq.poll();
                    rescur++;
                }
            }
            while(pq.size()>0){
                res[rescur] = pq.poll();
                rescur++;
            }
            for (int i = 0; i < n; i++) {
                System.out.print(res[i]);
                if(i!=n-1){
                    System.out.print(" ");
                }
            }

        }
    }
}
