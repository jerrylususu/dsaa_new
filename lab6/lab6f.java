//package lab6;
//
////import javax.swing.tree.TreeNode;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Scanner;
//
//public class lab6f {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while(totalnum-->0){
//            // get the nodes
//            int n = in.nextInt();
//            TreeNode[] arr = new TreeNode[n];
//            for (int i = 0; i < n; i++) {
//                arr[i] = new TreeNode(i+1);
//                arr[i].color = in.nextInt();
//            }
//            for (int i = 0; i < n - 1; i++) {
//                TreeNode t1 = arr[in.nextInt()-1], t2 = arr[in.nextInt()-1];
//                t1.li.add(t2); t2.li.add(t1);
//            }
//
//            // start to set depth for each layer
//            TreeNode root = arr[0];
//            root.depth=0;
//            int[] maxdepth = new int[1];
//            setDepth(root,0,maxdepth);
//
//            // start to count
//            int[] black = new int[maxdepth[0]+1];
//            countDepth(root,0,black);
////            System.out.println(Arrays.toString(black));
//
//            // judge
//            boolean allEven = true;
//            for (int i = 0; i < black.length; i++) {
//                if(black[i]%2!=0){
//                    allEven = false; break;
//                }
//            }
//
//            System.out.println(allEven?"NO":"YES");
//        }
//    }
//
//    public static void countDepth(TreeNode root, int parent , int[] count){
//        if(root.color==1) count[root.depth]++;
//        for (int i = 0; i < root.li.size(); i++) {
//            TreeNode n = root.li.get(i);
//            if (n.val != parent) {
//                countDepth(n,root.val,count);
//            }
//        }
//    }
//
//    public static void setDepth(TreeNode root, int parent, int[] maxdepth){
//        if(root.depth>maxdepth[0]) maxdepth[0]=root.depth;
//        for (int i = 0; i < root.li.size(); i++) {
//            TreeNode n = root.li.get(i);
//            if(n.val!=parent){
//                n.depth=root.depth+1;
//                setDepth(n,root.val,maxdepth);
//            }
//        }
//
//    }
//}
//
//class TreeNode{
//    List<TreeNode> li;
//    int val;
//    int color;
//    int depth;
//
//    public TreeNode(int i){
//        li = new ArrayList<>();
//        val=i;
//        color=0;
//        depth=0;
//    }
//
//    @Override
//    public String toString() {
//        return "TreeNode{" +
//                "val=" + val +
//                ", color=" + color +
//                ", depth=" + depth +
//                '}';
//    }
//}
