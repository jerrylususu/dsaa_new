//package lab8;
//
//// algorithm tle confirmed.
//// try to change treeset into hashset
//// still tle
//// try to implement an array-based, instead of object-based
//
//
//import java.io.*;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.util.*;
//
///**
// * Built using CHelper plug-in
// * Actual solution is at the top
// * Author: Wavator
// */
//public class lab8hfast {
//
//    public static void reset(MyNode[] arr){
//        for(MyNode n:arr){
//            n.depth = -1;
//            n.vis = false;
//        }
//    }
//
//    public static int findNearest(MyNode node,HashSet<Integer> ts){
//        LinkedList<MyNode> q = new LinkedList<>();
//        q.addLast(node);
//        node.vis = true;
//        node.depth = 0;
//
//        while(!q.isEmpty()){
//            MyNode cur = q.pollFirst();
//            for(MyNode n:cur.edges){
//                if(!n.vis){ // hasn't visited yet
//                    n.vis = true;
//                    n.depth = cur.depth + 1;
//                    if(ts.contains(n.id)){
//                        return n.depth; // find one nearest, stop as early as possible
//                    } else {
//                        q.addLast(n); // didn't find, continue to bfs
//                    }
//                }
//            }
//        }
//        return Integer.MAX_VALUE; // didn't find
//    }
//
//
//    static class MyNode{
//        int id;
//        boolean vis;
//        int depth;
//        List<MyNode> edges;
//
//        private MyNode(int id){
//            this.id = id;
//            this.vis = false;
//            this.depth = -1;
//            edges = new ArrayList<>();
//        }
//
//        @Override
//        public String toString() {
//            return "MyNode{" +
//                    "id=" + id +
//                    ", vis=" + vis +
//                    ", depth=" + depth +
//                    ", edges=" + edges.size() +
//                    '}';
//        }
//    }
//
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
//            while (totalnum-->0){
//
//                // build map
//                int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
//                MyNode[] arr = new MyNode[n];
//                for (int i = 0; i < n; i++) {
//                    arr[i] = new MyNode(i+1);
//                }
//                for (int i = 0; i < m; i++) {
//                    int n1 = in.nextInt()-1, n2 = in.nextInt()-1;
//                    arr[n1].edges.add(arr[n2]);
//                    arr[n2].edges.add(arr[n1]);
//                }
//                HashSet<Integer> ts = new HashSet<>();
//                for (int i = 0; i < k; i++) {
//                    ts.add(in.nextInt());
//                }
//
//                // query for each node in set once?
//                int min = Integer.MAX_VALUE;
//                for(Integer nodeid:ts){
//                    int res = findNearest(arr[nodeid-1],ts);
//                    if(min>res){
//                        min = res;
//                    }
//                    reset(arr);
//                }
//
//                // output
//                out.println(min);
//
//
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
