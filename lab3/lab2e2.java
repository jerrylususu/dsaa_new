//package lab2;
//
//import java.util.Scanner;
//
//public class lab2e2 {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int total = in.nextInt();
//
//        while(total-->0){
//            int n = in.nextInt();
//            int m = in.nextInt();
//            int e = in.nextInt();
//
//            ELinkedList ll = new ELinkedList();
//            ll.head = new ENode(0,(e==0));
//            ll.size++;
//            ENode cur = ll.head;
//            for(int i=1;i<n;i++){
//                cur.next = new ENode(i,(e==i));
//                cur = cur.next;
//                ll.size++;
//            }
//            cur.next = ll.head;
//            cur = ll.head;
//
//            boolean ok = true;
//
//            ENode tmp = null;
//
//            while(ll.size>1){
//                for(int i=0;i<m;i++){
//                    tmp = cur;
//                    cur = cur.next;
//                }
//                //System.out.println(cur.n);
//                if(cur.isEarth){
//                    ok=false;
//                    break;
//                }
//                tmp.next = cur.next;
//                //cur.prev = null;`
//                cur = cur.next;
//                ll.size--;
//            }
//
//            System.out.println(ok?"Yes":"No");
//        }
//
//    }
//}
//
//class ELinkedList{
//    int size;
//    ENode head;
//
//    public ELinkedList(){
//        this.size=0;
//    }
//
//}
//
//class ENode{
//    int n;
//    boolean isEarth;
//    ENode next;
//
//    public ENode(int n, boolean isEarth){
//        this.n = n;
//        this.isEarth = isEarth;
//    }
//}