package lab9;

// With credit to Wavator

// idea: do dijkstra from alice and bob separately
// get 2 of all city distance form alice and bob
// iterate over all city, get the min(max(da,db))

// very quick wa
// perhaps the long problem?
// change to long seems not helping

// according to others, not long problem
// rewrite the dijkstra algorithm
// ac
// black questions.jpg ???

import java.io.*;
import java.math.*;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab9ffast {

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

    public static long[] dijkstraFast2(MyNode s, MyNode[] arr){
        long[] dis = new long[arr.length];
        Arrays.fill(dis,-1); // add won't overflow
        dis[s.id]=0;
        // key=id, value=dis
        PriorityQueue<Map.Entry<Integer,Long>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Long>>() {
            @Override
            public int compare(Map.Entry<Integer, Long> o1, Map.Entry<Integer, Long> o2) {
                long cmp = o1.getValue()-o2.getValue();
                if(cmp==0){
                    return 0;
                } else if (cmp>0){
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        for(MyEdge edge:s.edges){
            dis[edge.sum-s.id] = edge.weight;
            pq.add(new AbstractMap.SimpleEntry<Integer, Long>(edge.sum-s.id, (long)edge.weight));
        }

        while(!pq.isEmpty()){
            // make sure the new next
            Map.Entry<Integer,Long> next = pq.poll();
            while(next.getValue()<dis[next.getKey()]){
                next = pq.poll();
            }
            MyNode cur = arr[next.getKey()];
            for(MyEdge edge:cur.edges){
                if(((edge.weight + dis[cur.id]) < dis[edge.sum - cur.id]) || (dis[edge.sum - cur.id] == -1)){
                    dis[edge.sum-cur.id] = edge.weight + dis[cur.id];
                    pq.add(new AbstractMap.SimpleEntry<>(edge.sum-cur.id,dis[edge.sum-cur.id]));
                }
            }
        }

        return dis;
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
                int n = in.nextInt(),m=in.nextInt();
//                int n=6001, m=6000;
                MyNode[] nodearr = new MyNode[n];
                for (int i = 0; i < n; i++) {
                    nodearr[i] = new MyNode(i);
                }
                for (int i = 0; i < m; i++) {
                    int n1=in.nextInt()-1, n2=in.nextInt()-1, w=in.nextInt();
//                    int n1 = i, n2=i+1, w=200000000;
                    MyEdge edge = new MyEdge(nodearr[n1],nodearr[n2],w);
                    nodearr[n1].edges.add(edge);
                    nodearr[n2].edges.add(edge);
                }

                int alicepos = in.nextInt()-1, bobpos = in.nextInt()-1;
//                int alicepos = 0, bobpos = 6000;


                long[] alice = dijk(nodearr[alicepos],nodearr);
                long[] bob = dijk(nodearr[bobpos],nodearr);
//                System.out.println(Arrays.toString(alice));
//                System.out.println(Arrays.toString(bob));

                long knownmin = Long.MAX_VALUE;
                long minid = -1;

                for (int i = 0; i < n; i++) {
                    long res = Math.max((long) alice[i],(long) bob[i]);
                    if(res<knownmin){
                        knownmin = res;
                        minid = i;
                    }
                }

                out.println(knownmin);
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

