package lab5;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class lab5g8 {
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
                }
            } else {
                cur++;
                size++;
                t[cur]=ops[i];
                int localmin = Integer.MAX_VALUE;
                for(int j=0;j<n;j++){
                    int addlen = size-sarr[j].length>0?sarr[j].length:size;
                    int startpoint = size-sarr[j].length>0?size-sarr[j].length:0;
                    int[] arr = new int[2*sarr[j].length+2];
                    System.arraycopy(nexts[j],0,arr,0,nexts[j].length);
                    int c1 = nexts[j].length-1, c2 = arr[c1];
                    char[] p = new char[2*sarr[j].length+1];
                    System.arraycopy(sarr[j],0,p,0,sarr[j].length);
                    System.arraycopy(t,startpoint,p,2*sarr[j].length-addlen+1,addlen);
                    while(c1<arr.length-1){
                        if(c2==-1||p[c1]==p[c2]){
                            arr[c1+1]=c2+1;
                            c1++;c2++;
                        } else {
                            c2=arr[c2];
                        }
                    }
//                    System.out.println(Arrays.toString(arr));
//                    System.out.println(p.length);
//                    System.out.println(arr[arr.length-1]);
//                    System.out.println(s-arr[arr.length-1]);
                    if(sarr[j].length-arr[arr.length-1]<localmin){
                        localmin=sarr[j].length-arr[arr.length-1];
                    }
                    if(localmin<0){
                        localmin=0;
                    }
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
