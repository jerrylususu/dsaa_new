package lab9;

// tried prim again
// with out any optimization

import java.util.*;

public class lab9b3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while(totalnum-->0) {
            // getting input
            int noden = in.nextInt();
            int edgen = in.nextInt();

            MyNode[] nodes = new MyNode[noden];
            for (int i = 0; i < noden; i++) {
                nodes[i] = new MyNode(i);
            }

            MyEdge[] edges = new MyEdge[edgen];


            int allmax = 0;
            int allmin = Integer.MAX_VALUE;
            for (int i = 0; i < edgen; i++) {
                int from = in.nextInt() - 1, to = in.nextInt() - 1, w = in.nextInt();
                MyEdge e = new MyEdge(from, to, w);
                nodes[from].edges.add(e);
                nodes[to].edges.add(e);
                edges[i] = e;
                allmax = Math.max(allmax, e.weight);
                allmin = Math.min(allmin, e.weight);
            }

            Arrays.sort(edges, new Comparator<MyEdge>() {
                @Override
                public int compare(MyEdge o1, MyEdge o2) {
                    return o1.weight-o2.weight;
                }
            });

            boolean[] vis = new boolean[noden];
            int[] dist = new int[noden];
            Arrays.fill(dist,-1);

            MyEdge startedge = edges[0];
            dist[startedge.from]=0;
            dist[startedge.to]=0;
            for (int i = 0; i < 2; i++) {
                MyNode curnode = null;
                if(i==0) curnode = nodes[startedge.from];
                else curnode = nodes[startedge.to];

                for(MyEdge e:curnode.edges){
                    MyNode itnode = nodes[e.sum-curnode.id];
                    if(dist[itnode.id]==-1||dist[itnode.id]>e.weight){
                        dist[itnode.id] = e.weight;
                    }
                }
            }




        }
    }


    static class MyEdge{
        int from;
        int to;
        int sum;
        int weight;
        boolean visited = false;

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
                    ", visited=" + visited +
                    '}';
        }
    }

    static class MyNode{
        int id;
        List<MyEdge> edges;
        boolean visited = false;
        int depth = Integer.MAX_VALUE;

        public MyNode(int id) {
            this.id = id;
            this.edges = new LinkedList<MyEdge>();
        }

        @Override
        public String toString() {
            return "MyNode{" +
                    "id=" + id +
                    ", visited=" + visited +
                    ", depth=" + depth +
                    '}';
        }
    }

}
