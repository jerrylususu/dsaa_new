//package lab6;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Scanner;
//
//public class lab6d {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();;
//
//        while(totalnum-->0){
//            int n = in.nextInt();
//            List<TreeNode> li = new ArrayList<>(n);
//            for(int i=0;i<n;i++){
//                li.add(new TreeNode(i+1));
//            }
//            for (int i = 0; i < n-1; i++) {
//                TreeNode tn1 = li.get(in.nextInt()-1), tn2 = li.get(in.nextInt()-1);
//                tn1.li.add(tn2);
//                tn2.li.add(tn1);
//            }
//
//            // find i: the longest from root
//            TreeNode root = li.get(0);
//            LinkedList<TreeNode> queue = new LinkedList<>();
//            queue.addAll(root.li);
//            for(TreeNode tn:root.li){
//                tn.rootno = root.val;
//                tn.depth = 1;
//            }
//            TreeNode deepestNode = null;
//            int curmaxdepth = 0;
//            while(queue.size()!=0){
//                TreeNode cur = queue.pollFirst();
//                System.out.println(cur);
//                if(cur.depth>curmaxdepth){
//                    curmaxdepth=cur.depth;
//                    deepestNode = cur;
//                }
//                if(cur.li.size()!=0){
//                    for(TreeNode son:cur.li){
//                        if(son.val!=cur.rootno) {
//                            queue.add(son);
//                            son.depth = cur.depth + 1;
//                            if (son.li.size() != 0) {
//                                for (TreeNode p : son.li) {
//                                    if (p.val == cur.val) {
//                                        son.rootno = p.val;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//
//            }
//
//
////            System.out.println(curmaxdepth);
////            System.out.println(deepestNode);
//
//            // find j: the other furthest one
//            // set another root: just like the first root setting...
//            // all is the same
//            TreeNode i = deepestNode;
//            TreeNode secDeepestNode = null;
//            int secMaxDepth = 0;
//
//            queue.addAll(i.li);
//            for(TreeNode tn:i.li){
//                tn.depth = 1;
//                tn.rootno = i.val;
//            }
//
//            while(queue.size()!=0){
//                TreeNode cur = queue.pollFirst();
//                System.out.println(cur);
//                if(cur.depth>secMaxDepth){
//                    secMaxDepth = cur.depth;
//                    secDeepestNode = cur;
//                }
//                if(cur.li.size()!=0){
//                    for(TreeNode son: cur.li){
//                        if(son.val!=cur.rootno){
//                            queue.add(son);
//                            son.depth = cur.depth+1;
//                            if(son.li.size()!=0){
//                                for(TreeNode p:son.li){
//                                    if(p.val==cur.val){
//                                        son.rootno=p.val;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            System.out.println(secMaxDepth);
////            System.out.println(secDeepestNode);
//
//        }
//    }
//}
//
//class TreeNode{
//    int val;
//    List<TreeNode> li;
//    int iter;
//    int rootno;
//    int depth;
//
//    public TreeNode(int i){
//        this.val=i;
//        this.iter=0;
//        this.rootno=0;
//        this.li =new ArrayList<>();
//        this.depth=0;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        TreeNode treeNode = (TreeNode) o;
//
//        return val == treeNode.val;
//    }
//
//    @Override
//    public int hashCode() {
//        return val;
//    }
//
//    @Override
//    public String toString() {
//        return "TreeNode{" +
//                "val=" + val +
//                ", iter=" + iter +
//                ", rootno=" + rootno +
//                ", depth=" + depth +
//                '}';
//    }
//}
//
