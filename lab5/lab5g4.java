package lab5;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class lab5g4 {
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
        int[][] onexts = new int[n][];
        for (int i = 0; i <n; i++) {
            nexts[i] = buildNext(sarr[i]);
            onexts[i]=buildNext2(sarr[i]);
            nexts[i][sarr[i].length()] = onexts[i][sarr[i].length()];
            nexts[i][0] = 0;
            System.out.println(Arrays.toString(onexts[i]));
        }

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
                    for (int j = 0; j < n; j++) {
                        ss[j].pop();
                    }
                    stack.pop();
                    cs = new int[n];
                    for(int j=0;j<n;j++){
                        cs[j] = ss[j].peek();
                    }
                    System.out.println(Arrays.toString(cs));
                }

            } else {
                cur++;
                t[cur]=c;
                cs = new int[n];
                for(int j=0;j<n;j++){
                    cs[j] = ss[j].peek();
                }
                boolean allyes=false;
                System.out.println("before "+Arrays.toString(cs));
                for(int j=0;j<n;j++){
                    if(cs[j]==sarr[j].length()) {
                        cs[j] = onexts[j][cs[j]]+1;
//                        allyes = true;
                    }
                }
                System.out.println("pre "+Arrays.toString(cs));
                for(int j=0;j<n;j++){
                    // for each
                    if(cs[j]==sarr[j].length()) {
                        cs[j] = onexts[j][cs[j]]+1;
                        if(cs[j]==-1){
                            cs[j]=0;
                        }
                        if(cs[j]<sarr[j].length()&&c==sarr[j].charAt(cs[j])){
                            cs[j]++;
                        }
                        if(cs[j]==sarr[j].length()){
                            allyes=true;
                            cs[j] = onexts[j][cs[j]]+1;
                            continue;
                        }
                        allyes=true;
                        continue;
                    } else {
                        if(cs[j]==-1||c==sarr[j].charAt(cs[j])){
                            cs[j]++;
                        } else {
                            cs[j] = onexts[j][cs[j]]+1;
                            if(cs[j]==-1){
                                cs[j]=0;
                            }
                            if(cs[j]<sarr[j].length()&&c==sarr[j].charAt(cs[j])){
                                cs[j]++;
                            }
                            if(cs[j]==sarr[j].length()){
                                allyes=true;
                                cs[j] = onexts[j][cs[j]]+1;
                                continue;
                            }
                        }

                    }
                }

                int localmin = Integer.MAX_VALUE;
                for(int j=0;j<n;j++){
                    if(cs[j]==-1){
                        cs[j]=0;
                    }
//                    if(cs[j]<sarr.length&&sarr[j].charAt(cs[j])==c){
//                        cs[j]++;
//                    }
                    if(sarr[j].length()-cs[j]<localmin){
                        localmin=sarr[j].length()-cs[j];
                    }
                }
                System.out.println("final "+Arrays.toString(cs));
                for(int j=0;j<n;j++){
                    ss[j].push(cs[j]);
                }
                if(allyes){
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
                j++;k++;
            } else {
                k = next[k];
            }
        }
        return next;
    }

}
