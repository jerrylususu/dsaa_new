package lab7;

import java.util.Scanner;

// pre-optimize with input checking
// pre-optimize: only calculate given step
// AC


public class lab7g6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while (totalnum-- > 0) {
            int n = in.nextInt();
            int k = (int) Math.sqrt(n);
            int[] arr = new int[n];
            int[][] res = new int[n - 1][];
            boolean[] mark = new boolean[n - 1];
            int[] oarr = new int[n];
            int[] xstore = new int[n], ystore = new int[n];
            // entry: start, step (x,y)
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            for (int i = 0; i < n; i++) {
                int x = in.nextInt(), y = in.nextInt();
                if ((n - x) / y + 1 >= k) {
                    // pass check
                    mark[y - 1] = true;
                    xstore[i] = x;
                    ystore[i] = y;
                } else {
                    oarr[i] = -1;
                }
            }
            for (int step = 1; step < n; step++) {
                if (mark[step - 1]) {
                    res[step - 1] = new int[n + 1 - k];
                    for (int type = 0; type < step; type++) {
                        int tmpsize = 0;
                        int cursor = n - 1 - type;
                        MinHeapInt m = new MinHeapInt(k);
                        while (cursor >= 0) {
                            if (m.size() < k) {
                                m.add(arr[cursor]);
                            } else if (m.peek() < arr[cursor]) {
                                m.arr[0] = arr[cursor];
                                m.shiftdown(0);
                            }

                            tmpsize++;
                            if (tmpsize >= k) {
                                res[step - 1][cursor] = m.peek();
                            }
                            cursor -= step;
                        }
                    }
                }

            }
            for (int i = 0; i < n; i++) {
                if (oarr[i] == -1) {
                    System.out.println(-1);
                } else {
                    System.out.println(res[ystore[i] - 1][xstore[i] - 1]);
                }
            }
        }
    }
}
