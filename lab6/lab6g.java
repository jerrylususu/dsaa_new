//package lab6;
//
//import java.util.*;
//
//public class lab6g {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while(totalnum-->0){
//            int n = in.nextInt();
//            TreeNode[] arr = new TreeNode[n];
//            for (int i = 0; i < n; i++) {
//                int val = in.nextInt();
//                arr[i] = new TreeNode(val,i);
//            }
//            for (int i = 0; i < n-1; i++) {
//                TreeNode t1 = arr[in.nextInt()-1], t2 = arr[in.nextInt()-1];
//                if(t1.val<t2.val){
//                    t1.out.add(t2);
//                } else {
//                    t2.out.add(t1);
//                }
//            }
//
//            // search
//            int max = Integer.MIN_VALUE;
//            for (int i = 0; i < n; i++) {
//                if(arr[i].maxsize==-1){
//                    findMaxRe(arr[i]);
//                }
//                int res = arr[i].maxsize;
////                int res = findMax(arr[i]);
////                System.out.println(i+" "+res);
//                if(res>max){
//                    max = res;
//                }
//            }
//            // choose max
//            System.out.println(max);
//
//        }
//    }
//
//    public static void findMaxRe(TreeNode root){
//        System.out.println(root);
//        if(root.maxsize!=-1){
//            return;
//        }
//        int cnt=1;
//        for(TreeNode node:root.out){
//            System.out.println(root);
//            if(node.maxsize==-1){
//                findMaxRe(node);
//            }
//            cnt+=node.maxsize;
//        }
//        root.maxsize=cnt;
//    }
//
//    public static int findMax(TreeNode root){
//        LinkedList<TreeNode> queue = new LinkedList<>();
//        int cnt=1;
//        queue.addAll(root.out);
//        while(!queue.isEmpty()){
//            TreeNode node = queue.pollFirst();
//            cnt++;
//            queue.addAll(node.out);
//        }
//        return cnt;
//    }
//
//    public static void buildGraph(TreeNode root, int parent){
//        for(TreeNode node:root.li){
////            if(node.id!=parent){
//                if(node.val<=root.val){
//                    root.in.add(node);
//                } else {
//                    root.out.add(node);
//                }
////            }
//        }
//        for (TreeNode node:root.li){
//            if(node.id!=parent){
//                buildGraph(node, root.id);
//            }
//        }
//
////        LinkedList<TreeNode> queue = new LinkedList<>();
////        queue.addAll(root.li);
////        while (!queue.isEmpty()){
////
////
////        }
//
//    }
//}
//
//class TreeNode{
//    List<TreeNode> li;
//    List<TreeNode> in;
//    List<TreeNode> out;
//    int id;
//    int val;
//    int maxsize;
//    int depth;
//
//    public TreeNode(int val, int id){
//        li = new LinkedList<>();
//        in = new LinkedList<>();
//        out = new LinkedList<>();
//        this.val=val;
//        this.id=id;
//        maxsize=-1;
//        depth=0;
//    }
//
//    @Override
//    public String toString() {
//        return "TreeNode{" +
//                "val=" + val +
//                ", maxsize=" + maxsize +
//                ", depth=" + depth +
//                '}';
//    }
//}
