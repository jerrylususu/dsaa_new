package lab9;

// brute force with fast read and write
// still tle

import java.io.*;
import java.math.*;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab9jfast {


    static class MyEdge{
        int from, to, sum;
        boolean toIsWolf;

        public MyEdge(int from, int to, boolean toIsWolf) {
            this.from = from;
            this.to = to;
            this.sum = from+to;
            this.toIsWolf = toIsWolf;
        }
    }

    static class MyNode{
        int id;
        boolean isWolf = false;
        boolean marker = false;
        MyEdge out = null;
        List<MyEdge> in;

        public MyNode(int id) {
            this.id = id;
            this.in = new LinkedList<>();
        }

        @Override
        public String toString() {
            return "MyNode{" +
                    "id=" + id +
                    ", isWolf=" + isWolf +
                    ", marker=" + marker +
                    ", out=" + out +
                    ", in=" + in.size() +
                    '}';
        }
    }
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

        public void solve(InputReader in, PrintWriter out) throws IOException {
            int totalnum = in.nextInt();

            while(totalnum-->0){
                int n = in.nextInt();
                MyNode[] nodes = new MyNode[n];
                for (int i = 0; i < n; i++) {
                    nodes[i] = new MyNode(i);
                }

                for (int i = 0; i < n; i++) {
                    int to = in.nextInt()-1;
                    boolean toIsWolf = in.next().charAt(0)=='w';
                    MyEdge e = new MyEdge(i,to,toIsWolf);
                    nodes[i].out = e;
                    nodes[to].in.add(e);
                }

                // try to brute force traversal
                int count = 0;
                for (int i = 0; i < n; i++) {
                    if(nodes[i].out.toIsWolf){
                        continue;
                    }

                    int start = i;
                    MyEdge curedge = nodes[i].out;
                    // move around villager edge
                    while(!curedge.toIsWolf){
                        curedge = nodes[curedge.to].out;
                    }
                    // we found a wolf
                    if(curedge.to==start){
                        if(!nodes[curedge.to].marker){ // haven't visited before
                            count++;
                            MyNode wolfnode = nodes[curedge.to];
                            wolfnode.isWolf = true;
                            wolfnode.marker = true; // now visited

                            // do backward cleaning
                            LinkedList<MyNode> q = new LinkedList<>();
                            q.addLast(wolfnode);
                            while(!q.isEmpty()){
                                MyNode curnode = q.pollFirst();
                                for(MyEdge e:curnode.in){
                                    if(!e.toIsWolf){
                                        q.addLast(nodes[e.from]);
                                    }
                                }
                                if(!curnode.marker){
                                    count++;
                                    curnode.isWolf=true;
                                    curnode.marker=true;
                                }
                            }
                        }
                    }
                }
                out.println(count);
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

