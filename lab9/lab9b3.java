package lab9;

// tried prim again
// with out any optimization

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
