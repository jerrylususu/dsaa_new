package lab3;

import java.util.Scanner;

public class lab2e4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();

        while(total-->0){
            int n = in.nextInt();
            int m = in.nextInt();
            int e = in.nextInt();

            int size = 0;
            e4node head = new e4node((0==e));
            e4node cur = head;
            size++;
            for(int i=1;i<n;i++){
                cur.next = new e4node((i==e));
                cur = cur.next;
                size++;
            }
            cur.next = head;
            cur = cur.next;

            boolean ok = true;

            e4node last=null;
            while(size>1){
                for(int i=0;i<m;i++){
                    last = cur;
                    cur = cur.next;
                }
                if(cur.isEarth){
                    ok=false;
                    break;
                } else {
                    last.next = cur.next;
                    cur=cur.next;
                    size--;
                }
            }

            //System.out.println(ok?"Yes":"No");
            System.out.println(ok&&cur.isEarth?"Yes":"No");
        }

    }
}

class e4node{
    boolean isEarth;
    e4node next;

    public e4node(boolean b){
        this.isEarth=b;
    }
}
