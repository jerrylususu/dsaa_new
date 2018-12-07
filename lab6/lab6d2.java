//package lab6;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class lab6d2 {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while(totalnum-->0){
//            int n = in.nextInt();
//            TreeNode[] arr = new TreeNode[n];
//            for (int i = 0; i < n; i++) {
//                arr[i] = new TreeNode(i+1);
//            }
//            for (int i = 0; i < n - 1; i++) {
//                TreeNode t1 = arr[in.nextInt()-1], t2 = arr[in.nextInt()-1];
//                t1.li.add(t2); t2.li.add(t1);
//            }
//
//            iter(arr[0],0);
//            System.out.println(arr[0].maxL);
////            for (int i = 0; i < n; i++) {
////                System.out.println(arr[i]);
////            }
//        }
//    }
//
//    public static void iter(TreeNode root, int parent) {
//        if (root.li.size() == 1) {
//            // this is a leaf node
//            root.maxD = 0;
//            root.maxL = 0;
//            return;
//        }
//
//        for (int i = 0; i < root.li.size(); i++) {
//            if(root.li.get(i).val != parent ){
//                iter(root.li.get(i), root.val);
//            }
//        }
//
//        int maxD1 = -1, maxD2 = -1, maxL = -1;
//        for (int i = 0; i < root.li.size(); i++) {
//            if(root.li.get(i).val != parent ) {
//                TreeNode n = root.li.get(i);
//                if (n.maxD > maxD1) {
//                    maxD2 = maxD1;
//                    maxD1 = n.maxD;
//                } else if (n.maxD == maxD1){
//                    maxD2 = maxD1;
//                } else if (n.maxD > maxD2){
//                    maxD2 = n.maxD;
//                }
//                if (n.maxL > maxL) {
//                    maxL = n.maxL;
//                }
//            }
//        }
////        if(parent==0) System.out.println(maxD1+" "+maxD2+" "+maxL);
//        root.maxD = maxD1 + 1;
//        root.maxL = Math.max(maxD1 + maxD2 + 2, maxL);
//    }
//}
//
//
//class TreeNode{
//    int val;
//    List<TreeNode> li;
//    int maxL;
//    int maxD;
//
//    public TreeNode(int i){
//        this.val=i;
//        this.li =new ArrayList<>();
//        this.maxD=0;
//        this.maxL=0;
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
//                ", maxL=" + maxL +
//                ", maxD=" + maxD +
//                '}';
//    }
//}
