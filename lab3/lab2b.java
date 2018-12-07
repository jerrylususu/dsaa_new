//package lab2;
//
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.Scanner;
//
//public class lab2b{
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int total = in.nextInt();
//
//        while(total-->0){
//            int s1 = in.nextInt();
//            PolyNode[] a1 = new PolyNode[s1];
//            for(int i=0;i<s1;i++){
//                a1[i] = new PolyNode(in.nextInt(),in.nextInt());
//            }
//            Arrays.sort(a1, new Comparator<PolyNode>() {
//                @Override
//                public int compare(PolyNode o1, PolyNode o2) {
//                    return o1.exp-o2.exp;
//                }
//            });
//            PolyLinkedList ll1 = new PolyLinkedList();
//            ll1.head = a1[0];
//            for(int i=0;i<s1-1;i++){
//                a1[i].next = a1[i+1];
//            }
//            ll1.tail = a1[s1-1];
//
//            int s2 = in.nextInt();
//            PolyNode[] a2=new PolyNode[s2];
//            for(int i=0;i<s2;i++){
//                a2[i] = new PolyNode(in.nextInt(),in.nextInt());
//            }
//            Arrays.sort(a2, new Comparator<PolyNode>() {
//                @Override
//                public int compare(PolyNode o1, PolyNode o2) {
//                    return o1.exp-o2.exp;
//                }
//            });
//            PolyLinkedList ll2 = new PolyLinkedList();
//            ll2.head = a2[0];
//            for(int i=0;i<s2-1;i++){
//                a2[i].next=a2[i+1];
//            }
//            ll2.tail = a2[s2-1];
//
//            PolyLinkedList ll3 = new PolyLinkedList(0,0);
//            PolyNode c1=ll1.head, c2=ll2.head, c3=ll3.head;
//            while(c1!=null&&c2!=null){
//                if(c1.exp<c2.exp){
//                    if(c3.exp==c1.exp){
//                        c3.cof+=c1.cof;
//                    } else if (c3.exp<c1.exp){
//                        ll3.add(c1.cof,c1.exp);
//                        c3=ll3.tail;
//                    } else {
//                        //System.out.println(c1+" "+c2+" "+c3);
//                    }
//                    c1 = c1.next;
//                } else if (c1.exp==c2.exp){
//                    if(c3.exp==c1.exp){
//                        c3.cof+=c1.cof+c2.cof;
//                    } else if (c3.exp<c1.exp){
//                        ll3.add(c1.cof+c2.cof,c1.exp);
//                        c3=ll3.tail;
//                    } else {
//                        //System.out.println(c1+" "+c2+" "+c3);
//                    }
//                    c1 = c1.next;
//                    c2 = c2.next;
//                } else {
//                    if(c2.exp==c3.exp){
//                        c3.cof+=c2.cof;
//                    } else if (c3.exp<c2.exp){
//                        ll3.add(c2.cof,c2.exp);
//                        c3=ll3.tail;
//                    } else {
//                        //System.out.println(c1+" "+c2+" "+c3);
//                    }
//                    c2 = c2.next;
//                }
//            }
//
//            if(c1==null&&c2!=null){
//                while(c2!=null){
//                    if(c2.exp==c3.exp){
//                        c3.cof+=c2.cof;
//                    } else if (c3.exp<c2.exp){
//                        ll3.add(c2.cof,c2.exp);
//                        c3=ll3.tail;
//                    } else {
//                        //System.out.println(c1+" "+c2+" "+c3);
//                    }
//                    c2 = c2.next;
//                }
//            } else if (c2==null &&c1!=null){
//                while(c1!=null) {
//                    if (c3.exp == c1.exp) {
//                        c3.cof += c1.cof;
//                    } else if (c3.exp < c1.exp) {
//                        ll3.add(c1.cof, c1.exp);
//                        c3 = ll3.tail;
//                    } else {
//                        //System.out.println(c1 + " " + c2 + " " + c3);
//                    }
//                    c1 = c1.next;
//                }
//            }
//
//            // output
//            StringBuilder sb = new StringBuilder();
//            PolyNode cur = ll3.head;
//            while (cur!=null) {
//                if (cur.cof != 0) {
//                    if (cur.exp == 0) {
//                        if (cur.cof == 1) {
//                            sb.append("1+");
//                        } else if (cur.cof <0) {
//                            if (sb.length() != 0 && sb.charAt(sb.length() - 1) == '+') {
//                                sb.deleteCharAt(sb.length() - 1);
//                            }
//                            sb.append(cur.cof+"+");
//                        } else {
//                            sb.append(cur.cof + "+");
//                        }
//                    } else {
//                        if (cur.cof == 1) {
//                            sb.append("x");
//                        } else if (cur.cof <0) {
//                            if (sb.length() != 0 && sb.charAt(sb.length() - 1) == '+') {
//                                sb.deleteCharAt(sb.length() - 1);
//                            }
//                            if(cur.cof==-1){
//                                sb.append("-x");
//                            } else {
//                                sb.append(cur.cof+"x");
//                            }
//
//                        } else {
//                            sb.append(cur.cof + "x");
//                        }
//                        if (cur.exp == 1) {
//                            sb.append("+");
//                        } else {
//                            sb.append("^" + cur.exp + "+");
//                        }
//                    }
//
//                }
//                cur=cur.next;
//            }
//            if(sb.length()!=0&&sb.charAt(sb.length()-1)=='+'){
//                sb.deleteCharAt(sb.length()-1);
//            }
//            if(sb.length()==0){
//                sb.append(0);
//            }
//            System.out.println(sb.toString());
//
//
//        }
//    }
//}
//
//class PolyLinkedList{
//    public int size;
//    public PolyNode head;
//    public PolyNode tail;
//
//    public PolyLinkedList(){
//        this.head=null;
//        this.tail=null;
//        this.size=0;
//    }
//
//    public PolyLinkedList(int cof, int exp){
//        this.head = new PolyNode(cof,exp);
//        this.size = 0;
//        this.tail = this.head;
//    }
//
//    public void add(int cof, int exp){
//        this.size++;
//        this.tail.next = new PolyNode(cof,exp);
//        this.tail = this.tail.next;
//    }
//
//    public void getall(){
//        PolyNode cur = this.head;
//        while(cur!=null){
//            System.out.println(cur);
//            cur=cur.next;
//        }
//    }
//
//}
//
//class PolyNode{
//    public int cof;
//    public int exp;
//    public PolyNode next=null;
//
//    public PolyNode(int cof, int exp){
//        this.cof=cof;
//        this.exp=exp;
//    }
//
//    @Override
//    public String toString() {
//        return "PolyNode{" +
//                "cof=" + cof +
//                ", exp=" + exp +
//                '}';
//    }
//}
