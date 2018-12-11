package lab9;

// rewrite prim
// not helping, still wa

import java.io.*;
import java.math.*;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab9b4fast {

    // With credit to SA Zhu

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

    static class MyEdge{
        int from,to,sum,weight;

        public MyEdge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.sum = from + to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "MyEdge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", sum=" + sum +
                    ", weight=" + weight +
                    '}';
        }


    }

    static class MyNode{
        int id;
        List<MyEdge> edges;

        public MyNode(int id) {
            this.id = id;
            this.edges = new LinkedList<>();
        }

        @Override
        public String toString() {
            return "MyNode{" +
                    "id=" + id +
                    ", edges=" + edges.size() +
                    '}';
        }
    }

    static class MyPair{
        int id;
        long time;
        long weight;

        public MyPair(int id, long time, long weight) {
            this.id = id;
            this.time = time;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "MyPair{" +
                    "id=" + id +
                    ", time=" + time +
                    ", weight=" + weight +
                    '}';
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
    static class Task {

        public void solve(InputReader in2, PrintWriter out) throws IOException {
            Reader in = new Reader();
            int totalnum = in.nextInt();

            while(totalnum-->0){
                int n=in.nextInt(), m=in.nextInt();
                MyNode[] nodes = new MyNode[n];
                for (int i = 0; i < n; i++) {
                    nodes[i] = new MyNode(i);
                }
                MyEdge[] edges = new MyEdge[m];
                for (int i = 0; i < m; i++) {
                    int from = in.nextInt()-1, to=in.nextInt()-1,w=in.nextInt();
                    MyEdge e = new MyEdge(from,to,w);
                    nodes[from].edges.add(e);
                    nodes[to].edges.add(e);
                    edges[i]=e;
                }

                MyEdge minedge = edges[0];
                for (int i = 0; i < m; i++) {
                    if(edges[i].weight<minedge.weight){
                        minedge = edges[i];
                    }
                }

                long sum = 0;
                long time = 0;

                long[] rectime = new long[n];
                long[] knownmin = new long[n];
                Arrays.fill(knownmin,-1);

                PriorityQueue<MyPair> pq = new PriorityQueue<>(new Comparator<MyPair>() {
                    @Override
                    public int compare(MyPair o1, MyPair o2) {
                        return (o1.weight==o2.weight)?0:(o1.weight<o2.weight?-1:1);
                    }
                });

                boolean[] vis = new boolean[n];
                vis[minedge.from]=true;
                vis[minedge.to]=true;
                sum+=minedge.weight;

                for (int i = 0; i < 2; i++) {
                    MyNode curnode = i==0?nodes[minedge.from]:nodes[minedge.to];
                    for(MyEdge e:curnode.edges){
                        if(!vis[e.sum-curnode.id]&&(knownmin[e.sum-curnode.id]==-1||e.weight<knownmin[e.sum-curnode.id])) {
                            time++;
                            rectime[e.sum - curnode.id] = time;
                            knownmin[e.sum-curnode.id] = e.weight;
                            pq.add(new MyPair(e.sum - curnode.id, time, e.weight));
                        }
                    }
                }

                int linked = 2;

                while(linked<n){
                    MyPair curpair = pq.poll();
                    while (!pq.isEmpty()&&curpair.time<rectime[curpair.id]){
                        curpair = pq.poll();
                    }

                    MyNode curnode = nodes[curpair.id];
                    sum+=curpair.weight;
                    vis[curnode.id]=true;
                    //System.out.println(curpair);
                    linked++;
                    for(MyEdge e:curnode.edges){
                        if(!vis[e.sum-curnode.id]&&(knownmin[e.sum-curnode.id]==-1||e.weight<knownmin[e.sum-curnode.id])){
                            time++;
                            rectime[e.sum-curnode.id]=time;
                            pq.add(new MyPair(e.sum-curnode.id,time,e.weight));
                        }

                    }
                }

                out.println(sum);

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

}

