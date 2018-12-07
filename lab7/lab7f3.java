//package lab7;
//
//public class lab7f3 {
//    public static void main(String[] args) {
//        test2();
//    }
//
//    public static void test2(){
//        int arr[]= {3,2,1,4,5,6,7,16,15,14,13,12,11,10,8,9};
//        MyAVL m = new MyAVL();
//        for(int i:arr){
//            m.insert(i,m.root);
//            System.out.println(m.size+" "+m.root+" "+m.getHeight());
//        }
//        System.out.println("fin");
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
//            return n;
//        }
//
//        if (q == node.val) {
//            // already exist
//        } else {
//            boolean rootcheck = (node==root);
//            if (q > node.val) {
//                node.r = insert(q, node.r);
//                int hDiff = getHeight(node.r) - getHeight(node.l);
//                if (hDiff >= 2) {
//                    // RR1
//                    node = RR1Rotate(node);
//                } else if (hDiff <= -2) {
//                    // RL2
//                    node = RL2Rotate(node);
//                }
//            } else {
//                node.l = insert(q, node.l);
//                int hDiff = getHeight(node.l) - getHeight(node.r);
//                if (hDiff >= 2) {
//                    // LL1
//                    node = LL1Rotate(node);
//                } else if (hDiff <= -2) {
//                    // LR2
//                    node = LR2Rotate(node);
//                }
//            }
//            if(rootcheck){
//                root = node;
//            }
//        }
//
//
//        node.height = Math.max(getHeight(node.l),getHeight(node.r))+1;
//
//
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
//    public MyAVLNode remove(int q, MyAVLNode node){
//        if(node==null){
//            return null;
//        }
//
//        if(q>node.val){
//            node.r = remove(q,node.r);
//
//            // maintain height
//            int hDiff = getHeight(node.l) - getHeight(node.r);
//            if (hDiff >= 2) {
//                // LL1
//                node = LL1Rotate(node);
//            } else if (hDiff <= -2) {
//                // LR2
//                node = LR2Rotate(node);
//            }
//        } else if (q<node.val){
//            node.l = remove(q,node.l);
//
//            // maintain height
//            int hDiff = getHeight(node.r) - getHeight(node.l);
//            if (hDiff >= 2) {
//                // RR1
//                node = RR1Rotate(node);
//            } else if (hDiff <= -2) {
//                // RL2
//                node = RL2Rotate(node);
//            }
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
//        } else {
//            boolean rootcheck = root==node;
//
//            node = (node.r==null)?node.l:node.r;
//
//            if(rootcheck){
//                root=node;
//            }
//            size--;
//        }
//
//        node.height = Math.max(getHeight(node.l),getHeight(node.r))+1;
//
//        return node;
//    }
//
//    public int getHeight(MyAVLNode n){
//        if(n==null) return 0;
////        int l=(n.l==null)?-1:n.l.height, r = (n.r==null)?-1:n.r.height;
////        n.height = Math.max(l,r)+1;
//        return n.height;
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
//        k1.height = Math.max(getHeight(k1.l),getHeight(k1.r))+1;
//        k2.height = Math.max(getHeight(k2.l),getHeight(k2.r))+1;
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
//        k1.height = Math.max(getHeight(k1.l),getHeight(k1.r))+1;
//        k2.height = Math.max(getHeight(k2.l),getHeight(k2.r))+1;
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
//        k1.height = Math.max(getHeight(k1.l),getHeight(k1.r))+1;
//        k2.height = Math.max(getHeight(k2.l),getHeight(k2.r))+1;
//        k3.height = Math.max(getHeight(k3.l),getHeight(k3.r))+1;
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
//        // update height
//        k1.height = Math.max(getHeight(k1.l),getHeight(k1.r))+1;
//        k2.height = Math.max(getHeight(k2.l),getHeight(k2.r))+1;
//        k3.height = Math.max(getHeight(k3.l),getHeight(k3.r))+1;
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
//    MyAVLNode p;
//    MyAVLNode l;
//    MyAVLNode r;
//
//    public MyAVLNode(int id,int val){
//        this.id = id;
//        this.val = val;
//        this.height = 0;
//    }
//
//    @Override
//    public String toString() {
//        return "MyAVLNode{" +
//                "val=" + val +
//                ", id=" + id +
//                ", height=" + height +
//                '}';
//    }
//}