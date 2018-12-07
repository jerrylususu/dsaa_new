//package lab8;
//
//import java.io.DataInputStream;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.*;
//
//// use a fast read
//// wa
//
//// change data type to long
//// ac
//
//
//public class lab8f3fast {
//    public static void main(String[] args) throws IOException{
//        Reader in = new Reader();
//        int totalnum = in.nextInt();
//
//        while (totalnum--> 0) {
//            int noden = in.nextInt();
//            int edgen = in.nextInt();
//
//            MyGraphNode[] nodearr = new MyGraphNode[noden];
//
//            for (int i = 0; i < noden; i++) {
//                nodearr[i] = new MyGraphNode(i );
//            }
//
//            for (int i = 0; i < edgen; i++) {
//                MyGraphNode from = nodearr[in.nextInt() - 1];
//                MyGraphNode to = nodearr[in.nextInt() - 1];
//                int val = in.nextInt();
//                MyGraphEdge e = new MyGraphEdge(from, to, val);
//                from.out.add(e);
//                to.incount++;
//            }
//
////            MyGraphNode start = null;
////
////            for (MyGraphNode n : nodearr) {
////                if (n.in.isEmpty()) {
////                    start = n;
////                    break;
////                }
////            }
//
//            List<MyGraphNode> stack = topoSort(nodearr);
////            System.out.println(stack);
//
//            long[] max = new long[]{-1};
//            calc(stack,max);
//            System.out.println(max[0]);
////            System.out.println(Arrays.toString(order));
//
//
////            System.out.println(end.tmpdepth);
//
//
//        }
//
//    }
//
//    public static int weight(MyGraphNode s1, MyGraphNode s2){
//        for(MyGraphEdge e:s1.out) {
//            if (e.to == s2) {
//                return e.val;
//            }
//        }
//        return -1;
//    }
//
//    public static long[] calc(List<MyGraphNode> s,long[] max){
//        int n = s.size();
//        long[] dist = new long[n];
//        Arrays.fill(dist,-1);
//        int index = 0;
//        MyGraphNode[] nodes = new MyGraphNode[n];
//        for(MyGraphNode node:s){
//            nodes[index++] = node;
//            if(node.incount==0){
//                dist[index-1]=0;
//            }
//        }
////        System.out.println(Arrays.toString(nodes));
//        for (int i = 0; i < n; i++) {
//            for(MyGraphEdge e:nodes[i].out){
//                if(dist[e.to.id]<dist[nodes[i].id]+e.val){
//                    dist[e.to.id]=dist[nodes[i].id]+e.val;
//                }
//                if(dist[e.to.id]>max[0]){
//                    max[0] = dist[e.to.id];
//                }
//            }
//        }
////        System.out.println(Arrays.toString(dist));
//        return dist;
//    }
//
//    public static List<MyGraphNode> topoSort(MyGraphNode[] arr){
//        LinkedList<MyGraphNode> s = new LinkedList<>();
//        for(MyGraphNode n:arr){
//            if(n.incount==0){
//                s.add(n);
//            }
//        }
//        List<MyGraphNode> sorted = new LinkedList<>();
//        while(!s.isEmpty()){
//            MyGraphNode cur = s.pollFirst();
//            sorted.add(cur);
//            for(MyGraphEdge e:cur.out){
//                e.to.incount--;
//                if(e.to.incount==0){
//                    s.addLast(e.to);
//                }
//            }
//        }
//        return sorted;
//    }
//
//    public static void reset(MyGraphNode[] arr){
//        for(MyGraphNode n:arr){
//            n.tmpdepth = -1;
//            n.visited = false;
//        }
//    }
//
//    static class Reader
//    {
//        final private int BUFFER_SIZE = 1 << 16;
//        private DataInputStream din;
//        private byte[] buffer;
//        private int bufferPointer, bytesRead;
//
//        public Reader()
//        {
//            din = new DataInputStream(System.in);
//            buffer = new byte[BUFFER_SIZE];
//            bufferPointer = bytesRead = 0;
//        }
//
//        public Reader(String file_name) throws IOException
//        {
//            din = new DataInputStream(new FileInputStream(file_name));
//            buffer = new byte[BUFFER_SIZE];
//            bufferPointer = bytesRead = 0;
//        }
//
//        public String readLine() throws IOException
//        {
//            byte[] buf = new byte[64]; // line length
//            int cnt = 0, c;
//            while ((c = read()) != -1)
//            {
//                if (c == '\n')
//                    break;
//                buf[cnt++] = (byte) c;
//            }
//            return new String(buf, 0, cnt);
//        }
//
//        public int nextInt() throws IOException
//        {
//            int ret = 0;
//            byte c = read();
//            while (c <= ' ')
//                c = read();
//            boolean neg = (c == '-');
//            if (neg)
//                c = read();
//            do
//            {
//                ret = ret * 10 + c - '0';
//            }  while ((c = read()) >= '0' && c <= '9');
//
//            if (neg)
//                return -ret;
//            return ret;
//        }
//
//        public long nextLong() throws IOException
//        {
//            long ret = 0;
//            byte c = read();
//            while (c <= ' ')
//                c = read();
//            boolean neg = (c == '-');
//            if (neg)
//                c = read();
//            do {
//                ret = ret * 10 + c - '0';
//            }
//            while ((c = read()) >= '0' && c <= '9');
//            if (neg)
//                return -ret;
//            return ret;
//        }
//
//        public double nextDouble() throws IOException
//        {
//            double ret = 0, div = 1;
//            byte c = read();
//            while (c <= ' ')
//                c = read();
//            boolean neg = (c == '-');
//            if (neg)
//                c = read();
//
//            do {
//                ret = ret * 10 + c - '0';
//            }
//            while ((c = read()) >= '0' && c <= '9');
//
//            if (c == '.')
//            {
//                while ((c = read()) >= '0' && c <= '9')
//                {
//                    ret += (c - '0') / (div *= 10);
//                }
//            }
//
//            if (neg)
//                return -ret;
//            return ret;
//        }
//
//        private void fillBuffer() throws IOException
//        {
//            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
//            if (bytesRead == -1)
//                buffer[0] = -1;
//        }
//
//        private byte read() throws IOException
//        {
//            if (bufferPointer == bytesRead)
//                fillBuffer();
//            return buffer[bufferPointer++];
//        }
//
//        public void close() throws IOException
//        {
//            if (din == null)
//                return;
//            din.close();
//        }
//    }
//
//}
//
//
//
//class MyGraphNode{
//    int id;
////    List<MyGraphEdge> in;
//    int incount;
//    List<MyGraphEdge> out;
//    boolean visited = false;
//    int tmpdepth = -1;
//
//    public MyGraphNode(int id){
//        this.id = id;
////        this.in = new ArrayList<>();
//        incount = 0;
//        this.out = new ArrayList<>();
//    }
//
//    @Override
//    public String toString() {
//        return "MyGraphNode{" +
//                "id=" + id +
//                ", in=" + incount +
//                ", out=" + out.size() +
//                ", visited=" + visited +
//                ", tmpdepth=" + tmpdepth +
//                '}';
//    }
//}
//
//class MyGraphEdge{
//    int val;
//    MyGraphNode from;
//    MyGraphNode to;
//
//    public MyGraphEdge(MyGraphNode from, MyGraphNode to, int val) {
//        this.val = val;
//        this.from = from;
//        this.to = to;
//    }
//
//    @Override
//    public String toString() {
//        return "MyGraphEdge{" +
//                "val=" + val +
//                ", from=" + from.id +
//                ", to=" + to.id +
//                '}';
//    }
//}