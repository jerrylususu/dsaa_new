package lab8;

// build map using 5104, with total time limit 6000
// need to make some big change

// remove myedge, since every edge is weight 1
// add super source and super sink for connecting
// AC

import java.io.*;
import java.math.*;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab8h4fast {

    public static void main(String[] args){
        InputStream inputStream = System.in;//new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }
    static class Task {
        public void solve(InputReader in, PrintWriter out) {
            int totalnum = in.nextInt();

            while(totalnum-->0){
                int n = in.nextInt(),m=in.nextInt(),k=in.nextInt();
                MyNode[] nodearr = new MyNode[n+1];
                for (int i = 1; i < n+1; i++) {
                    nodearr[i] = new MyNode(i);
                }
                for (int i = 0; i < m; i++) {
                    int n1=in.nextInt(), n2=in.nextInt();
                    nodearr[n1].edges.add(nodearr[n2]);
                    nodearr[n2].edges.add(nodearr[n1]);
                }
                int[] set = new int[k];
                for (int i = 0; i < k; i++) {
                    set[i] = in.nextInt();
                }

                // len for binary max length
                int len=0, tmp=1;
                while(tmp<k){
                    tmp*=2;
                    len++;
                }

//            System.out.println(len);

                int min = Integer.MAX_VALUE;

                // do binary separation
                for (int i = 0; i < len; i++) {
                    // add the new source
                    MyNode src = new MyNode(0);
                    nodearr[0] = src;

                    // separate source and sink
                    LinkedList<Integer> ll = new LinkedList<>(); // cache for faster reset
//                LinkedList<Integer> ll2 = new LinkedList<>();
//                LinkedList<Integer> ll3 = new LinkedList<>();
                    for (int j = 0; j < k; j++) {
                        if(isIthbit1(j,i)){
                            // source
//                        ll2.addLast(set[j]);
                            src.edges.add(nodearr[set[j]]);
                        } else {
                            // sink
//                        ll3.addLast(set[j]);
                            nodearr[set[j]].tmpSinkEdge = true;
                            ll.addLast(set[j]);
                        }
                    }
//                System.out.println(ll2);
//                System.out.println(ll3);

                    // do calculation
                    int[] dis = dijkstraFast(nodearr[0],nodearr);
//                System.out.println(Arrays.toString(dis));

                    min = Math.min(min,dis[dis.length-1]);

                    // do reset
                    nodearr[0] = null;
                    for(int j:ll){
                        nodearr[j].tmpSinkEdge = false;
                    }
                }

                out.println(min);

//
//            int[] dis = dijkstraFast(nodearr[0],nodearr);
//
//            System.out.println(Arrays.toString(dis));


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
    // bit-wise operation
    // ref: https://www.cnblogs.com/hapjin/p/5839797.html
    public static boolean isIthbit1(int num, int i){
        return  ((num & (1<<i))!=0);
    }

    public static int[] dijkstraFast(MyNode s, MyNode[] arr){
        int n = arr.length;
        int[] dis = new int[arr.length+1];
        int[] time = new int[arr.length];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[s.id]=0;
        PriorityQueue<MyTempStorage> pq = new PriorityQueue<>(new Comparator<MyTempStorage>() {
            @Override
            public int compare(MyTempStorage o1, MyTempStorage o2) {
                return o1.dist-o2.dist;
            }
        });

        int curtime = 1;

        for(MyNode node:s.edges){
            dis[node.id] = 0;
            time[node.id] = curtime;
            pq.add(new MyTempStorage(1,node.id,curtime));
        }

        while(!pq.isEmpty()){
            curtime++;

            // make sure the new next
            MyTempStorage maybeNext = pq.poll();
            while(maybeNext.time<time[maybeNext.nodeid]){
                maybeNext = pq.poll();
            }
            MyTempStorage next = maybeNext;
            MyNode cur = arr[next.nodeid];
            for(MyNode node:cur.edges){
                if(1 + dis[cur.id] < dis[node.id]){
                    dis[node.id] = 1 + dis[cur.id];
                    pq.add(new MyTempStorage(dis[node.id],node.id,curtime));
                }
                if(node.tmpSinkEdge){
                    if(dis[node.id]<dis[n]){
                        dis[n] = dis[node.id];
                    }
                }
            }

        }

        return dis;
    }


    static class MyTempStorage{
        int dist;
        int nodeid;
        int time;

        public MyTempStorage(int dist, int nodeid, int time) {
            this.dist = dist;
            this.nodeid = nodeid;
            this.time = time;
        }
    }

//    static class MyEdge{
//        MyNode another;
//        int weight;
//
//
//        public MyEdge(MyNode another, int weight) {
//            this.another = another;
//            this.weight = weight;
//        }
//
//        @Override
//        public String toString() {
//            return "MyEdge{" +
//                    "another=" + another +
//                    ", weight=" + weight +
//                    '}';
//        }
//    }

    static class MyNode{
        int id;
        List<MyNode> edges;
        //        int tmpdist = Integer.MAX_VALUE;
        boolean tmpSinkEdge = false;

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
}
