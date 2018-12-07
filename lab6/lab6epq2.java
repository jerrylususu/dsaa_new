//package lab6;
//
//import java.util.*;
//
//public class lab6epq2 {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while(totalnum-->0){
//            int pocketMax = in.nextInt();
//            int storen = in.nextInt();
//            MyItem[] arr = new MyItem[pocketMax];
//            HashMap<Integer,MyItem> hm = new HashMap<>();
//            PriorityQueue<MyItem> pq = new PriorityQueue<>(pocketMax, new Comparator<MyItem>() {
//                @Override
//                public int compare(MyItem o1, MyItem o2) {
//                    if(o1.visited!=o2.visited){
//                        return o2.visited-o1.visited;
//                    } else {
//                        return o1.putop-o2.putop;
//                    }
//                }
//            });
//
//
//            int cnt = 0;
//            for (int i = 0; i < storen; i++) {
//                int num = in.nextInt();
//                MyItem it = new MyItem(num,1,i);
//
//                if(pq.size()<pocketMax){
//                    if(pq.contains(it)){
//                        pq.remove(it);
//                        it = hm.get(num);
//                        it.visited++;
//                        pq.add(it);
//                    } else {
//                        hm.put(num,it);
//                        pq.add(it);
//                    }
//                } else {
//                    if(pq.contains(it)){
//                        pq.remove(it);
//                        it = hm.get(num);
//                        it.visited++;
//                        pq.add(it);
//                    } else {
//                        MyItem old = pq.poll();
//                        hm.remove(old.val);
//                        hm.put(num,it);
//                        pq.add(it);
//                        cnt++;
//                    }
//
//                }
//                //System.out.println(pq.peek());
//            }
//            System.out.println(cnt);
//        }
//    }
//}
//
//class MyItem implements Comparable<MyItem>{
//    int val;
//    int visited;
//    int putop;
//
//    public MyItem(int val, int visited, int putop) {
//        this.val = val;
//        this.visited = visited;
//        this.putop = putop;
//    }
//
//    @Override
//    public String toString() {
//        return "MyItem{" +
//                "val=" + val +
//                ", visited=" + visited +
//                ", putop=" + putop +
//                '}';
//    }
//
//    @Override
//    public int compareTo(MyItem o) {
//        if(this.visited!=o.visited){
//            return o.visited - this.visited;
//        } else {
//            return this.putop - o.putop;
//        }
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        MyItem myItem = (MyItem) o;
//
//        return val == myItem.val;
//    }
//
//    @Override
//    public int hashCode() {
//        return val;
//    }
//}
