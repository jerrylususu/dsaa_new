//package lab7;
//
//import java.util.*;
//

// not working BST

//public class lab7f {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while(totalnum-->0){
//            int opn = in.nextInt();
//            int min = in.nextInt();
//            MyBST bt = new MyBST(opn);
//            int cnt = 0;
//            for (int i = 0; i < opn; i++) {
//                String s = in.next();
//                if(s.charAt(0)=='I'){
//                    bt.insert(in.nextInt());
//                } else if (s.charAt(0)=='A'){
//                    int a = in.nextInt();
//                    for(MyBSTNode n:bt.li){
//                        n.val+=a;
//                    }
//                } else if (s.charAt(0)=='S'){
//                    int a = in.nextInt();
//                    Iterator<MyBSTNode> it = bt.li.iterator();
//                    while(it.hasNext()){
//                        MyBSTNode n = it.next();
//                        n.val-=a;
//                        if(n.val<min){
//                            n.todelete=true;
//
//                            bt.delete(bt.root,n.val);
//                            it.remove();
//                            cnt++;
//                        }
//                    }
//                } else if (s.charAt(0)=='Q'){
//                    System.out.println(bt.inorderstack(in.nextInt()-1));
//                }
//            }
//
//        }
//    }
//}
//
//class MyBST {
//    List<MyBSTNode> li;
//    MyBSTNode root;
//    int height;
//
//    public MyBST(int start) {
//        this.li = new ArrayList<>(start);
//        this.root = null;
//        this.height = 0;
//    }
//
//    public boolean insert(int i) {
//        if(this.root == null){
//            this.root = new MyBSTNode(height, i);
//            li.add(this.root);
//            height++;
//            return true;
//        } else {
//            MyBSTNode cur = root;
//            while(cur!=null){
//                if(cur.val==i){
//                    return false;
//                } else if (cur.val<i){
//                    // go to right
//                    if(cur.right==null){
//                        cur.right = new MyBSTNode(height++,i);
//                        li.add(cur.right);
//                        cur.right.parent = cur;
//                        cur.right.isLeft = false;
//                        return true;
//                    } else {
//                        cur = cur.right;
//                    }
//                } else if (cur.val>i){
//                    if(cur.left==null){
//                        cur.left = new MyBSTNode(height++,i);
//                        cur.left.parent = cur;
//                        cur.left.isLeft = true;
//                        li.add(cur.left);
//                        return true;
//                    } else {
//                        cur = cur.left;
//                    }
//                } else {
//                    System.out.println("insert wrong...");
//                    return false;
//                }
//            }
//        }
//        return false;
//    }
//
//    public boolean search(int i){
//        if(this.root==null){
//            return false;
//        } else {
//            MyBSTNode cur = this.root;
//            while(cur!=null){
//                if(cur.val==i){
//                    return true;
//                } else if (cur.val<i){
//                    if(cur.right!=null){
//                        cur=cur.right;
//                    } else {
//                        return false;
//                    }
//                } else if (cur.val>i){
//                    if(cur.left!=null){
//                        cur=cur.left;
//                    } else {
//                        return false;
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//    public MyBSTNode inorderstack(int key){
//        Stack<MyBSTNode> stack = new Stack<>();
//        MyBSTNode cur = this.root;
//        int i=0;
////        stack.push(cur);
//
//        while(stack.height()!=0||cur!=null){
//            while(cur!=null){
//                stack.push(cur);
//                cur = cur.left;
//            }
//
//            if(stack.height()>0){
//                MyBSTNode vis = stack.pop();
////                System.out.println(vis.val);
////                System.out.println(stack.toString());
//                if(key>i){
//                    i++;
//                } else {
//                    return vis;
//                }
//                cur = vis.right;
//            }
//
//        }
//        return null;
//    }
//
//    public MyBSTNode findPre(int i){
//        MyBSTNode cur = this.root;
//        MyBSTNode res = new MyBSTNode(0,Integer.MIN_VALUE);
//        if(cur==null){
//            return res;
//        } else {
//            while(cur!=null){
//                if(cur.val==i){
//                    res = cur;
//                    break;
//                } else {
//                    res = cur;
//                    if(cur.val>i){
//                        cur = cur.left;
//                    } else if (cur.val<i){
//                        cur = cur.right;
//                    }
//                }
//            }
//
//        }
//        return res;
//    }
//
//    public MyBSTNode findMin(MyBSTNode node){
//        MyBSTNode cur = node;
//        MyBSTNode res = new MyBSTNode(0,Integer.MIN_VALUE);
//        if(cur==null){
//            return null;
//        } else {
//            res = cur;
//            while(cur!=null){
//                cur = cur.left;
//            }
//        }
//        return res;
//    }
//
//    public boolean delete(MyBSTNode node, int i){
//        if(node==null){
//            return false;
//        }
//        if(node.val>i){
//            delete(node.left,i);
//        } else if (node.val<i){
//            delete(node.right,i);
//        } else {
//            // really delete here
//            if(node.left==null&&node.right==null){
//                // this is a leaf node
//                if(node.parent.val>node.val){
//                    // this is a left node
//                    li.remove(node);
//                    if(node.parent!=null)
//                    node.parent.left = null;
//                    this.height--;
//                    return true;
//                } else if (node.parent.val<node.val){
//                    // this is a right node
//                    li.remove(node);
//                    if(node.parent!=null)
//                    node.parent.right = null;
//                    this.height--;
//                    return true;
//                } else {
//                    System.out.println("delete wrong: left node error");
//                    return false;
//                }
//            } else {
//                // this is note a leaf node
//                if(node.left==null&&node.right!=null){
//                    // this is a only right node
//                    if(node.isLeft){
//                        if(node.parent!=null)
//                        node.parent.left = node.right;
//                    } else {
//                        if(node.parent!=null)
//                        node.parent.right = node.right;
//                    }
//                    li.remove(node);
//                    this.height--;
//                    return true;
//                } else if (node.left!=null&&node.right==null){
//                    // this is a only left node
//                    if(node.isLeft){
//                        if(node.parent!=null)
//                        node.parent.left = node.left;
//                    } else {
//                        if(node.parent!=null)
//                        node.parent.right = node.left;
//                    }
//                    this.height--;
//                    li.remove(node);
//                    return true;
//                } else {
//                    // this is a double son node
//                    MyBSTNode change = findMin(node.right);
//                    System.out.println(change);
//                    System.out.println(node);
//                    int tmp = node.val;
//                    node.val = change.val;
//                    change.val = tmp;
////                    node = change;
//                    return delete(change,i);
//                }
//
//            }
//        }
//        return false;
//    }
//
//}
//
//class MyBSTNode{
//    int id;
//    int val;
//    MyBSTNode parent;
//    MyBSTNode left;
//    MyBSTNode right;
//    boolean isLeft;
//    boolean todelete;
//
//    public MyBSTNode(int id, int val) {
//        this.id = id;
//        this.val = val;
//    }
//
//    @Override
//    public String toString() {
//        return "MyBSTNode{" +
//                "id=" + id +
//                ", val=" + val +
//                '}';
//    }
//}