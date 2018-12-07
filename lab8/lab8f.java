//package lab8;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Scanner;
//
//public class lab8f {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while(totalnum-->0){
//            int noden = in.nextInt();
//            int edgen = in.nextInt();
//
//            MyGraphNode[] nodearr = new MyGraphNode[noden];
//
//            for (int i = 0; i < noden; i++) {
//                nodearr[i] = new MyGraphNode(i+1);
//            }
//
//            for (int i = 0; i < edgen; i++) {
//                MyGraphNode from = nodearr[in.nextInt()-1];
//                MyGraphNode to = nodearr[in.nextInt()-1];
//                int val = in.nextInt();
//                MyGraphEdge e = new MyGraphEdge(from,to,val);
//                from.out.add(e);
//                to.in.add(e);
//            }
//
//            MyGraphNode start = null;
//
//            for(MyGraphNode n:nodearr){
//                if(n.in.isEmpty()){
//                    start = n;
//                    break;
//                }
//            }
//
//            MyGraphNode end = getDeepestNode1(start);
//            System.out.println(end.tmpdepth);
//
//
//        }
//    }
//
//    public static void reset(MyGraphNode[] arr){
//        for(MyGraphNode n:arr){
//            n.tmpdepth = -1;
//            n.visited = false;
//        }
//    }
//
//    public static MyGraphNode getDeepestNode1(MyGraphNode root){
//        LinkedList<MyGraphNode> q = new LinkedList<>();
//        q.addLast(root);
//        root.visited = true;
//        root.tmpdepth = 0;
//        MyGraphNode deepest = root;
//        while(!q.isEmpty()){
//            MyGraphNode cur = q.pollFirst();
//            if(cur.tmpdepth>deepest.tmpdepth){
//                deepest = cur;
//            }
//            for(MyGraphEdge e:cur.out){
//                if(!e.to.visited){
//                    e.to.visited = true;
//                    e.to.tmpdepth = cur.tmpdepth + e.val;
//                    q.addLast(e.to);
//                }
//            }
//        }
//        return deepest;
//    }
//
//    public static MyGraphNode getDeepestNode2(MyGraphNode root){
//        LinkedList<MyGraphNode> q = new LinkedList<>();
//        q.addLast(root);
//        root.visited = true;
//        root.tmpdepth = 0;
//        MyGraphNode deepest = root;
//        while(!q.isEmpty()){
//            MyGraphNode cur = q.pollFirst();
//            if(cur.tmpdepth>deepest.tmpdepth){
//                deepest = cur;
//            }
//            for(MyGraphEdge e:cur.in){
//                if(!e.from.visited){
//                    e.from.visited = true;
//                    e.from.tmpdepth = cur.tmpdepth + e.val;
//                    q.addLast(e.from);
//                }
//            }
//        }
//        return deepest;
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
//}