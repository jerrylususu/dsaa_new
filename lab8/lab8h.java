//package lab8;
//
//// initial idea: for each node in set, try to run BFS to get the nearest another node in set,
//// and stop as early as possible.
//
//// again... system.out lead to TLE
//
//import java.util.*;
//
//public class lab8h {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while (totalnum-->0){
//
//            // build map
//            int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
//            MyNode[] arr = new MyNode[n];
//            for (int i = 0; i < n; i++) {
//                arr[i] = new MyNode(i+1);
//            }
//            for (int i = 0; i < m; i++) {
//                int n1 = in.nextInt()-1, n2 = in.nextInt()-1;
//                arr[n1].edges.add(arr[n2]);
//                arr[n2].edges.add(arr[n1]);
//            }
//            TreeSet<Integer> ts = new TreeSet<>();
//            for (int i = 0; i < k; i++) {
//                ts.add(in.nextInt());
//            }
//
//            // query for each node in set once?
//            int min = Integer.MAX_VALUE;
//            for(Integer nodeid:ts){
//                int res = findNearest(arr[nodeid-1],ts);
//                if(min>res){
//                    min = res;
//                }
//                reset(arr);
//            }
//
//            // output
//            System.out.println(min);
//
//
//        }
//
//    }
//
//    public static void reset(MyNode[] arr){
//        for(MyNode n:arr){
//            n.depth = -1;
//            n.vis = false;
//        }
//    }
//
//    public static int findNearest(MyNode node,TreeSet<Integer> ts){
//        LinkedList<MyNode> q = new LinkedList<>();
//        q.addLast(node);
//        node.vis = true;
//        node.depth = 0;
//
//        while(!q.isEmpty()){
//            MyNode cur = q.pollFirst();
//            for(MyNode n:cur.edges){
//                if(!n.vis){ // hasn't visited yet
//                    n.vis = true;
//                    n.depth = cur.depth + 1;
//                    if(ts.contains(n.id)){
//                        return n.depth; // find one nearest, stop as early as possible
//                    } else {
//                        q.addLast(n); // didn't find, continue to bfs
//                    }
//                }
//            }
//        }
//        return Integer.MAX_VALUE; // didn't find
//    }
//
//
//    static class MyNode{
//        int id;
//        boolean vis;
//        int depth;
//        List<MyNode> edges;
//
//        private MyNode(int id){
//            this.id = id;
//            this.vis = false;
//            this.depth = -1;
//            edges = new ArrayList<>();
//        }
//
//        @Override
//        public String toString() {
//            return "MyNode{" +
//                    "id=" + id +
//                    ", vis=" + vis +
//                    ", depth=" + depth +
//                    ", edges=" + edges.size() +
//                    '}';
//        }
//    }
//}
//
