//package lab6;
//
//import java.util.*;
//
//public class lab6epq {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while(totalnum-->0){
//            int pocketMax = in.nextInt();
//            int storen = in.nextInt();
//            MyItem[] arr = new MyItem[pocketMax];
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
//                if(pq.size()<pocketMax){
//                    boolean newitem = false;
//                    LinkedList<MyItem> li= new LinkedList<>();
//                    while(pq.size()>0){
//                        MyItem it = pq.poll();
//                        if(it.val==num){
//                            it.visited++;
//                            newitem = true;
//                        }
//                        li.add(it);
//
//                    }
//                    if(!newitem){
//                        li.add(new MyItem(num,1,i));
//                    }
//                    pq.addAll(li);
//                } else {
//                    LinkedList<MyItem> li = new LinkedList<>();
//                    boolean found = false;
//                    while(pq.size()!=0){
//                        MyItem it = pq.poll();
//                        if(it.val==num){
//                            found = true;
//                            it.visited++;
//                        }
//                        li.add(it);
//                    }
//                    pq.addAll(li);
//                    if(!found){
//                        cnt++;
//                        MyItem d = pq.poll();
//                        //System.out.println("dropped"+d);
//                        pq.add(new MyItem(num,1,i));
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
//class MyItem{
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
//}
