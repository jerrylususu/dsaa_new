//package lab8;
//
//// algorithm tle confirmed.
//// try to change treeset into hashset
//// still tle
//// try to implement an array-based, instead of object-based
//// still tle, took even longer time?
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
//public class lab8h2fast {
//
//    public static int findNearest(int s,List<Integer>[] edges, boolean[] vis, int[] depth,HashSet<Integer> ts){
//        LinkedList<Integer> q = new LinkedList<>();
//        q.addLast(s);
//        vis[s] = true;
//        depth[s] = 0;
//
//        while(!q.isEmpty()){
//            int cur = q.pollFirst();
//            for(int n:edges[cur]){
//                if(!vis[n]){ // hasn't visited yet
//                    vis[n] = true;
//                    depth[n] = depth[cur] + 1;
//                    if(ts.contains(n)){
//                        return depth[n]; // find one nearest, stop as early as possible
//                    } else {
//                        q.addLast(n); // didn't find, continue to bfs
//                    }
//                }
//            }
//        }
//        return Integer.MAX_VALUE; // didn't find
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
//                List<Integer>[] edges = new List[n];
//
//                for (int i = 0; i < m; i++) {
//                    int n1 = in.nextInt()-1, n2 = in.nextInt()-1;
//                    if(edges[n1]==null){
//                        edges[n1] = new ArrayList<>();
//                    }
//                    if (edges[n2] == null) {
//                        edges[n2] = new ArrayList<>();
//                    }
//                    edges[n1].add(n2);
//                    edges[n2].add(n1);
//                }
//                HashSet<Integer> ts = new HashSet<>();
//                for (int i = 0; i < k; i++) {
//                    ts.add(in.nextInt()-1);
//                }
//
//                boolean[] vis = new boolean[n];
//                int[] depth = new int[n];
//
//                // query for each node in set once?
//                int min = Integer.MAX_VALUE;
//                for(Integer nodeid:ts){
//                    int res = findNearest(nodeid,edges,vis,depth,ts);
//                    if(min>res){
//                        min = res;
//                    }
//                    Arrays.fill(vis,false);
//                    Arrays.fill(depth,Integer.MAX_VALUE);
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
