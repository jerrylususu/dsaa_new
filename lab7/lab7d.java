package lab7;

import java.util.*;

public class lab7d {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while(totalnum-->0){
            int n = in.nextInt();
            int k = in.nextInt();
            PriorityQueue<Integer> heap = null;
            boolean isMax = true;
            int size = -1;
            if(k>n/2){
                // max heap
                size = n-k+1;
                heap = new PriorityQueue<>(Comparator.reverseOrder());
            } else {
                // min heap
                size = k;
                isMax = false;
                heap = new PriorityQueue<>();
            }

            for (int i = 0; i < n; i++) {
                int tmp = in.nextInt();
                if(heap.size()<size){
                    // not full
                    heap.add(tmp);
                } else {
                    // already full
                    if(isMax){
                        // is max heap
                        if(tmp<heap.peek()){
                            heap.poll();
                            heap.add(tmp);
                            // potential performance issue here
                        }
                    } else {
                        // is min heap
                        if(tmp>heap.peek()){
                            heap.poll();
                            heap.add(tmp);
                        }
                    }

                }
            }

            System.out.println(heap.peek());

        }
    }
}
