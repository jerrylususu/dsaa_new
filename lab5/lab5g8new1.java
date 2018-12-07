package lab5;

import java.io.*;
import java.util.Stack;

public final class lab5g8new1 {
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(in.readLine());
        char[][] sarr = new char[n][];
        int allMinLen = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            sarr[i] = in.readLine().toCharArray();
            if (allMinLen > sarr[i].length) {
                allMinLen = sarr[i].length;
            }
        }
        String op = in.readLine();
        char[] ops = op.toCharArray();

        int[][] nexts = new int[n][];
        for (int i = 0; i < n; i++) {
            nexts[i] = buildNext(String.valueOf(sarr[i]));
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(allMinLen);
        sb.append(stack.peek()).append("\n");
        int cur = -1, size = 0;
        char[] t = new char[ops.length];
        int rec=0;
        for (int i = 0; i < ops.length; i++) {
            if (ops[i] == '-') {
                if (size > 0) {
                    cur--;
                    size--;
                    stack.pop();
                }
            } else {
                cur++;
                size++;
                t[cur] = ops[i];
                int localmin = Integer.MAX_VALUE;
                for (int j = 0; j < n; j++) {
                    int addlen = size - sarr[j].length > 0 ? sarr[j].length : size;
                    int startpoint = size - sarr[j].length > 0 ? size - sarr[j].length : 0;
                    int[] arr = new int[2 * sarr[j].length + 1];
                    System.arraycopy(nexts[j], 0, arr, 0, nexts[j].length);
                    int c1 = nexts[j].length - 1, c2 = arr[c1];
                    char[] p = new char[2 * sarr[j].length];
                    System.arraycopy(sarr[j], 0, p, 0, sarr[j].length);
                    System.arraycopy(t, startpoint, p, 2 * sarr[j].length - addlen, addlen);
                    while (c1 < arr.length - 1) {
                        if (c2 == -1 || p[c1] == p[c2]) {
                            arr[c1 + 1] = c2 + 1;
                            c1++;
                            c2++;
                        } else {
                            c2 = arr[c2];
                        }
                    }
//                    System.out.println(Arrays.toString(arr));
//                    System.out.println(p.length);
//                    System.out.println(arr[arr.length-1]);
//                    System.out.println(s-arr[arr.length-1]);
                    if (sarr[j].length - arr[arr.length - 1] < localmin) {
                        localmin = sarr[j].length - arr[arr.length - 1];
                    }
                    if (localmin < 0) {
                        localmin = 0;
                    }
                }
                stack.push(localmin);
            }
            if (stack.isEmpty()) {
                sb.append(allMinLen);
            } else {
                sb.append(stack.peek());
            }
            sb.append("\n");
            rec=i;

        }

        throw new RuntimeException(op.substring(30000,60000)+"\n"+ops.length+" "+rec+" "+sb.length()+" "+String.valueOf(sb.toString().hashCode()));
        //System.out.print(sb);

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
