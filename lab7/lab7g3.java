//package lab7;
//
//import java.util.*;
//
//// using PQ as underlying
//// TLE
//
//public class lab7g3 {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while(totalnum-->0){
//            int n = in.nextInt();
//            int k = (int)Math.sqrt(n);
//            int[] arr = new int[n];
//            HashMap<Map.Entry<Integer,Integer>, Integer> hm = new HashMap<>();
//            // entry: start, step (x,y)
//            for (int i = 0; i < n; i++) {
//                arr[i] = in.nextInt();
//            }
//            for (int step = 1; step <= k+1; step++) {
//                for (int type = 0; type < step; type++) {
//                    int tmpsize = 0;
//                    int cursor = n-1-type;
//                    PriorityQueue<Integer> pq = new PriorityQueue<>();
//                    while(cursor>=0){
//                        if(pq.size()<k){
//                            pq.add(arr[cursor]);
//                        } else if (pq.peek()<arr[cursor]){
//                            pq.poll();
//                            pq.add(arr[cursor]);
//                        }
//
//                        tmpsize++;
//                        if(tmpsize>=k){
//                            hm.put(new AbstractMap.SimpleEntry<>(cursor+1,step),pq.peek());
//                        }
//                        cursor-=step;
//                    }
//                }
//            }
////            System.out.println("build finish");
//            for (int i = 0; i < n; i++) {
//                int x = in.nextInt(), y = in.nextInt();
//                AbstractMap.SimpleEntry key = new AbstractMap.SimpleEntry(x,y);
//                if(hm.containsKey(key)){
//                    System.out.println(hm.get(key));
//                } else {
//                    System.out.println(-1);
//                }
//            }
//
//
//        }
//    }
//}
