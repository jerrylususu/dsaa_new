//package lab8;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Queue;
//import java.util.Scanner;
//
//public class lab8c {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while(totalnum-->0){
//            int n = in.nextInt();
//            int m = in.nextInt();
//            int s = in.nextInt()-1;
//
//            MyGraphNode[] arr = new MyGraphNode[n];
//            for (int i = 0; i < n; i++) {
//                arr[i] = new MyGraphNode(i+1);
//            }
//
//            for (int i = 0; i < m; i++) {
//                int n1 = in.nextInt()-1, n2 = in.nextInt()-1;
//                arr[n1].link.add(arr[n2]);
//                arr[n2].link.add(arr[n1]);
//            }
//
//            setLevel(arr[s]);
//
//            for (int i = 0; i < n; i++) {
//                System.out.print(arr[i].tmplevel+" ");
//            }
//
//        }
//    }
//
//    public static void setLevel(MyGraphNode s){
//        LinkedList<MyGraphNode> q = new LinkedList<>();
//        q.addLast(s);
//        s.vis = true; s.tmplevel = 0;
//        while(!q.isEmpty()){
//            MyGraphNode cur = q.pollFirst();
////            System.out.println(cur);
//            for(MyGraphNode n:cur.link){
//                if(!n.vis){
//                    q.addLast(n);
//                    n.vis = true;
//                    n.tmplevel = cur.tmplevel+1;
//                }
//            }
//        }
//    }
//
//    public static int findShortest(MyGraphNode[] arr, MyGraphNode s, MyGraphNode e){
//        LinkedList<MyGraphNode> q = new LinkedList<>();
//        q.addLast(s);
//        s.vis = true; s.tmplevel = 0;
//        while(!q.isEmpty()){
//            MyGraphNode cur = q.pollFirst();
//            if(cur==e){
//                return cur.tmplevel;
//            }
//
//            for(MyGraphNode n:cur.link){
//                if(!n.vis){
//                    q.addLast(n);
//                    n.tmplevel = cur.tmplevel+1;
//                }
//            }
//        }
//
//        return -1;
//
//    }
//
//    public static void reset(MyGraphNode[] arr){
//        for(MyGraphNode n:arr){
//            n.vis = false;
//            n.tmplevel = -1;
//        }
//    }
//}
//
//
//class MyGraphNode{
//    int id;
//    List<MyGraphNode> link;
//    boolean vis = false;
//    int tmplevel = -1;
//
//    public MyGraphNode(int id) {
//        this.id = id;
//        this.link = new LinkedList<>();
//    }
//}