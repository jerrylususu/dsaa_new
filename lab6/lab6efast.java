//package lab6;
//
//
//
//
//import java.io.*;
//import java.math.*;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.StringTokenizer;
///**
// * Built using CHelper plug-in
// * Actual solution is at the top
// * Author: Wavator
// */
//public class lab6efast {
//    public static void main(String[] args){
//        InputStream inputStream = System.in;//new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
//        OutputStream outputStream = System.out;
//        InputReader in = new InputReader(inputStream);
//        PrintWriter out = new PrintWriter(outputStream);
//        Task solver = new Task();
//        solver.solve(in, out);
//        out.close();
//    }
//    static class Task {
//        public void solve(InputReader in, PrintWriter out) {
//            int totalnum = in.nextInt();
//
//            while(totalnum-->0){
//                int pocketSize = in.nextInt();
//                int storeNum = in.nextInt();
//
////                if(pocketSize>=storeNum){
////                    for (int i = 0; i < storeNum; i++) {
////                        in.nextInt();
////                    }
////                    System.out.println(0);
////                    continue;
////                }
//
////                HashMap<Integer, MyItem> hm = new HashMap<>(pocketSize);
////            HashMap<Integer, Integer> pos = new HashMap<>(pocketSize);
//                MinHeap<MyItem> mh = new MinHeap<>(storeNum);
//                HashMap<Integer, Integer> vishm = new HashMap<>();
//                HashMap<Integer, Integer> timehm = new HashMap<>();
//                int removeCount = 0;
//                for (int i = 1; i <= storeNum; i++) {
//                    int num = in.nextInt();
//                    if(vishm.size()<pocketSize){
//                        // not full
//                        if(!vishm.containsKey(num)){
//                            // not exist
//                            MyItem item = new MyItem(num,1,i);
//                            vishm.put(num,1);
//                            timehm.put(num,i);
//                            mh.add(new MinHeap.Node<>(item));
//                        } else {
//                            // already exist
//
//                            // increase visited
//                            vishm.put(num,vishm.get(num)+1);
//                            MyItem it = new MyItem(num, vishm.get(num), timehm.get(num));
//
//                            // just put in
//                            mh.add(new MinHeap.Node<>(it));
//                        }
//                    } else {
//                        // already full
//                        if(vishm.containsKey(num)){
//                            // already bought
//                            // already exist
//
//                            // increase visited
//                            vishm.put(num,vishm.get(num)+1);
//                            MyItem it = new MyItem(num, vishm.get(num), timehm.get(num));
//
//                            // just put in
//                            mh.add(new MinHeap.Node<>(it));
//                        } else {
//                            // not bought
//                            // need to pop out
//
//                            removeCount++;
//
//                            // remove old
//                            MyItem popout = mh.remove().getData();
//                            while((!vishm.containsKey(popout.val))
//                                    ||(popout.visited!=vishm.get(popout.val)
//                                    ||popout.putop!=timehm.get(popout.val))){
//                                popout = mh.remove().getData();
//                            }
//
//                                vishm.remove(popout.val);
//                                timehm.remove(popout.val);
//
//
////                        pos.remove(popout.val);
////                        System.out.println("removed"+popout);
//
//                            // insert new
//                            MyItem item = new MyItem(num,1,i);
//                            vishm.put(num,1);
//                            timehm.put(num,i);
//                            mh.add(new MinHeap.Node<>(item));
////                        pos.put(num,newpos);
//                        }
//                    }
////                System.out.println(hm);
////                System.out.println(mh.peek());
////                System.out.println(pos);
//                }
//
//                System.out.println(removeCount);
//            }
//        }
//
//    }
//    static class InputReader {
//        public BufferedReader reader;
//        public StringTokenizer tokenizer;
//        public InputReader(InputStream stream) {
//            reader = new BufferedReader(new InputStreamReader(stream), 32768);
//            tokenizer = null;
//        }
//        public String next() {
//            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
//                try {
//                    tokenizer = new StringTokenizer(reader.readLine());
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            return tokenizer.nextToken();
//        }
//        public int nextInt() {
//            return Integer.parseInt(next());
//        }
//        public long nextLong() {
//            return Long.parseLong(next());
//        }
//
//        public double nextDouble() {
//            return Double.parseDouble(next());
//        }
//
//        public char[] nextCharArray() {
//            return next().toCharArray();
//        }
//
//        //         public boolean hasNext() {
////             try {
////                 return reader.ready();
////             } catch(IOException e) {
////                 throw new RuntimeException(e);
////             }
////         }
//        public boolean hasNext() {
//            try {
//                String string = reader.readLine();
//                if (string == null) {
//                    return false;
//                }
//                tokenizer = new StringTokenizer(string);
//                return tokenizer.hasMoreTokens();
//            } catch(IOException e) {
//                return false;
//            }
//        }
//        public BigInteger nextBigInteger() {
//            return new BigInteger(next());
//        }
//
//        public BigDecimal nextBigDecinal() {
//            return new BigDecimal(next());
//        }
//    }
//}
//
//class MinHeap<T extends Comparable<T>>{
//    Node<T>[] arr;
//    int lastptr; // last ptr for actual storage
//    int cursize; // current size of heap
//    HashMap<T,Integer> cache;
//
//    public MinHeap(int size){
//        this.arr = new Node[size];
//        this.lastptr = -1;
//        this.cursize =0;
//        this.cache = new HashMap<>(size);
//    }
//
//    static class Node<T extends Comparable<T>> implements Comparable<Node>{
//        private T data;
//
//        public Node(T d){
//            this.data = d;
//        }
//
//        public T getData(){
//            return data;
//        }
//
//        public void setData(T d){
//            this.data=d;
//        }
//
//        @Override
//        public int compareTo(Node o){
//            return this.data.compareTo((T) o.getData());
//        }
//
//        @Override
//        public String toString() {
//            return data.toString();
//        }
//    }
//    public void swap(int i, int j){
//        Node<T> tmp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = tmp;
//    }
//
//    public int rearrange(int pos){
//        int cur = pos;
//        int parent = (cur%2==0)?(cur-2)/2:(cur-1)/2;
//        if(parent>=0&&arr[parent].compareTo(arr[pos])>0){
//            return shiftUp(pos);
//        }
//        return shiftDown(pos);
//    }
//
//    public void rearrange(T t){
//        int pos = -1;
//        if(cache.containsKey(t)&&arr[cache.get(t)].equals(t)){
//            pos=cache.get(t);
//        } else {
//            for (int i = 0; i <= lastptr; i++) {
//                if(arr[i].getData().equals(t)){
//                    pos=i;
//                    cache.put(t,i);
//                    break;
//                }
//            }
//        }
//
//
//        if(pos!=-1){
//            rearrange(pos);
//        } else {
//            System.out.println("ERROR"+t);
//        }
//    }
//
//    public int shiftUp(int pos){
//        int cur=pos;
//        int parent=(cur%2==0)?(cur-2)/2:(cur-1)/2;
//        while(parent>=0){
//            if(arr[parent].compareTo(arr[cur])>0){
//                swap(parent,cur);
//                cur=parent;
//            } else {
//                break;
//            }
//
//            if(cur==0){
//                break;
//            }
//            parent=(cur%2==0)?(cur-2)/2:(cur-1)/2;
//        }
//        return cur;
//    }
//
//    public int shiftDown(int pos){
//        int cur = pos;
//        while(cur<=lastptr){
//            if(2*cur+1<=lastptr&&2*cur+2<=lastptr) {
//                Node<T> l = arr[2 * cur + 1];
//                Node<T> r = arr[2 * cur + 2];
//
//                int small = -1, big = -1;
//                if (l.compareTo(r)<=0) {
//                    small = 2 * cur + 1;
//                    big = 2 * cur + 2;
//                } else {
//                    small = 2 * cur + 2;
//                    big = 2 * cur + 1;
//                }
//
//                if (arr[cur].compareTo(arr[small])<=0) {
//                    break;
//                } else {
//                    int swaprec = -1;
//                    swaprec = small;
//
////                System.out.println("swapped"+swaprec+cur);
//
//                    swap(swaprec,cur);
//                    cur = swaprec;
//                }
//            } else if (2*cur+1<=lastptr && 2*cur+2>lastptr){
//                if(arr[cur].compareTo(arr[2*cur+1])>0){
//                    swap(cur,2*cur+1);
//                    cur=2*cur+1;
//                } else {
//                    break;
//                }
//            } else {
//                break;
//            }
//        }
//        return cur;
//    }
//
//    public int add(Node<T> i){
//        // put in
//        cursize++;
//        lastptr++;
//        arr[lastptr]=i;
//
//        // try up
//        int pos = shiftUp(lastptr);
//        cache.put(i.getData(),pos);
//        return pos;
//    }
//
//    public Node<T> remove(){
//        Node<T> res = arr[0];
//        cache.remove(res.getData());
//        arr[0] = arr[lastptr];
//        lastptr--;
//        cursize--;
//
//        shiftDown(0);
//        return res;
//    }
//
//    public T peek(){
//        return arr[0].getData();
//    }
//
//    public void debug(){
//        System.out.println(Arrays.toString(Arrays.copyOfRange(this.arr,0,10)));
//    }
//
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
