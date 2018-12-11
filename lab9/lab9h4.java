package lab9;

import java.util.*;

public class lab9h4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();
        while(totalnum-->0){
            int n = in.nextInt();
            Hole[] arr = new Hole[n+2];
            for (int i = 1; i <= n; i++) {
                arr[i] = new Hole(i,in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt());
            }
            arr[0] = new Hole(0,in.nextInt(),in.nextInt(),in.nextInt(),0);
            arr[n+1] = new Hole(n+1,in.nextInt(),in.nextInt(),in.nextInt(),0);

            for (int n1 = 0; n1 < n+2; n1++) {
                for (int n2 = n1+1; n2 < n+2; n2++) {
                    Edge e = new Edge(n1,n2,calcWeight(arr,n1,n2));
                    arr[n1].li.add(e);
                    arr[n2].li.add(e);
                }
            }

            double[] dist = dijknew(arr[0],arr);
            System.out.println(Math.round(dist[n+1]));
        }
    }

/*    public static double[] dijknew(Hole start, Hole[] arr){
        int len = arr.length;
        double[] dist = new double[len];
        Arrays.fill(dist,-1);

        dist[start.id]=0;

        class Pair{
            int id; double weight;

            public Pair(int id, double weight) {
                this.id = id;
                this.weight = weight;
            }
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> {
            return (o1.weight==o2.weight)?0:((o1.weight<o2.weight)?-1:1);
        });

        pq.add(new Pair(start.id,0));

        while(!pq.isEmpty()){
            Pair curpair = pq.poll();
            while(curpair.weight<dist[curpair.id]){
                curpair = pq.poll();
            }

            Hole curhold = arr[curpair.id];
            for(Edge e:curhold.li){
                if(dist[e.sum-curhold.id]==-1||e.weight+dist[curhold.id]<dist[e.sum-curhold.id]){
                    dist[e.sum-curhold.id] = e.weight+dist[curhold.id];
                    pq.add(new Pair(e.sum-curhold.id, dist[e.sum-curhold.id]));
                }
            }
        }

        return dist;
    }*/

    public static double[] dijkold(Hole start, Hole[] arr){
        int len = arr.length;
        double[] dist = new double[len];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] vis = new boolean[len];
        dist[start.id]=0;
        vis[start.id]=true;

        int count = 1;
        for (int i = 0; i < len; i++) {
            if(!vis[i]){

            }
        }


        return dist;
    }

    public static double pow(double i){
        return i*i;
    }

    public static double calcWeight(Hole[] arr, int i, int j){
        return 100*(Math.sqrt(pow(arr[i].x-arr[j].x)+pow(arr[i].y-arr[j].y)+pow(arr[i].z-arr[j].z))-arr[i].r-arr[j].r);
    }

    static class Edge{
        int from, to, sum;
        double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.sum = from+to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", sum=" + sum +
                    ", weight=" + weight +
                    '}';
        }
    }

    static class Hole{
        int x,y,z,r;
        int id;
        List<Edge> li;

        public Hole(int id,int x, int y, int z, int r) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.r = r;
            this.id = id;
            this.li = new LinkedList<>();
        }

        @Override
        public String toString() {
            return "Hole{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    ", r=" + r +
                    ", id=" + id +
                    '}';
        }
    }
}
