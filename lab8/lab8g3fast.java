package lab8;

// change: using correct DFS
// AC and faster about 100ms

import java.io.*;
import java.math.*;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab8g3fast {

    public static void dfs(MyNode root){
        Stack<MyNode> s = new Stack<>();
        int time = 0;
        root.intime = time++;
        s.push(root);
        while(!s.isEmpty()){
            MyNode cur = s.peek();
            if(cur.visno!=cur.sons.size()){
                MyNode topush = cur.sons.get(cur.visno++);
                topush.intime = time++;
                s.push(topush);
            } else {
                MyNode justpop = s.pop();
                justpop.outtime = time++;
            }
        }
    }

    public static void pre(MyNode root, int[] rec){
        Stack<MyNode> s = new Stack<>();
        MyNode cur = root;
        int order = 1;
        while (cur!=null || !s.isEmpty()){
            while(cur!=null){
//                System.out.println(cur);
                rec[cur.id-1]=order++;
                s.push(cur);
                if(cur.sons.size()>cur.visno){
                    cur=cur.sons.get(cur.visno++);
                } else {
                    cur = null;
                }

            }
            if(!s.isEmpty()){
                MyNode tmp = s.pop();
                if(tmp.sons.size()>tmp.visno){
                    cur=tmp.sons.get(tmp.visno++);
                    s.push(tmp);
                } else {
                    cur = null;
                }

            }
        }
    }

    public static void post(MyNode root,int[] rec){
        Stack<MyNode> s = new Stack<>();
        MyNode cur = null,pre = null;
        int order = 1;
        s.push(root);
        while(!s.isEmpty()){
            cur = s.peek();
            if((cur.sons.size()==0)
                    ||(pre!=null && cur.sons.contains(pre))){
//                System.out.println(cur);
                rec[cur.id-1]=order++;
                s.pop();
                pre = cur;
            } else {
                ListIterator<MyNode> li = cur.sons.listIterator(cur.sons.size());
                while(li.hasPrevious()){
                    s.add(li.previous());
                }
            }
        }
    }

    public static void main(String[] args){
        InputStream inputStream = System.in;//new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }
    static class Task {
        public void solve(InputReader in, PrintWriter out) {
            int totalnum = in.nextInt();

            while(totalnum-->0){
                int n = in.nextInt(), m = in.nextInt();
                MyNode[] arr = new MyNode[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = new MyNode(i+1);
                }
                for (int i = 0; i < n-1; i++) {
                    int s = in.nextInt()-1, f = in.nextInt()-1;
                    // s for son and f for father
                    arr[s].parent = arr[f];
                    arr[f].sons.add(arr[s]);
//                    arr[s].sonsid = arr[f].sons.size();
                }
                MyNode root = arr[0];
                while(root.parent!=null){
                    root = root.parent;
                }

                dfs(root);

                for (int i = 0; i < m; i++) {
                    int ps = in.nextInt()-1, pf = in.nextInt()-1;
                    // p for possible
//                    System.out.println(arr[ps]+" "+arr[pf]);
                    boolean found = (arr[pf].intime<=arr[ps].intime&&arr[pf].outtime>=arr[ps].intime);
                    out.println(found?"Yes":"No");
                }
            }
        }

    }
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
        public int nextInt() {
            return Integer.parseInt(next());
        }
        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        //         public boolean hasNext() {
//             try {
//                 return reader.ready();
//             } catch(IOException e) {
//                 throw new RuntimeException(e);
//             }
//         }
        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch(IOException e) {
                return false;
            }
        }
        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }
    }


    static class MyNode{
        int id;
        int visno = 0;
        List<MyNode> sons;
        MyNode parent;
        int intime;
        int outtime;

        public MyNode(int id) {
            this.id = id;
            sons = new ArrayList<>();
            parent = null;
        }

        @Override
        public String toString() {
            return "MyNode{" +
                    "id=" + id +
                    ", visno=" + visno +
                    ", intime=" + intime +
                    ", outtime=" + outtime +
                    '}';
        }
    }
}

