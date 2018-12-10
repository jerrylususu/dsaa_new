package lab9;

import java.util.*;

// try to use delete in 0
// use pq to optimize

// can dfs do this?

// tle
// use fast read and passed

public class lab9e {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
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
                System.out.println("impossible");
                continue;
            } else {
                for(MyNode n:res){
                    System.out.print((1+n.id)+" ");
                    System.out.println();
                }
            }




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
}
