//package lab6;
//import java.io.*;
//import java.math.*;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.StringTokenizer;
///**
// * Built using CHelper plug-in
// * Actual solution is at the top
// * Author: Wavator
// */
//public class lab6gfast {
//    public static void findMaxRe(TreeNode root){
//        if(root.maxsize!=-1){
//            return;
//        }
//        int cnt=1;
//        for(TreeNode node:root.out){
//            if(node.maxsize==-1){
//                // not init
//                findMaxRe(node);
//            }
//            cnt+=node.maxsize;
//        }
//        root.maxsize=cnt;
//    }
//
//    public static int findMax(TreeNode root){
//        LinkedList<TreeNode> queue = new LinkedList<>();
//        int cnt=1;
//        queue.addAll(root.out);
//        while(!queue.isEmpty()){
//            TreeNode node = queue.pollFirst();
//            cnt++;
//            queue.addAll(node.out);
//        }
//        return cnt;
//    }
//
//    public static int buildGraph(TreeNode root, int parent, TreeNode[] arr){
//        if(root.maxsize!=-1){
//            return root.maxsize;
//        }
//        int cnt = 1;
//        for(TreeNode node:root.li){
////            if(node.id!=parent){
//            if(node.val<=root.val){
//                root.in.add(node);
//                if(node.id!=parent){
//                    buildGraph(node, root.id,arr);
//                }
//            } else {
//                root.out.add(node);
////                cnt++;
//                if(node.id!=parent){
//                    cnt+=buildGraph(node,root.id,arr);
//                } else {
//                    cnt+=arr[parent].maxsize;
//                }
//
//            }
////            }
//        }
//
//        root.maxsize=cnt;
//        return root.maxsize;
//
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
//                TreeNode[] arr = new TreeNode[n];
//                for (int i = 0; i < n; i++) {
//                    int val = in.nextInt();
//                    arr[i] = new TreeNode(val,i);
//                }
//                for (int i = 0; i < n-1; i++) {
//                    TreeNode t1 = arr[in.nextInt()-1], t2 = arr[in.nextInt()-1];
//                    if(t1.val<t2.val){
//                        t1.out.add(t2);
//                    } else {
//                        t2.out.add(t1);
//                    }
//                }
//
//                // search
//                int max = Integer.MIN_VALUE;
//                for (int i = 0; i < n; i++) {
//                    if(arr[i].maxsize==-1){
//                        findMaxRe(arr[i]);
//                    }
//                    int res = arr[i].maxsize;
////                int res = findMax(arr[i]);
////                System.out.println(i+" "+res);
//                    if(res>max){
//                        max = res;
//                    }
//                }
//                // choose max
//                System.out.println(max);
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
//class TreeNode{
//    List<TreeNode> li;
//    List<TreeNode> in;
//    List<TreeNode> out;
//    int id;
//    int val;
//    int maxsize;
//    int depth;
//
//    public TreeNode(int val, int id){
//        li = new ArrayList<>();
//        in = new ArrayList<>();
//        out = new ArrayList<>();
//        this.val=val;
//        this.id=id;
//        maxsize=-1;
//        depth=0;
//    }
//
//    @Override
//    public String toString() {
//        return "TreeNode{" +
//                "val=" + val +
//                ", maxsize=" + maxsize +
//                ", depth=" + depth +
//                '}';
//    }
//}
