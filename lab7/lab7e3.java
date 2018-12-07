/*
package lab7;

import java.util.*;

public class lab7e3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while(totalnum-->0){
            int n = in.nextInt();
            int k = in.nextInt();
            MyItem[] arr = new MyItem[n];
            for (int i = 0; i < n; i++) {
                arr[i] = new MyItem(in.nextInt(),i);
            }
            MyItem[] res = null;
            if(k>=n){
                Arrays.sort(arr, new Comparator<MyItem>() {
                    @Override
                    public int compare(MyItem o1, MyItem o2) {
                        return o1.val- o2.val;
                    }
                });
                res = arr;
            } else {
                boolean[] visited = new boolean[n];
                PriorityQueue<MyItem> pq = new PriorityQueue<>(new Comparator<MyItem>() {
                    @Override
                    public int compare(MyItem o1, MyItem o2) {
                        return o2.val-o1.val;
                    }
                });
                PriorityQueue<MyItem> pq2 = new PriorityQueue<>(new Comparator<MyItem>() {
                    @Override
                    public int compare(MyItem o1, MyItem o2) {
                        return o1.val-o2.val;
                    }
                });
                for (int i = 0; i < n; i++) {
                    if(pq.height()<k){
                        pq.add(arr[i]);
                        pq2.add(arr[i]);
                    } else {
                        if(arr[i].val>pq2.peek().val){
                            MyItem maxmin = pq2.poll();
                            pq.remove(maxmin);
                            pq.add(arr[i]);
                            pq2.add(arr[i]);
                        }
                    }
                }
                res = new MyItem[n];
                int respos = n-1;
                for (int i = 0; i < k; i++) {
                    res[respos] = pq.poll();
                    visited[res[respos].id]=true;
                    respos--;
                }
                respos = 0;
                int oldpos=0;
                for (int i = 0; i < n-k; i++) {
                    while(visited[oldpos]){
                        oldpos++;
                    }
                    res[respos] = arr[oldpos];
                    respos++;
                    oldpos++;
                }
            }

            for (int i = 0; i < n; i++) {
                System.out.print(res[i].val);
                if(i!=n-1){
                    System.out.print(" ");
                }
            }
        }
    }
}

class MyItem{
    int val;
    int id;

    public MyItem(int val, int id){
        this.val = val;
        this.id = id;
    }

    @Override
    public String toString() {
        return "MyItem{" +
                "val=" + val +
                ", id=" + id +
                '}';
    }
}*/
