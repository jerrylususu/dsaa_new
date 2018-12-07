//package lab8;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Scanner;
//
//public class lab8b {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while(totalnum-->0){
//            int noden = in.nextInt();
//            int edgen = in.nextInt();
//
//            MyGraphNode[] arr = new MyGraphNode[noden];
//
//            for (int i = 0; i < noden; i++) {
//                arr[i] = new MyGraphNode(i+1);
//            }
//
//            for (int i = 0; i < edgen; i++) {
//                arr[in.nextInt()-1].out.add(arr[in.nextInt()-1]);
//            }
//
//            int queryn = in.nextInt();
//            for (int i = 0; i < queryn; i++) {
//                System.out.println(check(arr[in.nextInt()-1],arr[in.nextInt()-1])?"YES":"NO");
//                for (int j = 0; j < noden; j++) {
//                    arr[j].vis = false;
//                }
//            }
//
//        }
//    }
//
//    public static boolean check(MyGraphNode s, MyGraphNode e){
//        LinkedList<MyGraphNode> ll = new LinkedList<>();
//        ll.addLast(s);
//        s.vis = true;
//        while(!ll.isEmpty()){
//            MyGraphNode cur = ll.pollFirst();
//            if(cur==e){
//                return true;
//            }
//            for(MyGraphNode n:cur.out){
//                if(!n.vis){
//                    n.vis = true;
//                    ll.addLast(n);
//                }
//            }
//        }
//        return false;
//    }
//}
//
//class MyGraphNode{
//    int id;
//    List<MyGraphNode> out;
//    boolean vis = false;
//
//    public MyGraphNode(int id) {
//        this.id = id;
//        this.out = new LinkedList<>();
//    }
//}