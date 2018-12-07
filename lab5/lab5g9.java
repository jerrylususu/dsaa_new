package lab5;

import java.util.Scanner;
import java.util.Stack;

public class lab5g9 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        char[][] sarr = new char[n][];
        int allMinLen = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            sarr[i] = in.next().toCharArray();
            if(allMinLen>sarr[i].length){
                allMinLen = sarr[i].length;
            }
        }
        String op = in.next();
        char[] ops = op.toCharArray();

        int[][] nexts = new int[n][];
        for (int i = 0; i < n; i++) {
            nexts[i] = buildNext(String.valueOf(sarr[i]));
        }

        Stack<Integer> stack = new Stack<>();
        int[][] newnext = new int[n][];
        char[][] ps = new char[n][];
        int[] cursors = new int[n];
        for (int i = 0; i < n; i++) {
            newnext[i] =new int[sarr[i].length+op.length()];
            ps[i]=new char[sarr[i].length+op.length()];
            System.arraycopy(nexts[i],0,newnext[i],0,nexts[i].length);
            System.arraycopy(sarr[i],0,ps[i],0,sarr[i].length);
            cursors[i] = nexts[i].length;
        }
        stack.push(allMinLen);
        System.out.println(stack.peek());
        int cur = -1, size = 0;
        char[] t = new char[ops.length];
        for(int i=0;i<ops.length;i++){
            if(ops[i]=='-'){
                if(size>0){
                    cur--;
                    size--;
                    stack.pop();
                    for(int j=0;j<n;j++){
                        cursors[j]--;
                    }
                }
            } else {
                cur++;
                size++;
                t[cur]=ops[i];
                int localmin = Integer.MAX_VALUE;
                for(int j=0;j<n;j++){
                    // first put then add
                    int c2 = cursors[j];
                    ps[j][c2+1]=t[cur];
                    while(c2!=-1&&t[cur]!=ps[j][c2]){
                        c2=nexts[j][c2];
                    }
                    nexts[j][cursors[j]]=c2+1;



                }
                stack.push(localmin);
            }
            if(stack.isEmpty()){
                System.out.println(allMinLen);
            } else {
                System.out.println(stack.peek());
            }

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
                j++;k++;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
