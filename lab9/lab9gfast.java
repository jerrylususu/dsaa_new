package lab9;

// this seems need some permutation
// yes...
// the problem here is just how to generate permutation smartly
// and use them in iteration correctly

// idea: if need go through 3,4,5
// then consider 0, p{3,4,5}, n-1, where p stands for permutation
// there are 3! = 6 possibilities, need to check all

// wa
// the iteration has some problem...
// changed index
// ac

import java.io.*;
import java.math.*;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab9gfast {

    // With credit to SA Zhu
    static class Task {

        public void solve(InputReader in2, PrintWriter out) throws IOException {
            Reader in = new Reader();
            int totalnum = in.nextInt();

            while(totalnum-->0) {
                int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
                MyNode[] nodearr = new MyNode[n];
                for (int i = 0; i < n; i++) {
                    nodearr[i] = new MyNode(i);
                }
                for (int i = 0; i < m; i++) {
                    int n1 = in.nextInt() - 1, n2 = in.nextInt() - 1, w = in.nextInt();
                    MyEdge edge = new MyEdge(nodearr[n1], nodearr[n2], w);
                    nodearr[n1].edges.add(edge);
                    nodearr[n2].edges.add(edge);
                }

                int[] mustpass = new int[k + 2];
                mustpass[0] = 0;
                for (int i = 1; i <= k; i++) {
                    mustpass[i] = in.nextInt() - 1;
                }
                mustpass[k + 1] = n - 1;

                long[][] dist = new long[k+2][];
                for (int i = 0; i < k+2; i++) {
                    dist[i] = dijk(nodearr[mustpass[i]], nodearr);
                }

                int[] parr = new int[k]; //p for permutation
                for (int i = 0; i < k; i++) {
                    parr[i] = i+1;
                }

                List<List<Integer>> pli = new LinkedList<>();
                getA(parr,0,pli);

                long knownmin = Long.MAX_VALUE;
                for(List<Integer> li:pli){
                    // for each possible permutation
                    long dis = 0;
                    dis += dist[0][mustpass[li.get(0)]];
                    for (int i = 0; i < k-1; i++) {
                        dis += dist[li.get(i)][mustpass[li.get(i+1)]];
                    }
//                    System.out.println(li.get(k-1)+" "+(n-1));
                    dis += dist[li.get(k-1)][n-1];
                    if(dis<knownmin){
                        knownmin = dis;
                    }
                }
                out.println(knownmin);
            }

        }

    }


    public static void getA(int[] arr, int idx, List<List<Integer>> li){
        if(idx==arr.length) {
//            System.out.println(Arrays.toString(arr));
            ArrayList<Integer> l = new ArrayList<Integer>() {{ for (int i : arr) add(i); }};
            li.add(l);
            return;
        }

        for (int i = idx; i < arr.length; i++) {
            swap(arr,idx,i);
            getA(arr,idx+1,li);
            swap(arr,idx,i);
        }
    }

    public static void swap(int[] arr, int a, int b){
        int tmp = arr[b];
        arr[b] = arr[a];
        arr[a] = tmp;
    }

    public static long[] dijk(MyNode s,MyNode[] arr){
        int len = arr.length;
        long[] dist = new long[len];
        Arrays.fill(dist,-1);

        dist[s.id]=0;
        class Pair{
            int id;
            long dist;

            public Pair(int id, long dist) {
                this.id = id;
                this.dist = dist;
            }
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> {
            long cmp = o1.dist - o2.dist;
            return (cmp==0) ? 0: ((cmp<0)? -1 : 1);
        });

        pq.add(new Pair(s.id,0));

        while(!pq.isEmpty()){
            Pair curpair = pq.poll();
            while(curpair.dist<dist[curpair.id]){
                curpair=pq.poll();
            }
            MyNode curnode = arr[curpair.id];
            for(MyEdge e:curnode.edges){
                if(dist[e.sum-curnode.id]==-1||e.weight+dist[curnode.id]<dist[e.sum-curnode.id]){
                    dist[e.sum-curnode.id] = e.weight+dist[curnode.id];
                    pq.add(new Pair(e.sum-curnode.id,dist[e.sum-curnode.id]));
                }
            }
        }

        return dist;
    }

    static class MyEdge{
        MyNode from;
        MyNode to;
        int sum;
        int weight;


        public MyEdge(MyNode from, MyNode to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
            this.sum = from.id+to.id;
        }

        @Override
        public String toString() {
            return "MyEdge{" +
                    "from=" + from.id +
                    ", to=" + to.id +
                    ", weight=" + weight +
                    '}';
        }
    }
    static class MyNode{
        int id;
        List<MyEdge> edges;
//        int tmpdist = Integer.MAX_VALUE;

        public MyNode(int id) {
            this.id = id;
            this.edges = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "MyNode{" +
                    "id=" + id +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MyNode myNode = (MyNode) o;

            return id == myNode.id;
        }

        @Override
        public int hashCode() {
            return id;
        }
    }

    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
    public static void main(String[] args) throws IOException{
        InputStream inputStream = System.in;//new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
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
}

