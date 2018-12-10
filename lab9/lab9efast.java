package lab9;

// With credit to Wavator

// need fast read and write


import java.io.*;
import java.math.*;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab9efast {

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


    static class PQHelper{
        int nodeid;
        int incount;
        int time;

        public PQHelper(int nodeid, int incount, int time) {
            this.nodeid = nodeid;
            this.incount = incount;
            this.time = time;
        }

        @Override
        public String toString() {
            return "PQHelper{" +
                    "nodeid=" + nodeid +
                    ", incount=" + incount +
                    ", time=" + time +
                    '}';
        }
    }

    static class MyNode{
        int id;
        int visno = 0;
        int incount = 0;
        List<MyNode> next;

        public MyNode(int id) {
            this.id = id;
            this.next = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "MyNode{" +
                    "id=" + id +
                    ", visno=" + visno +
                    ", incount=" + incount +
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
                int noden = in.nextInt();
                int edgen = in.nextInt();

                MyNode[] nodes = new MyNode[noden];
                for (int i = 0; i < noden; i++) {
                    nodes[i] = new MyNode(i);
                }

                for (int i = 0; i < edgen; i++) {
                    int from = in.nextInt()-1, to = in.nextInt()-1;
                    nodes[from].next.add(nodes[to]);
                    nodes[to].incount++;
                }

                PriorityQueue<PQHelper> pq = new PriorityQueue<>(new Comparator<PQHelper>() {
                    @Override
                    public int compare(PQHelper o1, PQHelper o2) {
                        if(o1.incount!=o2.incount){
                            return o1.incount-o2.incount;
                        } else {
                            return o1.nodeid - o2.nodeid;
                        }
                    }
                });

                for(MyNode n:nodes){
                    pq.add(new PQHelper(n.id,n.incount,0));
                }

                LinkedList<MyNode> res = new LinkedList<>();

                boolean[] check = new boolean[noden];
                int[] rectime = new int[noden];
                int time=1;
                boolean possiblemark = true;

                while(!pq.isEmpty()){
                    PQHelper curhelper = pq.poll();
                    while(!pq.isEmpty()&&curhelper.time<rectime[curhelper.nodeid]){
                        curhelper = pq.poll();
                    }

                    MyNode curnode = nodes[curhelper.nodeid];
                    if(check[curnode.id]){
                        continue;
                    }
                    check[curnode.id] = true;
                    res.addLast(curnode);
                    for(MyNode itnode:curnode.next){
                        if(check[itnode.id]){
                            possiblemark = false;
                            break;
                        }
                        itnode.incount--;
                        pq.add(new PQHelper(itnode.id,itnode.incount,time));
                        rectime[itnode.id] = time;
                        time++;
                    }
                }

                if(!possiblemark){
                    out.println("impossible");
                    continue;
                } else {
                    for(MyNode n:res){
                        out.print((1+n.id)+" ");

                    }
                    out.println();
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
}

