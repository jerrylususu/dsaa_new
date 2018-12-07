package lab4;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class lab3g2 {
    // warning: that's attempt. Failed.
    // will cause TLE, even with fast input
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();

        while(total-->0){
            int len = in.nextInt();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(len,Collections.reverseOrder());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(len);
            LinkedList<Integer> stack = new LinkedList<>();

            for(int i=0;i<len;i++){
                String s = in.next();
                if(s.charAt(1)=='u'){
                    //push
                    int tmp = in.nextInt();
                    maxHeap.add(tmp);
                    minHeap.add(tmp);
                    stack.addLast(tmp);
                }else{
                    //pop
                    if(stack.size()>1){
                        int tmp = stack.removeLast();
                        maxHeap.remove(tmp);
                        minHeap.remove(tmp);
                        System.out.println(maxHeap.peek()-minHeap.peek());
                    } else {
                        stack.removeLast();
                        maxHeap.clear();
                        minHeap.clear();
                        System.out.println(0);
                    }


                }
            }
        }
    }
}
