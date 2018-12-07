//package lab7;
//
//import java.util.Scanner;
//import java.util.Stack;
//
//public class lab7c {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while(totalnum-->0){
//            int n = in.nextInt();
//            MyAVLNode[] arr = new MyAVLNode[n];
//            for (int i = 0; i < n; i++) {
//                arr[i] = new MyAVLNode(i+1,in.nextInt());
//            }
//            boolean check = true;
//            for (int i = 0; i < n; i++) {
//                // ln for left node, etc..
//                int lnum = in.nextInt(), rnum = in.nextInt();
//                MyAVLNode ln = null, rn = null, p = arr[i];
//                if(lnum==0){
//
//                } else{
//                    ln = arr[lnum-1];
//                    if(ln.val<p.val){
//                        if (p.left == null) {
//                            p.left = ln;
//                            ln.parent = p;
//                        } else {
//                            check = false;
//                        }
//                    } else {
//                        check = false;
//                    }
//                }
//
//                if(rnum==0){
//
//                } else {
//                    rn = arr[rnum-1];
//                    if (rn.val > p.val) {
//                        if (p.right == null) {
//                            p.right = rn;
//                            rn.parent = p;
//                        } else {
//                            check = false;
//                        }
//                    } else {
//                        check = false;
//                    }
//                }
//            }
//
//            if(!check){
//                System.out.println("No");
//                continue;
//            }
//
//            MyAVLNode root = arr[0];
//            while(root.parent!=null){
//                root = root.parent;
//            }
//
//            boolean[] checkarr = {true};
//            root.depth=1;
//            setDepth(root,checkarr);
//
//            if(!checkarr[0]){
//                System.out.println("No");
//                continue;
//            }
//
//            int[] last = {Integer.MIN_VALUE};
//            inOrderWhile(root,last,checkarr);
//
//            System.out.println(checkarr[0]?"Yes":"No");
//
//        }
//    }
//
//    public static int setDepth(MyAVLNode node,boolean[] checkarr){
//        if(node.left!=null){
//            node.left.depth = node.depth+1;
//            node.leftdepth=setDepth(node.left,checkarr);
//        } else {
//            node.leftdepth=0;
//        }
//        if(node.right!=null){
//            node.right.depth = node.depth+1;
//            node.rightdepth=setDepth(node.right,checkarr);
//        } else {
//            node.rightdepth=0;
//        }
//        if(Math.abs(node.leftdepth-node.rightdepth)>1){
//            checkarr[0]=false;
//        }
//        return Math.max(node.leftdepth,node.rightdepth)+1;
//    }
//
//    public static void inOrderWhile(MyAVLNode node, int[] last, boolean[] checkarr){
//        Stack<MyAVLNode> stack = new Stack<>();
//        MyAVLNode cur = node;
//
//        while(cur!=null||stack.height()>0){
//
//            while(cur!=null){
//                stack.push(cur);
//                cur = cur.left;
//            }
//
//            if(stack.height()>0) {
//                MyAVLNode vis = stack.pop();
////                System.out.print(vis.val + " ");
//                if(vis.val>last[0]){
//                    last[0] = vis.val;
//                } else {
//                    checkarr[0] = false;
//                }
//                cur = vis.right;
//            }
//        }
//    }
//
//}
//
//class MyAVLNode{
//    int id, val, depth;
//    MyAVLNode left, right, parent;
//    int leftdepth, rightdepth;
//
//    public MyAVLNode(int i, int v){
//        id=i;
//        val=v;
//        depth=-1;
//    }
//
//    @Override
//    public String toString() {
//        return "MyAVLNode{" +
//                "id=" + id +
//                ", val=" + val +
//                ", leftdepth=" + leftdepth +
//                ", rightdepth=" + rightdepth +
//                '}';
//    }
//}
