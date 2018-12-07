package lab5;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class lab5g5 {
    public static void main(String[] args) {
        int maxn = 100010;
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
        int[][] nexts = new int[n][maxn];
        int[][] opts = new int[n][maxn];
        int[] ans = new int[n];
        int[] last = new int[n];
//        for(int i=0;i<n;i++){
//            nexts[i]=new int[sarr[i].length+1];
//            opts[i]=new int[sarr[i].length+1];
//        }
        for(int i=0;i<n;i++){
            genNext(sarr[i],nexts[i],opts[i]);
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(allMinLen);
        System.out.println(stack.peek());
        int oplen = op.length();
        char[] ops = op.toCharArray();
        char[] t = new char[oplen];
        int size = 0;
        int index=0, c1=0;

        for(int i=0;i<oplen;i++){
            if(ops[i]=='-'){
                if(size>0){
                    size--;
                }
                if(size<=0){
                    ans[size+1] = allMinLen;
                } else {
                    ans[size+1] = ans[last[n]];
                }
            } else {
                for(int j=0;j<n;j++){
                    c1 = sarr[j].length + size;
                    int cursor = i;
                    int c2 = nexts[j][c1];
                    while(cursor<i+1){
                        if(c2 == -1||sarr[j][c2]==ops[i]){
                            if(sarr[j][c2]!=ops[i]){
                                opts[j][c1] = c2;
                            }
                            cursor++;
                            c2++;
                        } else {
                            c2 = opts[j][cursor];
                        }
                    }
                }
                last[size] = i;
                size++;
                ans[i+1] = allMinLen;
                for(int j=0;j<n;j++){
                    ans[i+1] = Math.min(ans[i+1],sarr[j].length-nexts[j][sarr[j].length+size]-1);
                }
            }
        }
        for (int i = 0; i < oplen; i++) {
            System.out.println(ans[i]);
        }





    }

    public static void genNext(char[] t,int[] next,int[] opt){
        // init
        int i=0, j=-1;
        next[0]=-1;
        opt[0]=-1;

        while (i<t.length){
            if((j==-1||t[i]==t[j])){
                if(j==-1){
                    // at start
                    if(t[i]==t[0]){
                        // same
                        opt[i] = -1;
                    } else {
                        opt[i] = next[i];
                    }
                } else {
                    // not at start
                    if(t[i]==t[next[i]]){
                        // same
                        opt[i] = opt[next[i]];
                    } else {
                        opt[i] = next[i];
                    }
                }
                i++;j++;
                next[i]=j;
            } else {
                j = opt[j];
            }
        }

        System.out.println(String.valueOf(t));
        System.out.println(Arrays.toString(next));
        System.out.println(Arrays.toString(opt));
    }
}
