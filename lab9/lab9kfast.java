package lab9;

// the idea
// first do tarjan
// find the 1st and 2nd largest scc (by nnumber of nodes contained)
// do the calculation

// one time ac
// proves that my tarjan implementation is correct

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class lab9kfast {
    static class Task {

        public void solve(InputReader in2, PrintWriter out) throws IOException {
            Reader in = new Reader();
            int totalnum = in.nextInt();

            while(totalnum-->0){

                // get input
                int n = in.nextInt();
                MyNode[] nodes = new MyNode[n];
                for (int i = 0; i < n; i++) {
                    nodes[i] = new MyNode(i);
                }
                MyEdge[] edges = new MyEdge[n];
                for (int i = 0; i < n; i++) {
                    MyEdge e = new MyEdge(in.nextInt()-1,i,1);
                    nodes[e.from].out.add(e);
                    nodes[e.to].in.add(e);
                }

                // run tarjan
                List<List<MyNode>> sccs = runTarjan(n, nodes);

                // do calculation
                Collections.sort(sccs,(o1,o2)->o2.size()-o1.size());

                // calc result
                long sum = 0;
                for(List<MyNode> li:sccs){
                    sum+=li.size()*li.size();
                }

                if(sccs.size()>=2){
                    int size0 = sccs.get(0).size(), size1= sccs.get(1).size();

                    sum-=size0*size0;
                    sum-=size1*size1;

                    sum+=(size0+size1)*(size0+size1);

                }
                out.println(sum);

            }
        }

    }

    public static List<List<MyNode>> runTarjan(int noden, MyNode[] nodes){
        List<List<MyNode>> sccs = new LinkedList<>();
        int[] low = new int[noden];
        int[] dfs = new int[noden];
        boolean[] vis = new boolean[noden];
        Arrays.fill(dfs,-1);
        int[] idx = {0};
        boolean[] instack = new boolean[noden];

        boolean allchecked=true;
        Stack<MyNode> s= new Stack<>();
        do{
            int startid = -1;
            for (int i = 0; i < noden; i++) {
                if(!vis[i]){
                    startid = i;
                    allchecked = false;
                    break;
                }
            }

            if(startid==-1){
                allchecked = true;
                break;
            }

            runTarjanSingleStart(startid,noden,nodes,low,dfs,idx,s,vis,instack,sccs);

        }while (!allchecked);

        return sccs;

    }

    public static void runTarjanSingleStart(int sid, int noden, MyNode[] nodes, int[] low, int [] dfs,
                                            int[] idx,Stack<MyNode> s,boolean[] vis, boolean[] instack,
                                            List<List<MyNode>> sccs){
        idx[0]++;
        vis[sid]=true;
        dfs[sid]=idx[0]; low[sid]=idx[0];
        MyNode curnode = nodes[sid];
        s.push(curnode);
        instack[sid]=true;
        for(MyEdge e:curnode.out){
            if(!vis[e.to]){
                // haven't visited
                runTarjanSingleStart(e.to,noden,nodes,low,dfs,idx,s,vis,instack,sccs);
                low[sid] = Math.min(low[sid],low[e.to]);
            } else if(instack[e.to]){
                low[sid] = Math.min(low[sid],dfs[e.to]);
            }
        }
        if(dfs[sid]==low[sid]){
            List<MyNode> li = new LinkedList<>();
            while(s.peek().id!=sid){
                MyNode n = s.pop();
                instack[n.id]=false;
//                System.out.print(n.id);
                li.add(n);
            }
            MyNode n = s.pop();
            instack[n.id]=false;
            li.add(n);
//            System.out.print(n.id);
//            System.out.println();
            sccs.add(li);
        }
    }

    static class MyEdge{
        int from,to,sum,weight;
        boolean vis = false;

        public MyEdge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.sum = from+to;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MyEdge myEdge = (MyEdge) o;

            if (from != myEdge.from) return false;
            if (to != myEdge.to) return false;
            if (sum != myEdge.sum) return false;
            return weight == myEdge.weight;
        }

        @Override
        public int hashCode() {
            int result = from;
            result = 31 * result + to;
            result = 31 * result + sum;
            result = 31 * result + weight;
            return result;
        }
    }
    static class MyNode{
        int id;
        List<MyEdge> in,out;
        int inn=0, outn=0;
        boolean enable = true;

        public MyNode(int id) {
            this.id = id;
            this.in = new ArrayList<>();
            this.out = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "MyNode{" +
                    "id=" + id +
                    ", in=" + in.size() +
                    ", out=" + out.size() +
                    '}';
        }
    }


    // With credit to SA Zhu
    static class Reader {
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
