//package lab8;
//
//
//
//
//import java.io.*;
//import java.math.*;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.StringTokenizer;
///**
// * Built using CHelper plug-in
// * Actual solution is at the top
// * Author: Wavator
// */
//public class lab8c2fast {
//
//
//        public static void setLevel(MyGraphNode s, boolean[][] rec,MyGraphNode[] arr){
//        LinkedList<MyGraphNode> q = new LinkedList<>();
//        q.addLast(s);
//        s.vis = true; s.tmplevel = 0;
//        while(!q.isEmpty()){
//            MyGraphNode cur = q.pollFirst();
////            System.out.println(cur);
//            int n = rec.length;
//            int d = cur.id-1;
//            for (int i = 0; i < rec.length; i++) {
//                if(rec[i][d]){
//                    if(!arr[i].vis){
//                        arr[i].vis = true;
//                        arr[i].tmplevel = cur.tmplevel+1;
//                        q.add(arr[i]);
//                    }
//                }
//            }
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
//            while(totalnum-->0){
//                int n = in.nextInt();
//                int m = in.nextInt();
//                int s = in.nextInt()-1;
//
//                MyGraphNode[] arr = new MyGraphNode[n];
//                for (int i = 0; i < n; i++) {
//                    arr[i] = new MyGraphNode(i+1);
//                }
//
//                boolean[][] rec = new boolean[n][n];
//
//                for (int i = 0; i < m; i++) {
//                    int n1 = in.nextInt()-1, n2 = in.nextInt()-1;
//                    rec[n1][n2] = true;
//                    rec[n2][n1] = true;
//                }
//
//                setLevel(arr[s],rec,arr);
//
//                for (int i = 0; i < n; i++) {
//                    System.out.print(arr[i].tmplevel+" ");
//                }
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
//
//class MyGraphNode {
//    int id;
//    List<MyGraphNode> link;
//    boolean vis = false;
//    int tmplevel = -1;
//
//    public MyGraphNode(int id) {
//        this.id = id;
//        this.link = new LinkedList<>();
//    }
//}