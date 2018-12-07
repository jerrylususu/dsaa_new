package lab7;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

// judge whether the tree is a balanced search tree
// request: left < mid < right for all
public class lab7b {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        for(int currentcase = 1;currentcase<=totalnum;currentcase++){
            int n = in.nextInt();
            MyNode[] arr = new MyNode[n];
            boolean check = true;
            for (int i = 0; i < n; i++) {
                arr[i] = new MyNode(i+1,in.nextInt());
            }
            for (int i = 0; i < n-1; i++) {
                MyNode father = arr[in.nextInt()-1], son = arr[in.nextInt()-1];
                if(son.val<father.val){
                    if(father.left==null){
                        father.left = son;
                        son.parent = father;
                    } else {
                        check = false;
                    }
                } else if (son.val>father.val){
                    if(father.right==null){
                        father.right = son;
                        son.parent = father;
                    } else {
                        check = false;
                    }
                } else {
                    check = false;
                }
            }
            if(!check){
                System.out.printf("Case #%d: NO%n",currentcase);
                continue;
            }

            // pass init check

            // find root
            MyNode root = arr[0];
            while(root.parent!=null){
                root = root.parent;
            }

            // do inorder traversal
            int[] last = new int[1];
            boolean[] checkarr = new boolean[1];
            checkarr[0] = true;
            last[0] = Integer.MIN_VALUE;
            inorderstack(root, last,checkarr);

            if(!checkarr[0]){
                System.out.printf("Case #%d: NO%n",currentcase);
            } else {
                System.out.printf("Case #%d: YES%n",currentcase);
            }



        }

    }

    public static void inorder(MyNode node,int[] last,boolean[] checkarr){
        if (node.left!=null){
            inorder(node.left,last,checkarr);
        }
        if(node.val<=last[0]){
            checkarr[0] = false;
            return;
        } else {
            last[0] = node.val;
        }
        if(node.right!=null){
            inorder(node.right,last,checkarr);
        }
    }

    public static void inorderstack(MyNode node,int[] last,boolean[] checkarr){
        Stack<MyNode> stack = new Stack<>();
        MyNode cur = node;
//        stack.push(cur);

        while(stack.height()!=0||cur!=null){
            while(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }

            if(stack.height()>0){
                MyNode vis = stack.pop();
//                System.out.println(vis.val);
//                System.out.println(stack.toString());
                if(vis.val>last[0]){
                    last[0] = vis.val;
                } else {
                    checkarr[0] = false;
                    break;
                }
                cur = vis.right;
            }

        }
    }

    public static void inordermanual(MyNode node,int[] last,boolean[] checkarr){

    }
}

class MyNode {
    int no;
    int val;
    int depth;
    MyNode parent = null;
    MyNode left = null;
    MyNode right = null;

    public MyNode(int i,int val) {
        no = i;
        depth = -1;
        this.val = val;
    }

    @Override
    public String toString() {
        return "MyNode{" +
                "no=" + no +
                ", val=" + val +
                ", depth=" + depth +
                '}';
    }
}

class CallContext { //this class is similar to the stack frame
    Object[] args;
    List<Object> vars = new LinkedList<>();
    int resumePoint = 0;

    public CallContext(Object[] args) {
        this.args = args;
    }

}