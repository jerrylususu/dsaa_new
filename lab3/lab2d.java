package lab3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class lab2d {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();

        while(total-->0){
            int s = in.nextInt();
            PolyNode[] arr = new PolyNode[s];
            for(int i=0;i<s;i++){
                arr[i] = new PolyNode(in.nextInt(),in.nextInt());
            }
            Arrays.sort(arr, new Comparator<PolyNode>() {
                @Override
                public int compare(PolyNode o1, PolyNode o2) {
                    return o1.exp-o2.exp;
                }
            });
            PolyLinkedList ll = new PolyLinkedList();
            ll.head=arr[0];
            for(int i=0;i<s-1;i++){
                arr[i].next=arr[i+1];
            }
            ll.tail=arr[s-1];

            PolyLinkedList r = new PolyLinkedList(0,-1100);
            PolyNode c1 = ll.head, c2=r.head;
            while (c1!=null){
                if(c1.exp==0){

                } else if (c1.exp-1==c2.exp){
                    c2.cof+=c1.exp*c1.cof;
                } else if (c1.exp-1>c2.exp){
                    c2.next = new PolyNode(c1.exp*c1.cof,c1.exp-1);
                    c2 = c2.next;
                    r.tail=c2;
                }
                c1=c1.next;
            }

            //r.getall();

            // output
            StringBuilder sb = new StringBuilder();
            PolyNode cur = r.head;
            while (cur!=null) {
                if (cur.cof != 0) {
                    if (cur.exp == 0) {
                        if (cur.cof == 1) {
                            sb.append("1+");
                        } else if (cur.cof <0) {
                            if (sb.length() != 0 && sb.charAt(sb.length() - 1) == '+') {
                                sb.deleteCharAt(sb.length() - 1);
                            }
                            sb.append(cur.cof+"+");
                        } else {
                            sb.append(cur.cof + "+");
                        }
                    } else {
                        if (cur.cof == 1) {
                            sb.append("x");
                        } else if (cur.cof <0) {
                            if (sb.length() != 0 && sb.charAt(sb.length() - 1) == '+') {
                                sb.deleteCharAt(sb.length() - 1);
                            }
                            if(cur.cof==-1){
                                sb.append("-x");
                            } else {
                                sb.append(cur.cof+"x");
                            }

                        } else {
                            sb.append(cur.cof + "x");
                        }
                        if (cur.exp == 1) {
                            sb.append("+");
                        } else {
                            sb.append("^" + cur.exp + "+");
                        }
                    }

                }
                cur=cur.next;
            }
            if(sb.length()!=0&&sb.charAt(sb.length()-1)=='+'){
                sb.deleteCharAt(sb.length()-1);
            }
            if(sb.length()==0){
                sb.append(0);
            }
            System.out.println(sb.toString());



        }
    }
}

class PolyLinkedList{
    public int size;
    public PolyNode head;
    public PolyNode tail;

    public PolyLinkedList(){
        this.head=null;
        this.tail=null;
        this.size=0;
    }

    public PolyLinkedList(int cof, int exp){
        this.head = new PolyNode(cof,exp);
        this.size = 0;
        this.tail = this.head;
    }

    public void add(int cof, int exp){
        this.size++;
        this.tail.next = new PolyNode(cof,exp);
        this.tail = this.tail.next;
    }

    public void getall(){
        PolyNode cur = this.head;
        while(cur!=null){
            System.out.println(cur);
            cur=cur.next;
        }
    }

}

class PolyNode{
    public int cof;
    public int exp;
    public PolyNode next=null;

    public PolyNode(int cof, int exp){
        this.cof=cof;
        this.exp=exp;
    }

    @Override
    public String toString() {
        return "PolyNode{" +
                "cof=" + cof +
                ", exp=" + exp +
                '}';
    }
}
