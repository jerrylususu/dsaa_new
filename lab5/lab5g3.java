package lab5;


import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class lab5g3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] sarr = new String[n];
        int allMinLen = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            sarr[i] = in.next();
            if(allMinLen>sarr[i].length()){
                allMinLen = sarr[i].length();
            }
        }
        String op = in.next();
        int[][] nexts = new int[n][];
        for (int i = 0; i <n; i++) {
            nexts[i] = buildNext(sarr[i]);
            nexts[i][sarr[i].length()] = buildNext2(sarr[i])[sarr[i].length()];
            nexts[i][0] = 0;
            System.out.println(Arrays.toString(nexts[i]));
        }



        // do the calculation
        Stack<Integer> stack = new Stack<>();
        Stack<Integer>[] ss = new Stack[n];
        for(int i=0;i<n;i++){
            ss[i] = new Stack<>();
            ss[i].push(0);
        }
        stack.push(allMinLen);
        System.out.println(stack.peek());
        int[] cs = new int[n];
        char[] t = new char[op.length()];
        int cur=-1;

        for(int i=0;i<op.length();i++){
            char c = op.charAt(i);
            if(c=='-'){
                if(cur!=-1){
                    cur--;
                    for(int j=0;j<=cur;j++){
                        System.out.print(t[j]);
                    }
                    for(int j=0;j<n;j++){
                        ss[j].pop();
                    }
                    System.out.println();
                    stack.pop();
                }
            } else {
                // is a real char
                cur++;
                t[cur]=c;
                boolean oneall = false;
                for(int j=0;j<=cur;j++){
                    System.out.print(t[j]);
                }
                System.out.println();
                cs = new int[n];
                for(int j=0;j<n;j++){
                    cs[j] = ss[j].peek();
                }
                System.out.println("before "+Arrays.toString(cs));
                for(int j=0;j<n;j++){
                    if(cs[j]==sarr[j].length()){
                        System.out.println("full "+Arrays.toString(cs));
                        oneall=true;
                        cs[j] = nexts[j][cs[j]];
                        continue;
                    }

                    if(cs[j]==-1||t[cur]==sarr[j].charAt(cs[j])){
                        cs[j]++;
                    } else {
                        cs[j] = nexts[j][cs[j]];
                    }

                    if(cs[j]==sarr[j].length()){
                        System.out.println("full "+Arrays.toString(cs));
                        oneall=true;
                        cs[j] = nexts[j][cs[j]];
                    }
                }
                System.out.println("middle "+Arrays.toString(cs));
                int localmin = Integer.MAX_VALUE;
                for(int j=0;j<n;j++){
                    if(cs[j]==-1){
                        cs[j]=0;
                    }
                    if(cs[j]<sarr.length&&sarr[j].charAt(cs[j])==c){
                        cs[j]++;
                    }
                    if(sarr[j].length()-cs[j]<localmin){
                        localmin=sarr[j].length()-cs[j];
                    }
                }
                System.out.println("final "+Arrays.toString(cs));
                for(int j=0;j<n;j++){
                    ss[j].push(cs[j]);
                }
                if(oneall){
                    localmin=0;
                }
                stack.push(localmin);
            }

            if(stack.isEmpty()||cur==-1){
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

    public static int[] buildNext2(String s1){
        char[] p = s1.toCharArray();
        int[] next = new int[p.length+1];
        next[0] = -1;
        int j = 0, k = -1;

        // build next
        while (j < p.length) {
            if (k == -1 || p[j] == p[k]) {
                next[j + 1] = k + 1; // next[j+1] = next[j]+1

                // optimized version
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
