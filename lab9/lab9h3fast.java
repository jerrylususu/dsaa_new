package lab9;

// first naively tried dijkstra on all possible roads
// tle even with fast read and write

// then realized that this is a geometry problem
// if the direct route is in cover of a sphere, that dist can be shortened
// but this will lead to more?

// try to remove one class
// not helping much
// seems we are in some strange situation... don't know what is happening


import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab9h3fast {

    // With credit to SA Zhu
    static class Task {

        public void solve(InputReader in2, PrintWriter out) throws IOException {
            Reader in = new Reader();
            int totalnum = in.nextInt();

            while(totalnum-->0) {
                int holen = in.nextInt();
                MyNode[] holearr = new MyNode[holen+2];
                for (int i = 1; i <= holen; i++) {
                    holearr[i] = new MyNode(i,in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt());
                }
                holearr[0] = new MyNode(0,in.nextInt(),in.nextInt(),in.nextInt(),0);
                holearr[holen+1] = new MyNode(holen+1,in.nextInt(),in.nextInt(),in.nextInt(),0);

                int n = holen+2;
//                MyNode[] nodearr = new MyNode[n];
//                for (int i = 0; i < n; i++) {
//                    nodearr[i] = new MyNode(i);
//                }

                for (int n1 = 0; n1 < n-1; n1++) {
                    for (int n2 = n1+1; n2 < n; n2++) {
                        MyEdge edge = new MyEdge(holearr[n1], holearr[n2], weight(holearr,n1,n2));
                        holearr[n1].edges.add(edge);
                        holearr[n2].edges.add(edge);
                    }
                }

                //System.out.println(System.currentTimeMillis());
                double[] dist = dijk(holearr[0],holearr);
                //System.out.println(System.currentTimeMillis());

                out.printf("%d\n",Math.round(dist[holen+1]));


            }

        }

    }

    public static double pow(int i, int j){
        return i*i;
    }

    public static double weight(MyNode[] arr, int i, int j){
        return 100*(Math.sqrt(pow(arr[i].x-arr[j].x,2)+pow(arr[i].y-arr[j].y,2)+pow(arr[i].z-arr[j].z,2)) - arr[i].r - arr[j].r);
    }

    public static double[] dijk(MyNode s,MyNode[] arr){
        int len = arr.length;
        double[] dist = new double[len];
        Arrays.fill(dist,-1);

        dist[s.id]=0;
        class Pair{
            int id;
            double dist;

            public Pair(int id, double dist) {
                this.id = id;
                this.dist = dist;
            }
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Double.compare(o1.dist,o2.dist);
            }
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

    static class Hole{
        int x,y,z,r;
        public Hole(int x, int y, int z, int r) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.r = r;
        }
    }
    static class MyEdge{
        MyNode from;
        MyNode to;
        int sum;
        double weight;


        public MyEdge(MyNode from, MyNode to, double weight) {
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
        int x,y,z,r;
        List<MyEdge> edges;
//        int tmpdist = Integer.MAX_VALUE;

        public MyNode(int id) {
            this.id = id;
            this.edges = new ArrayList<>();
        }

        public MyNode(int id, int x, int y, int z, int r) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
            this.r = r;
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

