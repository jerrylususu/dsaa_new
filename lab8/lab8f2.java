//package lab8;
//
//import java.util.*;
//
//// dfs to topological sort
//// dp to find max path
//// tle for stackoverflow
//
//public class lab8f2 {
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
//                to.in.add(e);
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
//            if(node.in.size()==0){
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
//    public static void visit(MyGraphNode node, List<MyGraphNode> l){
//        if(!node.visited){
//            node.visited = true;
//            for(MyGraphEdge e:node.in){
//                visit(e.from,l);
//            }
//            l.add(node);
//        }
//    }
//
//    public static List<MyGraphNode> topoSort(MyGraphNode[] arr){
//        LinkedList<MyGraphNode> s = new LinkedList<>();
//        for(MyGraphNode n:arr){
//            if(n.out.size()==0){
//                s.add(n);
//            }
//        }
////        System.out.println(s);
//        List<MyGraphNode> ll = new LinkedList<>();
//        for(MyGraphNode n:s){
//            visit(n,ll);
//        }
//        return ll;
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
//    List<MyGraphEdge> in;
//    List<MyGraphEdge> out;
//    boolean visited = false;
//    int tmpdepth = -1;
//
//    public MyGraphNode(int id){
//        this.id = id;
//        this.in = new LinkedList<>();
//        this.out = new LinkedList<>();
//    }
//
//    @Override
//    public String toString() {
//        return "MyGraphNode{" +
//                "id=" + id +
//                ", in=" + in.size() +
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