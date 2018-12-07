package lab8;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

// tle, even with fast read and write
// perhaps need to modify the algorithm

// compared to AC programs, and the result is not correct -> WA
// with help from shenjingran, fixed the problem on union set combine

// still tle, even build graph took 2100ms
// change to new fast read, build took 400ms, before union set 1324
// after union set still tle
// perhaps need to try at least some route compression

// implemented route compression, now getting WA
// tried multiple input file, the results are all correct, but still WA

// tried to mess around with fast input, still WA
// there is perhaps some strange flaw deep in algorithm

// rewrote the findCircle() method, using a new method, still WA

// tried to rearrange the input order, found an inconsistency!
// debug and found the bug: union set operation!
// when doing route compression, only update 2 level, instead of all upward levels!

// fixed the bug, getting MLE
// caused by the 50% upscale of ArrayList
// changed to LinkedList, finally AC

// tried my old findCircle(), and it works, just a little slower, but still AC

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab8i2fast{
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
        public void solve(InputReader in2, PrintWriter out) throws IOException{
            Reader in = new Reader();
            int totalnum = in.nextInt();

            while(totalnum-->0){
//                System.out.println(totalnum);
                int noden = in.nextInt();
                int edgen = in.nextInt();

                MyNode[] nodes = new MyNode[noden];
                for (int i = 0; i < noden; i++) {
                    nodes[i] = new MyNode(i);
                }

                MyEdge[] edges = new MyEdge[edgen];
                for (int i = 0; i < edgen; i++) {
                    int from=in.nextInt()-1, to=in.nextInt()-1, w=in.nextInt();
                    MyEdge e = new MyEdge(i,from,to,w);
                    nodes[from].edges.add(e);
                    nodes[to].edges.add(e);
                    edges[i] = e;
                }

                // check circles
                List<List<MyEdge>> cirs = findCircles2(nodes,edges);

                // remove min edges
                for (List<MyEdge> li:cirs){
                    long minweight = li.get(0).weight;
                    int minid = 0;
                    int size = li.size();
                    for (int i = 1; i < size; i++) {
                        if(li.get(i).weight<minweight){
                            minid = i;
                            minweight = li.get(i).weight;
                        }
                    }
                    MyEdge minedge = li.get(minid);
//                nodes[minedge.from].edges.remove(minedge);
//                nodes[minedge.to].edges.remove(minedge);
//                li.remove(minid);
                    minedge.removed = true;
                    for(MyEdge edge:li){
                        if(!edge.removed)
                            edge.weight+=minweight;
                    }
                }

//                System.out.println("check");

                // do the tree change
                Arrays.sort(edges, new Comparator<MyEdge>() {
                    @Override
                    public int compare(MyEdge o1, MyEdge o2) {
                        return (int)(o2.weight-o1.weight);
                    }
                });
                UnionSet<MyNode> us = new UnionSet<>(noden);
                for (int i = 0; i < noden; i++) {
                    us.arr[i] = new UnionSetNode<>(i,nodes[i]);
                }
                long ans = 0;
                for (int i = 0; i < edgen; i++) {
                    MyEdge curedge = edges[i];
                    if(curedge.removed){
                        continue;
                    }
                    int from = curedge.from;
                    int to = curedge.to;
                    List<UnionSetNode<MyNode>> ll1 = us.findLink(from);
                    List<UnionSetNode<MyNode>> ll2 = us.findLink(to);
                    UnionSetNode<MyNode> f1 = ll1.get(ll1.size()-1);
                    UnionSetNode<MyNode> f2 = ll2.get(ll2.size()-1);
                    long t1 = f1.size;
                    long t2 = t1 * f2.size;
                    ans+=t2*curedge.weight;
//                    us.unionWithInfo(from,to,f1,f2);
                    us.union2(from,to,ll1,ll2);
//                    System.out.println(ans);
                }
                out.println(ans);

            }
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

        public List<UnionSetNode<T>> findLink(int i){
            List<UnionSetNode<T>> ll = new ArrayList<>();
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

    public static List<List<MyEdge>> findCircles(MyNode[] nodes, MyEdge[] edges){
        List<List<MyEdge>> cirs= new ArrayList<>();
        boolean[] instack = new boolean[nodes.length];

        Stack<MyEdge> sedge = new Stack<>();
        Stack<MyNode> snode = new Stack<>();

        int latercnt = 0;

        snode.push(nodes[7]);
        while(!snode.isEmpty()){
            boolean needskip = false;
            MyNode curnode = snode.peek();
            while (curnode.visno>=curnode.edges.size()){
                snode.pop();
                if(!sedge.isEmpty())
                    sedge.pop();
                if(snode.isEmpty()){
                    needskip = true;
                    break;
                }
                curnode = snode.peek();
            }


            if(needskip){
                continue;
            }
            while (snode.peek().visno>=snode.peek().edges.size()){
                MyNode n = snode.pop();
                if(!sedge.isEmpty())
                    sedge.pop();
                instack[n.id] = false;
                if(snode.isEmpty()){
                    needskip = true;
                    break;
                }
            }
            if(needskip){
                continue;
            }
            MyEdge curedge = curnode.edges.get(curnode.visno++);

            while (curedge.visited){
                if(curnode.visno>=curnode.edges.size()){
                    snode.pop();
                    if(!sedge.isEmpty())
                        sedge.pop();

                    needskip = true;
                    break;
                }
                curedge = curnode.edges.get(curnode.visno++);
            }

            if(needskip){
                continue;
            }

            curedge.visited = true;
            snode.push(nodes[curedge.sum-curnode.id]);
            sedge.push(curedge);
            instack[curnode.id] = true;

            if(instack[curedge.sum-curnode.id]){
                // has met
                int backend = curedge.sum-curnode.id;
                List<MyEdge> li = new ArrayList<>();
                Stack<MyEdge> lateredge = new Stack<>();
                Stack<MyNode> laternode = new Stack<>();
                boolean later = false;
//                System.out.println(snode);
                snode.pop();
                while(snode.peek().id!=backend){
                    MyEdge popedge = sedge.pop();
                    li.add(popedge);
                    if(later){
                        lateredge.push(popedge);
                    }
                    MyNode popnode = snode.pop();
//                    popnode.visno++;
                    while(popnode.visno<popnode.edges.size()&&popnode.edges.get(popnode.visno).visited){
                        popnode.visno++;
                    }
                    if(popnode.visno<popnode.edges.size()){
                        later = true;
                        latercnt++;
                    }
                    if(later){
                        laternode.push(popnode);
                    }
                    instack[popnode.id]=false;
                }
                MyEdge popedge = sedge.pop();
                li.add(popedge);
                if(later){
                    lateredge.push(popedge);
                }

                cirs.add(li);


                if(later){
                    // do rebuild
                    while(!lateredge.empty()){
                        sedge.push(lateredge.pop());
                    }
                    while(!laternode.empty()){
                        snode.push(laternode.pop());
                    }
                }
            }
        }


       for(List<MyEdge> li:cirs){
           System.out.println(li);
       }

        return cirs;

    }

    public static List<List<MyEdge>> findCircles2(MyNode[] nodes, MyEdge[] edges){
        boolean[] instack = new boolean[nodes.length];
        LinkedList<MyNode> snode = new LinkedList<>();
        LinkedList<MyEdge> sedge = new LinkedList<>();
        List<List<MyEdge>> cirs = new LinkedList<>();

        snode.push(nodes[0]);
        instack[nodes[0].id] = true;
        int startid = nodes[0].id;

        while(!snode.isEmpty()){
            boolean skipthis = false;
            // get next node
            MyNode curnode = snode.getLast();
            while(curnode.visno>=curnode.edges.size()){

                MyNode n = snode.removeLast();
                if(startid==n.id){
                    break;
                } else{
                    sedge.removeLast();
                }
                instack[n.id] = false;
                if(snode.isEmpty()){
                    skipthis=true;
                    break;
                }
                curnode = snode.getLast();
            }

            if(skipthis){
                continue;
            }

            // get next edge
            if(curnode.visno>=curnode.edges.size()){
                continue;
            }
            MyEdge curedge = curnode.edges.get(curnode.visno++);
            while(curedge.visited){
                if(curnode.visno>=curnode.edges.size()){
                    // this node can be thrown
                    skipthis=true;
                    break;
                }
                curedge = curnode.edges.get(curnode.visno++);
            }

            if(skipthis){
                continue;
            }

            // do inqueue
            sedge.addLast(curedge);
            curedge.visited=true;
            snode.addLast(nodes[curedge.sum-curnode.id]);
            instack[curnode.id]=true;

            // check if we have circle
            if(instack[snode.getLast().id]){
                // met a circle
                MyNode endnode = snode.removeLast();
                int endmark = endnode.id;

                List<MyEdge> li = new ArrayList<>();
                ListIterator<MyEdge> reedgeitor = sedge.listIterator(sedge.size());
                ListIterator<MyNode> renodeitor = snode.listIterator(snode.size());

                MyNode itnode = renodeitor.previous();
                while(itnode.id!=endnode.id){
                    li.add(reedgeitor.previous());
                    itnode = renodeitor.previous();
                }
                li.add(reedgeitor.previous());

                cirs.add(li);

                // do stack reverse
                MyNode curtopnode = snode.getLast();
                while(curtopnode.visno<curtopnode.edges.size()) {
                    if(curtopnode.edges.get(curtopnode.visno).visited){
                        curtopnode.visno++;
                    } else {
                        break;
                    }
                }

                while(snode.getLast().visno>=snode.getLast().edges.size()){
                    MyNode popnode = snode.removeLast();
                    instack[popnode.id]=false;
                    MyEdge popedge = sedge.removeLast();
                }
                sedge.removeLast();
                }
            }

//        for(List<MyEdge> li:cirs){
//            System.out.println(li);
//        }

        return cirs;
    }

    static class MyEdge{
        int from;
        int to;
        int sum;
        int id;
        long weight;
        boolean removed = false;
        boolean visited = false;

        @Override
        public String toString() {
            return String.format("(%d) %d <-- %d %s --> %d",id,from,weight,removed?"/":"-",to);
        }

        public MyEdge(int id, int from, int to, int weight) {
            this.from = from;
            this.id = id;
            this.to = to;
            this.sum = from+to;
            this.weight = weight;
        }
    }

    static class MyNode{
        int id;
        int visno = 0;
        int listno = 0;
        List<MyEdge> edges;

        public MyNode(int id) {
            this.id = id;
            this.edges = new ArrayList<>();
        }

        @Override
        public String toString() {
            return
                    "id=" + id +
                    ", visno=" + visno +
                    ", edges=" + edges.size()
                    ;
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

}
