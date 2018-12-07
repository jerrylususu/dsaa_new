package lab8;

// change: using chain forward star

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab8g4fast {

    public static void dfs(MyNode root,MyNode[] arr,int[] head, MyEdge[] edges){
        Stack<MyNode> s = new Stack<>();
        int time = 0;
        root.intime = time++;
        s.push(root);
        while(!s.isEmpty()){
            MyNode cur = s.peek();
            if(cur.nextvis!=-1){
                MyEdge curedge = edges[cur.nextvis];
                cur.nextvis = curedge.lastedgeid;
                MyNode topush = arr[curedge.tonodeid];
                topush.intime = time++;
                s.push(topush);
            } else {
                MyNode justpop = s.pop();
                justpop.outtime = time++;
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
                    arr[i] = new MyNode(i);
                }

                MyEdge[] edges = new MyEdge[n-1];
                int[] head = new int[n];
                int[] father = new int[n];
                Arrays.fill(head,-1);
                Arrays.fill(father,-1);
                int cnt =0;

                for(int i=0;i<n-1;i++){
                    int s = in.nextInt()-1, f = in.nextInt()-1;
                    father[s] = f;
                    MyEdge e = new MyEdge(s,1,head[f]);
                    edges[cnt++] = e;
                    head[f]=cnt-1;
                }

                // find father
                MyNode root = arr[0];
                while(father[root.id]!=-1){
                    root = arr[father[root.id]];
                }

                for (int i = 0; i < n; i++) {
                    arr[i].nextvis = head[i];
                }

                dfs(root,arr,head,edges);

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

    static class MyEdge{
        int tonodeid;
        int weight;
        int lastedgeid = -1;

        public MyEdge(int tonodeid, int weight, int lastedgeid) {
            this.tonodeid = tonodeid;
            this.weight = weight;
            this.lastedgeid = lastedgeid;
        }

        @Override
        public String toString() {
            return "MyEdge{" +
                    "tonodeid=" + tonodeid +
                    ", weight=" + weight +
                    ", lastedgeid=" + lastedgeid +
                    '}';
        }
    }

    static class MyNode{
        int id;
        int nextvis;
        int intime;
        int outtime;

        public MyNode(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "MyNode{" +
                    "id=" + id +
                    ", nextvis=" + nextvis +
                    ", intime=" + intime +
                    ", outtime=" + outtime +
                    '}';
        }
    }
}

