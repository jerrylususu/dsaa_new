package lab9;

// using Kruskal
// finally ac
// no idea
// Kruskal indeed must get the max(weight) min, since it adds edge from small to big
// but why prim not working?

// ps: great help from wikipedia example

import java.io.*;
import java.math.*;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab9b5fast {

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

    static class UnionSet<T>{
        UnionSetNode<T>[] arr;
        int size;

        public UnionSet(int size) {
            this.size = size;
            this.arr = new UnionSetNode[size];
        }

        public UnionSetNode<T> find(int i){
            UnionSetNode<T> node = arr[i];
            while(node.parent!=-1){
                node = arr[node.parent];
            }
            return node;
        }

        public LinkedList<UnionSetNode<T>> findLink(int i){
            LinkedList<UnionSetNode<T>> ll = new LinkedList<>();
            UnionSetNode<T> node = arr[i];
            ll.add(node);
            while(node.parent!=-1){
                node = arr[node.parent];
                ll.add(node);
            }
            return ll;
        }

        public void unionWithInfo(int i, int j, UnionSetNode<T> f1, UnionSetNode<T> f2){
            UnionSetNode<T> n1 = arr[i], n2 = arr[j];

            boolean f1bigger = f1.size>=f2.size;
            if(!f1bigger){
                UnionSetNode<T> tmp = f1;
                f1 = f2;
                f2 = tmp;
            }

            f2.parent = f1.id;
            f1.size += f2.size;
        }

        public void union(int i, int j){
            UnionSetNode<T> n1 = arr[i], n2 = arr[j];

            UnionSetNode<T> f1 = find(n1.id);
            UnionSetNode<T> f2 = find(n2.id);

            boolean f1bigger = f1.size>=f2.size;
            if(!f1bigger){
                UnionSetNode<T> tmp = f1;
                f1 = f2;
                f2 = tmp;
            }

            f2.parent = f1.id;
            f1.size += f2.size;
        }

        public void union2(int i, int j, List<UnionSetNode<T>> ll1, List<UnionSetNode<T>> ll2){
            UnionSetNode<T> n1 = arr[i], n2 = arr[j];

            boolean ll1bigger = ll1.size()>=ll2.size();
            UnionSetNode<T> f = (ll1bigger)?ll1.get(ll1.size()-1):ll2.get(ll2.size()-1);

            int fid = f.id;
            int ffid = f.parent;

            if(ll1bigger){
                for(UnionSetNode<T> node:ll1){
                    if(node.id!=fid&&node.parent!=fid&&node.parent!=-1){
                        arr[node.parent].size-=node.size;
                        node.parent=fid;
                        //f.size+=node.size;
                    }
                }
                for(UnionSetNode<T> node:ll2){
                    if(node.id!=fid){
                        UnionSetNode<T> updatenode = node;
                        int removesize = node.size;
//                        if(node.parent!=-1){
//                            arr[node.parent].size-=node.size;
//                        }
                        while(updatenode.parent!=-1){
                            arr[updatenode.parent].size-=removesize;
                            updatenode = arr[updatenode.parent];
                        }
                        node.parent=fid;
                        f.size+=node.size;
                    }
                }
            } else {
                for(UnionSetNode<T> node:ll1){
                    if(node.id!=fid){
                        UnionSetNode<T> updatenode = node;
                        int removesize = node.size;
//                        if(node.parent!=-1){
//                            arr[node.parent].size-=node.size;
//                        }
                        while(updatenode.parent!=-1){
                            arr[updatenode.parent].size-=removesize;
                            updatenode = arr[updatenode.parent];
                        }
                        node.parent=fid;
                        f.size+=node.size;
                    }
                }
                for(UnionSetNode<T> node:ll2){
                    if(node.id!=fid&&node.parent!=fid&&node.parent!=-1){
                        arr[node.parent].size-=node.size;
                        node.parent=fid;
                        //f.size+=node.size;
                    }
                }

            }

            f.parent=ffid;
//            f.size+=(ll1bigger)?ll2.size():ll1.size();
        }

    }

    static class UnionSetNode<T>{
        int id;
        int parent = -1;
        int size = 1;
        T thing;

        public UnionSetNode(int id, T thing) {
            this.id = id;
            this.thing = thing;
        }

        @Override
        public String toString() {
            return "UnionSetNode{" +
                    "id=" + id +
                    ", parent=" + parent +
                    ", size=" + size +
                    '}';
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

                Arrays.sort(edges, new Comparator<MyEdge>() {
                    @Override
                    public int compare(MyEdge o1, MyEdge o2) {
                        return Long.compare(o1.weight,o2.weight);
                    }
                });

                UnionSet<MyNode> us = new UnionSet<>(n);
                for (int i = 0; i < n; i++) {
                    us.arr[i] = new UnionSetNode<>(i,nodes[i]);
                }

                long sum = 0;
                for (int i = 0; i < m; i++) {
                    MyEdge curedge = edges[i];
                    int from = curedge.from, to = curedge.to;
                    LinkedList<UnionSetNode<MyNode>> ll1 = us.findLink(from), ll2 = us.findLink(to);
                    if(ll1.getLast()!=ll2.getLast()){
                        sum+=curedge.weight;
                        us.union2(from,to,ll1,ll2);
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

