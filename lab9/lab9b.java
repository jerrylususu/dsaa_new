package lab9;

import java.util.*;

// use prim
// optimized with union set
// wa

// tried binary
// still wa


public class lab9b {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while(totalnum-->0){
            // getting input
            int noden = in.nextInt();
            int edgen = in.nextInt();

            MyNode[] nodes =new MyNode[noden];
            for (int i = 0; i < noden; i++) {
                nodes[i] = new MyNode(i);
            }

            MyEdge[] edges = new MyEdge[edgen];


            int allmax = 0;
            int allmin = Integer.MAX_VALUE;
            for (int i = 0; i < edgen; i++) {
                int from = in.nextInt()-1, to = in.nextInt()-1, w = in.nextInt();
                MyEdge e = new MyEdge(from,to,w);
                nodes[from].edges.add(e);
                nodes[to].edges.add(e);
                edges[i] = e;
                allmax = Math.max(allmax,e.weight);
                allmin = Math.min(allmin,e.weight);
            }

            // run prim algorithm
            // with binary division

            long[] res = calc(nodes,edges,noden,edgen);
            System.out.println(res[0]);

//            int l = allmin, r = allmax;
//            int mid = (allmin+allmax)/2;
//            long[] res = null;
//            while(l<=r){
//                mid = (l+r)/2;
//                res = calcWithLimit(mid,nodes,edges,noden,edgen);
//                if(res[1]==-1){
//                    // not ok
//                    l = mid+1;
//                } else {
//                    // ok
//                    r = mid-1;
//                }
//
//            }
//            res = calcWithLimit(mid,nodes,edges,noden,edgen);
//            while(res[1]==-1){
//                mid++;
//                res = calcWithLimit(mid,nodes,edges,noden,edgen);
//            }
//            System.out.println(res[0]);
        }
    }

    public static long[] calcWithLimit(int max, MyNode[] nodes, MyEdge[] edges, int noden, int edgen){
        reset(nodes,edges);
        int curmax = 0;
        PriorityQueue<PQHelper> pq = new PriorityQueue<>(new Comparator<PQHelper>() {
            @Override
            public int compare(PQHelper o1, PQHelper o2) {
                return o1.depth-o2.depth;
            }
        });
        int time = 0;
        long sum = 0;
        int[] recordtime = new int[noden];
        MyNode startnode = nodes[0];
        startnode.visited = true;
        recordtime[startnode.id] = time++;
        for(MyEdge e:startnode.edges){
            MyNode itnode = nodes[e.sum-startnode.id];
            if(e.weight>max){
                continue;
            }
            itnode.depth = e.weight;
            pq.add(new PQHelper(itnode.id,itnode.depth,time++));
            recordtime[itnode.id]=time-1;
        }

        int nodecount = 1;

        while(!pq.isEmpty()){
            PQHelper pqHelper = pq.poll();
            while(!pq.isEmpty()&&pqHelper.time<recordtime[pqHelper.nodeid]){
                pqHelper = pq.poll();
            }
            MyNode curnode = nodes[pqHelper.nodeid];
            if(curnode.visited){
                continue;
            }
            curnode.visited = true;
            nodecount++;
//            System.out.println(sum+" "+curnode);
            sum += curnode.depth;
            if(curnode.depth>curmax){
                curmax = curnode.depth;
            }
            for(MyEdge e:curnode.edges){
                MyNode itnode = nodes[e.sum-curnode.id];
                if(!itnode.visited){
                    if(e.weight>max){
                        continue;
                    }
                    if(e.weight<itnode.depth){
                        itnode.depth = e.weight;
                        recordtime[itnode.id] = time++;
                        pq.add(new PQHelper(itnode.id,itnode.depth,time-1));
                    }
                }
            }
        }

        if(nodecount<noden){
            return new long[]{sum,-1};
        }

//        System.out.println(sum);
        return new long[]{sum,curmax};
    }

    public static long[] calc(MyNode[] nodes, MyEdge[] edges, int noden, int edgen){
        reset(nodes, edges);
        int max = 0;
        PriorityQueue<PQHelper> pq = new PriorityQueue<>(new Comparator<PQHelper>() {
            @Override
            public int compare(PQHelper o1, PQHelper o2) {
                return o1.depth-o2.depth;
            }
        });
        int time = 0;
        long sum = 0;
        int[] recordtime = new int[noden];
        MyNode startnode = nodes[0];
        Arrays.sort(edges, new Comparator<MyEdge>() {
            @Override
            public int compare(MyEdge o1, MyEdge o2) {
                return o1.weight-o2.weight;
            }
        });
        for (int i = 0; i < 2; i++) {
            if(i==0){
                startnode = nodes[edges[0].from];
            } else if (i==1){
                startnode = nodes[edges[0].to];
            }
            startnode.visited = true;
            recordtime[startnode.id] = time++;
            for(MyEdge e:startnode.edges){
                MyNode itnode = nodes[e.sum-startnode.id];
                itnode.depth = e.weight;
                pq.add(new PQHelper(itnode.id,itnode.depth,time++));
                recordtime[itnode.id]=time-1;
            }
        }
        sum=edges[0].weight;


        while(!pq.isEmpty()){
            PQHelper pqHelper = pq.poll();
            while(!pq.isEmpty()&&pqHelper.time<recordtime[pqHelper.nodeid]){
                pqHelper = pq.poll();
            }
            MyNode curnode = nodes[pqHelper.nodeid];
            if(curnode.visited){
                continue;
            }
            curnode.visited = true;
            System.out.println(sum+" "+curnode);
            sum += curnode.depth;
            if(curnode.depth>max){
                max = curnode.depth;
            }
            for(MyEdge e:curnode.edges){
                MyNode itnode = nodes[e.sum-curnode.id];
                if(!itnode.visited){
                    if(e.weight<itnode.depth){
                        itnode.depth = e.weight;
                        recordtime[itnode.id] = time++;
                        pq.add(new PQHelper(itnode.id,itnode.depth,time-1));
                    }
                }
            }
        }

//        System.out.println(sum);
        return new long[]{sum,max};
    }

    public static void reset(MyNode[] nodes, MyEdge[] edges){
        for(MyNode n:nodes){
            n.visited = false;
            n.depth = Integer.MAX_VALUE;
        }
    }

    static class PQHelper{
        int nodeid;
        int depth;
        int time;

        public PQHelper(int nodeid, int depth, int time) {
            this.nodeid = nodeid;
            this.depth = depth;
            this.time = time;
        }

        @Override
        public String toString() {
            return "PQHelper{" +
                    "nodeid=" + nodeid +
                    ", depth=" + depth +
                    ", time=" + time +
                    '}';
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
            this.edges = new LinkedList<>();
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
