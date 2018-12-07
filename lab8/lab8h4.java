package lab8;

// new attempt of lab8 h
// try to use binary separation
// only using log(n) time in theory
// reference to hdu 6166

// build map tle


import java.util.*;

public class lab8h4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while(totalnum-->0){
            int n = in.nextInt(),m=in.nextInt(),k=in.nextInt();
            MyNode[] nodearr = new MyNode[n+1];
            for (int i = 1; i < n+1; i++) {
                nodearr[i] = new MyNode(i);
            }
            for (int i = 0; i < m; i++) {
                int n1=in.nextInt(), n2=in.nextInt();
                nodearr[n1].edges.add(new MyEdge(nodearr[n2],1));
                nodearr[n2].edges.add(new MyEdge(nodearr[n1],1));
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
//                        ll2.addLast(j);
                        src.edges.add(new MyEdge(nodearr[set[j]],0));
                    } else {
                        // sink
//                        ll3.addLast(j);
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

            System.out.println(min);

//
//            int[] dis = dijkstraFast(nodearr[0],nodearr);
//
//            System.out.println(Arrays.toString(dis));


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

        for(MyEdge edge:s.edges){
            dis[edge.another.id] = edge.weight;
            time[edge.another.id] = curtime;
            pq.add(new MyTempStorage(edge.weight,edge.another.id,curtime));
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
                if(edge.weight + dis[cur.id] < dis[edge.another.id]){
                    dis[edge.another.id] = edge.weight + dis[cur.id];
                    pq.add(new MyTempStorage(dis[edge.another.id],edge.another.id,curtime));
                }
                if(edge.another.tmpSinkEdge){
                    if(dis[edge.another.id]<dis[n]){
                        dis[n] = dis[edge.another.id];
                    }
                }
            }

        }

        return dis;
    }

//    public static int[] dijkstra(MyNode s, MyNode[] arr){
//        int[] dis = new int[arr.length];
//        Arrays.fill(dis,Integer.MAX_VALUE);
//        boolean[] vis = new boolean[arr.length];
//
//        vis[s.id]=true;
//        dis[s.id]=0;
//        for(MyEdge edge:s.edges){
//            dis[edge.to.id] = edge.weight;
////            vis[edge.to.id] = true;
//        }
//
//        int i=1,n=arr.length;
//        while(i<n){
//            int curmindis = Integer.MAX_VALUE;
//            int curmin = -1;
//            boolean done = true;
//            for (int j = 0; j < n; j++) {
//                if(!vis[j]&&dis[j]<curmindis){
//                    curmin = j;
//                    curmindis = dis[j];
//                    done = false;
//                }
//            }
//
//            if(done){
//                break;
//            }
//            MyNode cur = arr[curmin];
//            for(MyEdge edge:cur.edges){
//                if(edge.weight+curmindis<dis[edge.to.id]){
//                    dis[edge.to.id] = edge.weight+curmindis;
//                }
//            }
//
//            i++;
//            vis[curmin]=true;
//        }
//
//        return dis;
//
//    }

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
        MyNode another;
        int weight;


        public MyEdge(MyNode another, int weight) {
            this.another = another;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "MyEdge{" +
                    "another=" + another +
                    ", weight=" + weight +
                    '}';
        }
    }

    static class MyNode{
        int id;
        List<MyEdge> edges;
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
