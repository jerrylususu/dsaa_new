//package lab8;
//
//import java.util.*;
//
//// iteration to topological sort
//// dp to find max path
//// tle
//
//// found out that List of in is not used
//// changed to incount
//// tle
//
//// suspect that building map tle
//// that's f****** true
//
//
//public class lab8f3 {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while (totalnum-- > 0) {
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
//            int[] max = new int[]{-1};
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
//    public static int[] calc(List<MyGraphNode> s,int[] max){
//        int n = s.size();
//        int[] dist = new int[n];
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
//
//}
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