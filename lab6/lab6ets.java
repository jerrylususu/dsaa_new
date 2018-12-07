package lab6;

import java.util.*;

public class lab6ets {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();
        while(totalnum-->0){
            int size = in.nextInt();
            int store = in.nextInt();
            TreeSet<MyItem> ts = new TreeSet<>();
//            HashMap<Integer, Integer> hmvis = new HashMap<>();
//            HashMap<Integer, Integer> hmopn = new HashMap<>();
            HashMap<Integer, MyItem> hmit = new HashMap<>();
            int removeCount = 0;
            for (int i = 0; i < store; i++) {
                int num = in.nextInt();
                if(ts.size()<size){
                    // not full

                    if(!hmit.containsKey(num)){
                        // new
                        MyItem it = new MyItem(num,1,i);
                        ts.add(it);
                        hmit.put(num,it);
                    } else {
                        // existed
                        MyItem old = hmit.get(num);
                        boolean re = ts.remove(old);
//                        System.out.println(re);
                        old.visited++;
                        ts.add(old);
//                        hmit.put(num,old);
                    }
                } else {
                    // full

                    if(hmit.containsKey(num)){
                        // existed
                        MyItem old = hmit.get(num);
                        boolean re = ts.remove(old);
//                        System.out.println(re);
                        old.visited++;
                        ts.add(old);
                    } else {
                        // new
                        MyItem it = new MyItem(num,1,i);
                        removeCount++;
                        MyItem rem = ts.pollFirst();
//                        System.out.println(num+" "+rem);
                        hmit.remove(rem.val);
                        ts.add(it);
                        hmit.put(num,it);
                    }

                }
            }
            System.out.println(removeCount);


        }

    }
}

class MyItem implements Comparable<MyItem>{
    int val;
    int visited;
    int putop;

    public MyItem(int val, int visited, int putop) {
        this.val = val;
        this.visited = visited;
        this.putop = putop;
    }

    @Override
    public String toString() {
        return "MyItem{" +
                "val=" + val +
                ", visited=" + visited +
                ", putop=" + putop +
                '}';
    }

    @Override
    public int compareTo(MyItem o) {
        if(this.visited!=o.visited){
            return o.visited - this.visited;
        } else {
            return this.putop - o.putop;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyItem myItem = (MyItem) o;

        return val == myItem.val;
    }

    @Override
    public int hashCode() {
        return val;
    }
}