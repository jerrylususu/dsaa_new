//package lab6;
//
//import java.io.*;
//import java.math.*;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.PriorityQueue;
//import java.util.StringTokenizer;
///**
// * Built using CHelper plug-in
// * Actual solution is at the top
// * Author: Wavator
// */
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
//
//public class lab6epq2fast {
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
//                int pocketMax = in.nextInt();
//                int storen = in.nextInt();
//                MyItem[] arr = new MyItem[pocketMax];
//                HashMap<Integer,MyItem> hm = new HashMap<>();
//                PriorityQueue<MyItem> pq = new PriorityQueue<>(pocketMax, new Comparator<MyItem>() {
//                    @Override
//                    public int compare(MyItem o1, MyItem o2) {
//                        if(o1.visited!=o2.visited){
//                            return o2.visited-o1.visited;
//                        } else {
//                            return o1.putop-o2.putop;
//                        }
//                    }
//                });
//
//
//                int cnt = 0;
//                for (int i = 0; i < storen; i++) {
//                    int num = in.nextInt();
//                    MyItem it = new MyItem(num,1,i);
//
//                    if(pq.size()<pocketMax){
//                        if(pq.contains(it)){
//                            pq.remove(it);
//                            it = hm.get(num);
//                            it.visited++;
//                            pq.add(it);
//                        } else {
//                            hm.put(num,it);
//                            pq.add(it);
//                        }
//                    } else {
//                        if(pq.contains(it)){
//                            pq.remove(it);
//                            it = hm.get(num);
//                            it.visited++;
//                            pq.add(it);
//                        } else {
//                            MyItem old = pq.poll();
//                            hm.remove(old.val);
//                            hm.put(num,it);
//                            pq.add(it);
//                            cnt++;
//                        }
//
//                    }
//                    //System.out.println(pq.peek());
//                }
//                System.out.println(cnt);
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
