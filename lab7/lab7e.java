package lab7;

import java.util.*;

public class lab7e {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while(totalnum-->0){
            int n = in.nextInt();
            int time = in.nextInt();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
            LinkedList<Integer> li = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                int tmp = in.nextInt();
                pq.add(tmp);
                li.addLast(tmp);
            }
            int[] res = new int[n];
            int respos = n-1;
//            System.out.println(respos);
            for (int i = 0; i < time; i++) {
                res[respos] = pq.poll();
                li.remove((Integer) res[respos]);
                respos--;
            }
            respos=0;
            for (int i = 0; i < n-time; i++) {
                res[respos] = li.pollFirst();
                respos++;
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
