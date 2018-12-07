package lab8;

// new attempt of lab8 h
// try to use binary separation
// only using log(n) time in theory
// reference to hdu 6166


// this only finished the dijkstra algorithm
// also with the dijkstra optimized with min heap

// fast2: removed time[], just compare dis[n] with the element in heap
import java.util.*;

public class Dijkstra {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while(totalnum-->0){
            int n = in.nextInt(),m=in.nextInt(),k=in.nextInt();
            MyNode[] nodearr = new MyNode[n];
            for (int i = 0; i < n; i++) {
                nodearr[i] = new MyNode(i);
            }
            for (int i = 0; i < m; i++) {
                int n1=in.nextInt()-1, n2=in.nextInt()-1, w=in.nextInt();
                MyEdge edge = new MyEdge(nodearr[n1],nodearr[n2],w);
                nodearr[n1].edges.add(edge);
                nodearr[n2].edges.add(edge);
            }

            int[] dis = dijkstraFast2(nodearr[0],nodearr);

            System.out.println(Arrays.toString(dis));


        }
    }

    public static int[] dijkstraFast2(MyNode s, MyNode[] arr){
        int[] dis = new int[arr.length];
        Arrays.fill(dis,0x3f3f3f3f); // add won't overflow
        dis[s.id]=0;
        // key=id, value=dis
        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue()-o2.getValue();
            }
        });

        for(MyEdge edge:s.edges){
            dis[edge.to.id] = edge.weight;
            pq.add(new AbstractMap.SimpleEntry<>(edge.to.id, edge.weight));
        }

        while(!pq.isEmpty()){

            // make sure the new next
            Map.Entry<Integer,Integer> next = pq.poll();
            while(next.getValue()<dis[next.getKey()]){
                next = pq.poll();
            }
            MyNode cur = arr[next.getKey()];
            for(MyEdge edge:cur.edges){
                if(edge.weight + dis[cur.id] < dis[edge.to.id]){
                    dis[edge.to.id] = edge.weight + dis[cur.id];
                    pq.add(new AbstractMap.SimpleEntry<>(edge.to.id,dis[edge.to.id]));
                }

            }

        }

        return dis;
    }

    public static int[] dijkstraFast(MyNode s, MyNode[] arr){
        int[] dis = new int[arr.length];
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

        for(MyEdge edge:s.edges){
            dis[edge.to.id] = edge.weight;
            time[edge.to.id] = curtime;
            pq.add(new MyTempStorage(edge.weight,edge.to.id,curtime));
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
            for(MyEdge edge:cur.edges){
                if(edge.weight + dis[cur.id] < dis[edge.to.id]){
                    dis[edge.to.id] = edge.weight + dis[cur.id];
                    pq.add(new MyTempStorage(dis[edge.to.id],edge.to.id,curtime));
                }
            }

        }

        return dis;
    }

    public static int[] dijkstra(MyNode s, MyNode[] arr){
        int[] dis = new int[arr.length];
        Arrays.fill(dis,Integer.MAX_VALUE);
        boolean[] vis = new boolean[arr.length];

        vis[s.id]=true;
        dis[s.id]=0;
        for(MyEdge edge:s.edges){
            dis[edge.to.id] = edge.weight;
//            vis[edge.to.id] = true;
        }

        int i=1,n=arr.length;
        while(i<n){
            int curmindis = Integer.MAX_VALUE;
            int curmin = -1;
            boolean done = true;
            for (int j = 0; j < n; j++) {
                if(!vis[j]&&dis[j]<curmindis){
                    curmin = j;
                    curmindis = dis[j];
                    done = false;
                }
            }

            if(done){
                break;
            }
            MyNode cur = arr[curmin];
            for(MyEdge edge:cur.edges){
                if(edge.weight+curmindis<dis[edge.to.id]){
                    dis[edge.to.id] = edge.weight+curmindis;
                }
            }

            i++;
            vis[curmin]=true;
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

    static class MyEdge{
        MyNode from;
        MyNode to;
        int weight;


        public MyEdge(MyNode from, MyNode to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "MyEdge{" +
                    "from=" + from.id +
                    ", to=" + to.id +
                    ", weight=" + weight +
                    '}';
        }
    }

    static class MyNode{
        int id;
        List<MyEdge> edges;
//        int tmpdist = Integer.MAX_VALUE;

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
