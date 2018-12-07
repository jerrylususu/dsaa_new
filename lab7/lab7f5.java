//package lab7;
//
//import java.util.Scanner;
//
//// working AVL
//// problem fixed: duplicate salary
//
//public class lab7f5 {
//    public static void main(String[] args) {
////        test2();
////    test1();
//        m();
//    }
//
//
//    public static void m() {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while (totalnum-->0) {
//            int opn = in.nextInt();
//            int min = in.nextInt();
//            int std = 0;
//            int cnt = 0;
//            MyAVL m = new MyAVL();
//            for (int i = 0; i < opn; i++) {
//                char op = in.next().charAt(0);
//                if (op == 'I') {
//                    int n = in.nextInt();
//                    if (n < min) {
//                        // already lower
//                    } else {
//                        m.insert(n - min + std);
//                    }
//                } else if (op == 'S') {
//                    std += in.nextInt();
//                    MyAVLNode tmp = null;
//                    while ((tmp = m.findMin()) != null && tmp.val < std) {
//                        m.remove(tmp.val);
//                        cnt++;
//                    }
//                } else if (op == 'A') {
//                    std -= in.nextInt();
//                } else if (op == 'Q') {
//                    int k = m.size - in.nextInt() + 1;
//                    if (k <= 0) {
//                        System.out.println(-1);
//                    } else {
//                        MyAVLNode q = m.findKth(k);
//                        if (q == null) {
//                            System.out.println(-1);
//                        } else {
//                            System.out.println(q.val - std + min);
//                        }
//                    }
//
//                }
//            }
//            System.out.println(cnt);
//        }
//    }
//
//    public static void test2(){
//        int arr[]= {3,2,1,4,5,6,7,16,15,14,13,12,11,10,8,9};
//        MyAVL m = new MyAVL();
//        for(int i:arr){
//            m.insert(i,m.root);
//            System.out.println(m.size+" "+m.root+" "+m.getHeight());
//        }
////        m.remove(8);
//        System.out.println("fin1");
////        m.remove(7);
////        for (int i = arr.length-1; i >=0 ; i--) {
////            m.remove(arr[i]);
////            System.out.println(m.size+" "+m.root+" "+m.getHeight());
////        }
//        for (int i = 1; i <= 16; i++) {
//            System.out.println(m.findKth(i));
//        }
//    }
//
//    public static void test1(){
//        MyAVL m = new MyAVL();
//        m.insert(8,m.root);
//        m.insert(7,m.root);
//        m.insert(1,m.root);
//        m.insert(4,m.root);
//        m.insert(10,m.root);
//        System.out.println(m.findMax(m.root));
//        System.out.println(m.findMin(m.root));
//        System.out.println(m.size);
//        m.remove(7,m.root);
//        System.out.println(m.findMax(m.root));
//        m.remove(1,m.root);
//        System.out.println(m.size);
//        System.out.println(m.findMin(m.root));
//        m.remove(8,m.root);
//        m.remove(10,m.root);
//        System.out.println(m.findMin(m.root));
//        m.remove(4,m.root);
//        System.out.println(m.size);
//        System.out.println(m.root);
//        System.out.println(m.findMin(m.root));
//        m.insert(3,m.root);
//        System.out.println(m.findMax(m.root));
//        System.out.println(m.findMin(m.root));
//    }
//}
//
//class MyAVL{
//    MyAVLNode root;
//    int size;
//
//    public MyAVL(){
//        this.size=0;
//    }
//
//    public boolean contains(int q, MyAVLNode node){
//        if(node==null){
//            return false;
//        }
//        if(q==node.val){
//            return true;
//        } else if (q>node.val){
//            return contains(q,node.r);
//        } else {
//            return contains(q,node.l);
//        }
//    }
//
//    public MyAVLNode insert(int q, MyAVLNode node) {
//        if (node == null) {
//            MyAVLNode n = new MyAVLNode(size++, q);
//            if (root == null) {
//                root = n;
//            }
//            node = n;
//            node.height = Math.max(getHeight(node.l),getHeight(node.r))+node.count;
//            node.size = getSize(node.l)+getSize(node.r)+node.count;
//            return n;
//        }
//
//        if (q == node.val) {
//            // already exist
//            node.count++;
////            node.size++;
//            size++;
//        } else {
//            boolean rootcheck = (node==root);
//            if (q > node.val) {
//                node.r = insert(q, node.r);
//                int hDiff = getHeight(node.r) - getHeight(node.l);
//                if (Math.abs(hDiff) >= 2) {
//                    // need to rotate
//                    if(getHeight(node.r.r)>=getHeight(node.r.l)){
//                        // RR
//                        node = RR1Rotate(node);
//                    } else {
//                        // RL
//                        node = RL2Rotate(node);
//                    }
//                }
//            } else {
//                node.l = insert(q, node.l);
//                int hDiff = getHeight(node.l) - getHeight(node.r);
//                if (Math.abs(hDiff) >= 2) {
//                    // need to rotate
//                    if(getHeight(node.l.l)>=getHeight(node.l.r)){
//                        // LL
//                        node = LL1Rotate(node);
//                    } else {
//                        // LR
//                        node = LR2Rotate(node);
//                    }
//                }
//            }
//            if(rootcheck){
//                root = node;
//            }
//        }
//
//
//        node.height = Math.max(getHeight(node.l),getHeight(node.r))+1;
//        node.size = getSize(node.l)+getSize(node.r)+node.count;
//
//        return node;
//
//    }
//
//    public MyAVLNode findMax(MyAVLNode node){
//        if(node!=null){
//            while(node.r!=null){
//                node = node.r;
//            }
//            return node;
//        }
//        return null;
//    }
//
//    public MyAVLNode findMin(MyAVLNode node){
//        if(node!=null){
//            while(node.l!=null){
//                node = node.l;
//            }
//            return node;
//        }
//        return null;
//    }
//
//    public MyAVLNode findKth(int k, MyAVLNode node, int cnt){
//        if(node==null){
//            return null;
//        }
//
//        if(getSize(node.l)+cnt+1>k){
//            // go to left
//            return findKth(k,node.l,cnt);
//        } else if (getSize(node.l)+cnt+node.count<k){
//            // go to right
//            return findKth(k,node.r,cnt+getSize(node.l)+node.count);
//        } else {
//            // this is the node
//            return node;
//        }
//    }
//
//    public MyAVLNode findKth(int k){
//        return findKth(k,root,0);
//    }
//
//    public MyAVLNode remove(int q, MyAVLNode node){
//        if(node==null){
//            return null;
//        }
//
//        boolean rootcheck = (root==node);
//
//        if(q>node.val){
//            node.r = remove(q,node.r);
//
//            // maintain height
//            int hDiff = getHeight(node.l) - getHeight(node.r);
//            if (Math.abs(hDiff) >= 2) {
//                // need to rotate
//                if(getHeight(node.l.l)>=getHeight(node.l.r)){
//                    // LL
//                    node = LL1Rotate(node);
//                } else {
//                    // LR
//                    node = LR2Rotate(node);
//                }
//            }
//        } else if (q<node.val){
//            node.l = remove(q,node.l);
//
//            // maintain height
//            int hDiff = getHeight(node.r) - getHeight(node.l);
//            if (Math.abs(hDiff) >= 2) {
//                // need to rotate
//                if(getHeight(node.r.r)>=getHeight(node.r.l)){
//                    // RR
//                    node = RR1Rotate(node);
//                } else {
//                    // RL
//                    node = RL2Rotate(node);
//                }
//            }
//        } else if (node.count!=1) {
//            node.count--;
////            node.size--;
//            size--;
//        } else if (node.r!=null && node.l!=null){
//            // has 2 sons
//
//            // best performance of delete
//            // choose the higher one to delete
//            if(getHeight(node.l)>getHeight(node.r)){
//                MyAVLNode tmp = findMax(node.l);
//                node.val = tmp.val;
//                node.l = remove(tmp.val,node.l);
////                size--;
//            } else {
//                MyAVLNode tmp = findMin(node.r);
//                node.val = tmp.val;
//                node.r = remove(tmp.val,node.r);
////                size--;
//            }
//
////            if(rootcheck){
////                root=node;
////            }
//
//        } else {
////            boolean rootcheck = root==node;
//            node = (node.r==null)?node.l:node.r;
////            if(rootcheck){
////                root=node;
////            }
//            size--;
//        }
//
//        if(node!=null){
//            node.height = Math.max(getHeight(node.l),getHeight(node.r))+node.count;
//            node.size = getSize(node.l)+getSize(node.r)+node.count;
//
//        }
//
//        if(rootcheck){
//            root=node;
//        }
//
//        return node;
//    }
//
//    public int getHeight(MyAVLNode n){
//        if(n==null) return 0;
//        int l=(n.l==null)?0:n.l.height, r = (n.r==null)?0:n.r.height;
////        if(l==0&&r==0) n.height=0;
////        else n.height = Math.max(l,r)+1;
//        n.height = Math.max(l,r)+1;
//        return n.height;
//    }
//
//    public int getSize(MyAVLNode n){
//        if(n==null) return 0;
////        n.size = getSize(n.l)+getSize(n.r)+1;
//        return n.size;
//    }
//
//    public MyAVLNode LL1Rotate(MyAVLNode k2){
//        MyAVLNode k1 = k2.l;
//
//        // change branch
//        k2.l = k1.r;
//        k1.r = k2;
//
//        // adjust height
//        k2.height = Math.max(getHeight(k2.l),getHeight(k2.r))+1;
//        k1.height = Math.max(getHeight(k1.l),getHeight(k1.r))+1;
//
//        // adjust size
//        k2.size = getSize(k2.l)+getSize(k2.r)+k2.count;
//        k1.size = getSize(k1.l)+getSize(k1.r)+k1.count;
//
//        // return the connection
//        return k1;
//    }
//
//    public MyAVLNode RR1Rotate(MyAVLNode k2){
//        MyAVLNode k1 = k2.r;
//
//        // change branch
//        k2.r = k1.l;
//        k1.l = k2;
//
//        // update height
//        k2.height = Math.max(getHeight(k2.l),getHeight(k2.r))+1;
//        k1.height = Math.max(getHeight(k1.l),getHeight(k1.r))+1;
//
//        // adjust size
//        k2.size = getSize(k2.l)+getSize(k2.r)+k2.count;
//        k1.size = getSize(k1.l)+getSize(k1.r)+k1.count;
//
//        // return the connection
//        return k1;
//    }
//
//    public MyAVLNode RL2Rotate(MyAVLNode k1){
//        MyAVLNode k3 = k1.r, k2 = k3.l;
//
//        // first rotation
//        k3.l = k2.r;
//        k1.r = k2;
//        k2.r = k3;
//
//        // second rotation
//        k1.r = k2.l;
//        k2.l = k1;
//
//        // update height
//
//        k1.height = Math.max(getHeight(k1.l),getHeight(k1.r))+1;
//        k3.height = Math.max(getHeight(k3.l),getHeight(k3.r))+1;
//        k2.height = Math.max(getHeight(k2.l),getHeight(k2.r))+1;
//
//        // adjust size
//        k1.size = getSize(k1.l)+getSize(k1.r)+k1.count;
//        k3.size = getSize(k3.l)+getSize(k3.r)+k3.count;
//        k2.size = getSize(k2.l)+getSize(k2.r)+k2.count;
//
//
//        return k2;
//    }
//
//    public MyAVLNode LR2Rotate(MyAVLNode k1){
//        MyAVLNode k3 = k1.l, k2 = k3.r;
//
//        // first rotation
//        k3.r = k2.l;
//        k1.l = k2;
//        k2.l = k3;
//
//        // second rotation
//        k1.l = k2.r;
//        k2.r = k1;
//
//        // adjust size
//        k1.size = getSize(k1.l)+getSize(k1.r)+k1.count;
//        k3.size = getSize(k3.l)+getSize(k3.r)+k3.count;
//        k2.size = getSize(k2.l)+getSize(k2.r)+k2.count;
//
//        return k2;
//    }
//
//    public MyAVLNode insert(int q){
//        return insert(q,root);
//    }
//
//    public MyAVLNode remove(int q){
//        return remove(q,root);
//    }
//
//    public MyAVLNode findMax(){
//        return findMax(root);
//    }
//
//    public MyAVLNode findMin(){
//        return findMin(root);
//    }
//
//    public int getHeight(){
//        return getHeight(root);
//    }
//
//}
//
//class MyAVLNode{
//    int val;
//    int id;
//    int height;
//    int size;
//    int count;
//    MyAVLNode p;
//    MyAVLNode l;
//    MyAVLNode r;
//
//    public MyAVLNode(int id,int val){
//        this.id = id;
//        this.val = val;
//        this.height = 0;
//        this.size = 0;
//        this.count = 1;
//    }
//
//    @Override
//    public String toString() {
//        return "MyAVLNode{" +
//                "val=" + val +
//                ", id=" + id +
//                ", height=" + height +
//                ", size=" + size +
//                ", count=" + count +
//                '}';
//    }
//}
