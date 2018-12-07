//package lab2;
//
//import java.util.Scanner;
//
//public class lab2e {
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
//                cur.next.prev = cur;
//                cur = cur.next;
//                ll.size++;
//            }
//            cur.next = ll.head;
//            ll.head.prev = cur;
//            cur = ll.head;
//
//            boolean ok = true;
//
//            while(ll.size>1){
//                for(int i=0;i<m;i++){
//                    cur = cur.next;
//                }
//                if(cur.isEarth){
//                    ok=false;
//                    break;
//                }
//                cur.next.prev = cur.prev;
//                cur.prev.next = cur.next;
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
//    ENode prev;
//
//    public ENode(int n, boolean isEarth){
//        this.n = n;
//        this.isEarth = isEarth;
//    }
//}