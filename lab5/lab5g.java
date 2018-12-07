package lab5;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class lab5g {
    public static void main(String[] args) {
        // getting input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] sarr = new String[n];
        for (int i = 0; i < n; i++) {
            sarr[i] = in.next();
        }
        int[][] nexts = new int[n][];
        for (int i = 0; i < n; i++) {
            nexts[i] = buildNext(sarr[i]);
        }
        String ops = in.next();
        char[] op = ops.toCharArray();

        //throw new RuntimeException(Arrays.toString(nexts)+"\n"+ops);

        // do the calc
        int totalop = op.length;
        char[] t = new char[totalop];
        int cur = -1;
        int min = Integer.MAX_VALUE;
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<n;i++){
            if(sarr[i].length()<min){
                min=sarr[i].length();
            }
        }
        stack.push(min);
        int minlen = min;
        min =  Integer.MAX_VALUE;
        System.out.println(stack.peek());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < totalop; i++) {
//            System.out.println(Arrays.toString(t));
            // do operation
            if(op[i]=='-'){
                // backspace
                stack.pop();
            } else {
                // alphabet
                cur++;
                t[cur]=op[i];

                for(int j=0;j<n;j++){
                    // go over current next
                    int c1=cur-sarr[j].length()>=0?cur-sarr[j].length():0,c2=0;
//                    int c1=0,c2=0;
                    while(c1<=cur){
                        if(c2==-1||t[c1]==sarr[j].charAt(c2)){
                            c1++;c2++;
                        } else {
                            c2 = nexts[j][c2];
                        }
                        if(c2==sarr[j].length()){
                            break;
                        }
                    }
//                    System.out.println(sarr[j].length()+" "+c2);
                    int thisleft = sarr[j].length()-c2;
                    if(thisleft<min){
                        min=thisleft;
                    }
                }

                stack.push(min);
                min=Integer.MAX_VALUE;
            }

            if(stack.isEmpty()){
                stack.push(minlen);
            }
            System.out.println(String.valueOf(Arrays.copyOfRange(t,0,cur)));
            System.out.println(stack.peek());
        }


    }

    public static int[] buildNext(String s1){
        char[] p = s1.toCharArray();
        int[] next = new int[p.length+1];
        next[0] = -1;
        int j = 0, k = -1;

        // build next
        while (j < p.length) {
            if (k == -1 || p[j] == p[k]) {
                next[j + 1] = k + 1; // next[j+1] = next[j]+1
//                if(p[j+1]==p[k+1]){
//                    next[j+1]=next[k+1];
//                }else{
//                    next[j+1]=k+1;
//                }
                j++;k++;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
