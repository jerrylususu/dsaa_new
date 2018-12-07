//package lab7;
//
//import java.util.*;
//
//public class lab7e2 {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while(totalnum-->0){
//            int n = in.nextInt();
//            int time = in.nextInt();
//            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
//            HashMap<Integer,MyItem> hm = new HashMap<>();
//            for (int i = 0; i < n; i++) {
//                int tmp = in.nextInt();
//                pq.add(tmp);
//                hm.put(tmp, new MyItem(tmp,i));
//            }
//            int[] res = new int[n];
//            int respos = n-1;
////            System.out.println(respos);
//            for (int i = 0; i < time; i++) {
//                if(respos>=0){
//                    res[respos] = pq.poll();
//                    hm.remove(res[respos]);
//                    respos--;
//                }
//
//            }
//            respos=0;
//            MyItem[] tmparr = new MyItem[hm.height()];
//            int cur=0;
//            for(Map.Entry<Integer,MyItem> entry:hm.entrySet()){
//                tmparr[cur] = entry.getValue();
//                cur++;
//            }
//            Arrays.sort(tmparr, new Comparator<MyItem>() {
//                @Override
//                public int compare(MyItem o1, MyItem o2) {
//                    return o1.put-o2.put;
//                }
//            });
//            for (int i = 0; i < n-time; i++) {
//                res[respos]=tmparr[respos].val;
//                respos++;
//            }
//            for (int i = 0; i < n; i++) {
//                System.out.print(res[i]);
//                if(i!=n-1){
//                    System.out.print(" ");
//                }
//            }
//        }
//    }
//}
//
//class MyItem{
//    int val;
//    int put;
//    public MyItem(int v, int p){
//        val=v; put=p;
//    }
//}