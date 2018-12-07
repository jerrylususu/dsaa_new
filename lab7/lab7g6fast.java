package lab7;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

// pre-optimize with input checking & fast input
// AC

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab7g6fast {
    public static void main(String[] args){
        InputStream inputStream = System.in;//new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }
    static class Task {
        public void solve(InputReader in, PrintWriter out) {
            int totalnum = in.nextInt();

            while(totalnum-->0){
                int n = in.nextInt();
                int k = (int)Math.sqrt(n);
                int[] arr = new int[n];
                int[][] res = new int[n-1][];
                boolean[] mark = new boolean[n-1];
                int[] oarr = new int[n];
                int[] xstore = new int[n], ystore = new int[n];
                // entry: start, step (x,y)
                for (int i = 0; i < n; i++) {
                    arr[i] = in.nextInt();
                }
                for (int i = 0; i < n; i++) {
                    int x = in.nextInt(), y = in.nextInt();
                    if((n-x)/y+1>=k){
                        // pass check
                        mark[y-1]=true;
                        xstore[i] = x;
                        ystore[i] = y;
                    } else {
                        oarr[i]=-1;
                    }
                }
                for (int step = 1; step < n; step++) {
                    if(mark[step-1]){
                        res[step-1] = new int[n+1-k];
                        for (int type = 0; type < step; type++) {
                            int tmpsize = 0;
                            int cursor = n-1-type;
                            MinHeapInt m = new MinHeapInt(k);
                            while(cursor>=0){
                                if(m.size()<k){
                                    m.add(arr[cursor]);
                                } else if (m.peek()<arr[cursor]){
                                    m.arr[0] = arr[cursor];
                                    m.shiftdown(0);
                                }

                                tmpsize++;
                                if(tmpsize>=k){
                                    res[step-1][cursor] = m.peek();
                                }
                                cursor-=step;
                            }
                        }
                    }

                }
                for (int i = 0; i < n; i++) {
                    if(oarr[i]==-1){
                        System.out.println(-1);
                    } else {
                        System.out.println(res[ystore[i]-1][xstore[i]-1]);
                    }
                }


            }
        }

    }
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
        public int nextInt() {
            return Integer.parseInt(next());
        }
        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        //         public boolean hasNext() {
//             try {
//                 return reader.ready();
//             } catch(IOException e) {
//                 throw new RuntimeException(e);
//             }
//         }
        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch(IOException e) {
                return false;
            }
        }
        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }
    }
}

interface MyHeapInt{

    public int[] getarr();

    public int size();

    public void swap(int i, int j);

    public void add(int i);


    public int peek();

    public int remove();

    public void shiftdown(int i);
}

class MinHeapInt implements MyHeapInt {
    int[] arr;
    int lastptr; // last ptr for actual storage
    int cursize; // current height of heap

    public MinHeapInt(int size) {
        this.arr = new int[size];
        this.lastptr = -1;
        this.cursize = 0;
    }

    public int[] getarr(){
        return this.arr;
    }

    public int size(){
        return cursize;
    }

    public void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public void add(int i) {
        // put in
        cursize++;
        lastptr++;
        arr[lastptr] = i;

        // try up
        int cur = lastptr;
        int parent = (cur % 2 == 0) ? (cur - 2) / 2 : (cur - 1) / 2;
        while (parent >= 0) {
            if (arr[parent] > arr[cur]) {
                int tmp = arr[parent];
                arr[parent] = arr[cur];
                arr[cur] = tmp;
                cur = parent;
            } else {
                break;
            }

            if (cur == 0) {
                break;
            }
            parent = (cur % 2 == 0) ? (cur - 2) / 2 : (cur - 1) / 2;
        }
    }

    public void shiftdown(int pos) {
        int cur = pos;
        while (cur <= lastptr) {

            if (2 * cur + 1 <= lastptr && 2 * cur + 2 <= lastptr) {
                int l = arr[2 * cur + 1];
                int r = arr[2 * cur + 2];

                int small = -1, big = -1;
                if (l <= r) {
                    small = 2 * cur + 1;
                    big = 2 * cur + 2;
                } else {
                    small = 2 * cur + 2;
                    big = 2 * cur + 1;
                }

                if (arr[cur] <= arr[small]) {
                    break;
                } else {
                    int swaprec = -1;
                    swaprec = small;

//                System.out.println("swapped"+swaprec+cur);

                    int tmp = arr[swaprec];
                    arr[swaprec] = arr[cur];
                    arr[cur] = tmp;
                    cur = swaprec;
                }
            } else if (2 * cur + 1 <= lastptr && 2 * cur + 2 > lastptr) {
                if (arr[cur] > arr[2 * cur + 1]) {
                    int tmp = arr[cur];
                    arr[cur] = arr[2 * cur + 1];
                    arr[2 * cur + 1] = tmp;
                    cur = 2 * cur + 1;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
    }

    public int remove() {
        int res = arr[0];
        arr[0] = arr[lastptr];
        lastptr--;
        cursize--;

        int cur = 0;
        while (cur <= lastptr) {

            if (2 * cur + 1 <= lastptr && 2 * cur + 2 <= lastptr) {
                int l = arr[2 * cur + 1];
                int r = arr[2 * cur + 2];

                int small = -1, big = -1;
                if (l <= r) {
                    small = 2 * cur + 1;
                    big = 2 * cur + 2;
                } else {
                    small = 2 * cur + 2;
                    big = 2 * cur + 1;
                }

                if (arr[cur] <= arr[small]) {
                    break;
                } else {
                    int swaprec = -1;
                    swaprec = small;

//                System.out.println("swapped"+swaprec+cur);

                    int tmp = arr[swaprec];
                    arr[swaprec] = arr[cur];
                    arr[cur] = tmp;
                    cur = swaprec;
                }
            } else if (2 * cur + 1 <= lastptr && 2 * cur + 2 > lastptr) {
                if (arr[cur] > arr[2 * cur + 1]) {
                    int tmp = arr[cur];
                    arr[cur] = arr[2 * cur + 1];
                    arr[2 * cur + 1] = tmp;
                    cur = 2 * cur + 1;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return res;
    }

    public int peek() {
        return arr[0];
    }

    public void debug() {
        System.out.println(Arrays.toString(Arrays.copyOfRange(this.arr, 0, 10)));
    }

}

class MaxHeapInt implements MyHeapInt {
    int[] arr;
    int lastptr; // last ptr for actual storage
    int cursize; // current height of heap

    public MaxHeapInt(int size) {

        this.arr = new int[size];
        this.lastptr = -1;
        this.cursize = 0;
    }

    public int[] getarr() {
        return this.arr;
    }

    public int size() {
        return cursize;
    }

    public void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public void add(int i) {
        // put in
        cursize++;
        lastptr++;
        arr[lastptr] = i;

        // try up
        int cur = lastptr;
        int parent = (cur % 2 == 0) ? (cur - 2) / 2 : (cur - 1) / 2;
        while (parent >= 0) {
            if (arr[parent] < arr[cur]) {
                int tmp = arr[parent];
                arr[parent] = arr[cur];
                arr[cur] = tmp;
                cur = parent;
            } else {
                break;
            }

            if (cur == 0) {
                break;
            }
            parent = (cur % 2 == 0) ? (cur - 2) / 2 : (cur - 1) / 2;
        }
    }

    public void shiftdown(int pos) {
        int cur = pos;
        while (cur <= lastptr) {

            if (2 * cur + 1 <= lastptr && 2 * cur + 2 <= lastptr) {
                int l = arr[2 * cur + 1];
                int r = arr[2 * cur + 2];

                int small = -1, big = -1;
                if (l <= r) {
                    small = 2 * cur + 1;
                    big = 2 * cur + 2;
                } else {
                    small = 2 * cur + 2;
                    big = 2 * cur + 1;
                }

                if (arr[cur] >= arr[big]) {
                    break;
                } else {
                    int swaprec = -1;
                    swaprec = big;

//                System.out.println("swapped"+swaprec+cur);

                    int tmp = arr[swaprec];
                    arr[swaprec] = arr[cur];
                    arr[cur] = tmp;
                    cur = swaprec;
                }
            } else if (2 * cur + 1 <= lastptr && 2 * cur + 2 > lastptr) {
                if (arr[cur] < arr[2 * cur + 1]) {
                    int tmp = arr[cur];
                    arr[cur] = arr[2 * cur + 1];
                    arr[2 * cur + 1] = tmp;
                    cur = 2 * cur + 1;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
    }

    public int remove() {
        int res = arr[0];
        arr[0] = arr[lastptr];
        lastptr--;
        cursize--;

        int cur = 0;
        while (cur <= lastptr) {

            if (2 * cur + 1 <= lastptr && 2 * cur + 2 <= lastptr) {
                int l = arr[2 * cur + 1];
                int r = arr[2 * cur + 2];

                int small = -1, big = -1;
                if (l <= r) {
                    small = 2 * cur + 1;
                    big = 2 * cur + 2;
                } else {
                    small = 2 * cur + 2;
                    big = 2 * cur + 1;
                }

                if (arr[cur] >= arr[big]) {
                    break;
                } else {
                    int swaprec = -1;
                    swaprec = big;

//                System.out.println("swapped"+swaprec+cur);

                    int tmp = arr[swaprec];
                    arr[swaprec] = arr[cur];
                    arr[cur] = tmp;
                    cur = swaprec;
                }
            } else if (2 * cur + 1 <= lastptr && 2 * cur + 2 > lastptr) {
                if (arr[cur] < arr[2 * cur + 1]) {
                    int tmp = arr[cur];
                    arr[cur] = arr[2 * cur + 1];
                    arr[2 * cur + 1] = tmp;
                    cur = 2 * cur + 1;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return res;
    }

    public int peek() {
        return arr[0];
    }

    public void debug() {
        System.out.println(Arrays.toString(Arrays.copyOfRange(this.arr, 0, 10)));
    }
}