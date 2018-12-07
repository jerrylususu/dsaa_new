package lab3;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class lab2g {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();

        while(total-->0){
            int n = in.nextInt();
            int tmp=0;
            double curmid=0;
            int heapInitSize = n/2+1;
            StringBuilder sb = new StringBuilder();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(heapInitSize,Collections.reverseOrder());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(heapInitSize);
            int max=0,min=0; //size

            for(int i=0;i<n;i++){
                //put
                if(i==0){
                    maxHeap.add(in.nextInt());
                    max++;
                } else {
                    tmp = in.nextInt();
                    if((max+min)%2==0){
                        curmid=(maxHeap.peek()+minHeap.peek())/2.0;
                    } else {
                        curmid=maxHeap.peek();
                    }
                    if(tmp<=curmid){
                        maxHeap.add(tmp);
                        max++;
                    } else {
                        minHeap.add(tmp);
                        min++;
                    }
                }
                //maintain
                if(max>min+1){
                    minHeap.add(maxHeap.poll());
                    max--;
                    min++;
                } else if(min>max) {
                    maxHeap.add(minHeap.poll());
                    max++;
                    min--;
                }
                if(i%2==0){
                    sb.append(maxHeap.peek()+" ");
                }
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb.toString());
        }
    }
}
