package lab6;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeTravsel {

    public static void main(String[] args) {
        MyNode root = new MyNode(10,
                new MyNode(3, new MyNode(2), new MyNode(6, new MyNode(4),null)),
                new MyNode(18,new MyNode(12),null));
//        level(root);
//        vertical(root);
        System.out.println(pre(7,root));
        System.out.println(post(11,root));
    }

    public static int post(int g,MyNode root){
        int rec = Integer.MAX_VALUE;
        MyNode cur = root;
        while(cur!=null) {
            if(cur.key==g){
                return cur.key;
            } else if (cur.key>g){
                rec = cur.key;
                cur = cur.left;
            } else if (cur.key<g){

                cur = cur.right;
            }
        }
        return rec;
    }

    public static int pre(int g,MyNode root){
        int rec = Integer.MIN_VALUE;
        MyNode cur = root;
        while(cur!=null) {
            if(cur.key==g){
                return cur.key;
            } else if (cur.key>g){
                cur = cur.left;
            } else if (cur.key<g){
                rec = cur.key;
                cur = cur.right;
            }
        }
        return rec;
    }

    public static void vertical(MyNode root){
        class VNode{
            MyNode node;
            int level;

            public VNode(MyNode node, int level) {
                this.node = node;
                this.level = level;
            }
        }

        HashMap<Integer,LinkedList<MyNode>> hm = new HashMap<>();
        LinkedList<VNode> q = new LinkedList<>();
        int min=0, max=0, cur=0;

        q.add(new VNode(root, 0));
        while(!q.isEmpty()){
            VNode c = q.pollFirst();
            cur = c.level;

            min = Math.min(cur,min);
            max = Math.max(cur,max);

            if(hm.containsKey(cur)){
                hm.get(cur).add(c.node);
            } else {
                LinkedList<MyNode> ll = new LinkedList<>();
                ll.add(c.node);
                hm.put(cur, ll);
            }

            if(c.node.left!=null) q.addLast(new VNode(c.node.left, cur-1));
            if(c.node.right!=null) q.addLast(new VNode(c.node.right, cur+1));
        }

        System.out.println(hm);




    }

    public static void level(MyNode root){
        LinkedList<MyNode> q = new LinkedList<>();
        q.addLast(root);
        while (!q.isEmpty()){
            MyNode cur = q.pollFirst();
            System.out.println(cur);
            if(cur.left!=null) q.addLast(cur.left);
            if(cur.right!=null) q.addLast(cur.right);
        }
    }


    public static void post(MyNode root){
        Stack<MyNode> s = new Stack<>();
        MyNode cur = null,pre = null;
        s.push(root);
        while(!s.isEmpty()){
            cur = s.peek();
            if((cur.left==null&&cur.right==null)
                    ||(pre!=null && (pre==cur.left||pre==cur.right))){
                System.out.println(cur);
                s.pop();
                pre = cur;
            } else {
                if(cur.right!=null) s.push(cur.right);
                if(cur.left!=null) s.push(cur.left);
            }
        }
    }

}

class MyNode{
   int key;
   MyNode left;
   MyNode right;

    public MyNode(int key) {
        this.key = key;
    }

    public MyNode(int key, MyNode left, MyNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "MyNode{" +
                "key=" + key +
                '}';
    }
}
