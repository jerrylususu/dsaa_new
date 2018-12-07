package lab5;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class lab5g2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] sarr = new String[n];
        for(int i=0;i<n;i++){
            sarr[i] = in.next();
        }
        int[][] nexts = new int[n][];
        int minlen = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            nexts[i] = buildNext(sarr[i]);
            if(sarr[i].length()<minlen){
                minlen=sarr[i].length();
            }
//            System.out.println(Arrays.toString(nexts[i]));
        }

        String op = in.next();
        char[] t = new char[op.length()];
        int pos = -1;
        Stack<Integer> status = new Stack<>();
        status.push(minlen);
        System.out.println(minlen);

        for(int i=0;i<op.length();i++){
            if(op.charAt(i)=='-'){
                // backspace
                if(pos!=-1){
                    pos--;
                    status.pop();
//                    System.out.println(status.peek());
                } else {
//                    System.out.println(minlen);
                }
            } else {
                // not backspace
                pos++;
                t[pos]=op.charAt(i);
                int curmin = Integer.MAX_VALUE;
                for(int j=0;j<n;j++){
                    int c1=pos-sarr[j].length()>1?pos-sarr[j].length()-1:0,c2=0;
                    char[] p = sarr[j].toCharArray();
                    int[] next = nexts[j];
                    while(c1<=pos){
                        if(c2==-1||t[c1]==p[c2]){
                            c1++;c2++;
                        } else {
                            c2=next[c2];
                        }
                        if(c2==p.length){
                            break;
                        }
                    }
                    int remain = p.length-c2;
                    if(remain<curmin){
                        curmin=remain;
                    }
                }
                status.push(curmin);
//                System.out.println(status.peek());
            }

            if(status.size()!=0){
                System.out.println(status.peek());
            } else {
                System.out.println(minlen);
            }

        }

    }

    public static int[] buildNext(String s1){
        char[] p = s1.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0, k = -1;

        // build next
        while (j < p.length-1) {
            if (k == -1 || p[j] == p[k]) {
//                next[j + 1] = k + 1; // next[j+1] = next[j]+1

                // optimized version
                if(p[j+1]==p[k+1]){
                    next[j+1]=next[k+1];
                }else{
                    next[j+1]=k+1;
                }
                j++;k++;
            } else {
                k = next[k];
            }
        }
        return next;
    }


}
