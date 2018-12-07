//package lab6;
//
//import javax.swing.tree.TreeNode;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//import java.util.Stack;
//
//public class lab6b {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//        while(totalnum-->0){
//            int n = in.nextInt();
//
//            // build the tree
//            List<BinaryTreeNode> li = new ArrayList<>(n);
//            for (int i = 0; i < n; i++) {
//                li.add(new BinaryTreeNode(i+1));
//            }
//            for (int i = 0; i < n-1; i++) {
//                int p = in.nextInt()-1;
//                int s = in.nextInt()-1;
//                BinaryTreeNode pn = li.get(p);
//                BinaryTreeNode sn = li.get(s);
//                if(pn.left==null){
//                    pn.left = sn;
//                } else {
//                    pn.right = sn;
//                }
//
//            }
//
//            // walk the tree
//            BinaryTreeNode root = li.get(0);
//            preorder(root);
//            System.out.println();
//            preorderiter(root);
//            System.out.println();
//            inorder(root);
//            System.out.println();
//            inorderiter(root);
//            System.out.println();
//            postorder(root);
//            System.out.println();
//
//        }
//    }
//
//    public static void preorderiter (BinaryTreeNode root){
//        Stack<BinaryTreeNode> s = new Stack<>();
//        s.push(root);
//        while(!s.isEmpty()){
//            BinaryTreeNode node = s.pop();
//            System.out.print(node.val+" ");
//            if(node.right!=null){
//                s.push(node.right);
//            }
//            if(node.left!=null){
//                s.push(node.left);
//            }
//        }
//    }
//
//    public static void inorderiter (BinaryTreeNode root){
//        Stack<BinaryTreeNode> s = new Stack<>();
//        s.push(root);
//        while(!s.isEmpty()){
//            BinaryTreeNode node = s.peek();
////            System.out.print(node.val+" ");
//            if(node.right!=null){
//                s.push(node.right);
//            }
////            s.push(node);
//            System.out.print(node.val+" ");
//            if(node.left!=null){
//                s.push(node.left);
//            }
//        }
//    }
//
//    public static void inorder(BinaryTreeNode root){
//        if(root.left!=null) inorder(root.left);
//        System.out.print(root.val+" ");
//        if(root.right!=null) inorder(root.right);
//    }
//
//    public static void preorder (BinaryTreeNode root){
//        System.out.print(root.val+" ");
//        if(root.left!=null) preorder(root.left);
//        if(root.right!=null) preorder(root.right);
//    }
//
//    public static void postorder (BinaryTreeNode root){
//        if(root.left!=null) postorder(root.left);
//        if(root.right!=null) postorder(root.right);
//        System.out.print(root.val+" ");
//    }
//
//}
//
//class BinaryTreeNode {
//    int val;
//    BinaryTreeNode left;
//    BinaryTreeNode right;
//
//    public BinaryTreeNode(int i) {
//        this.val = i;
//    }
//}
