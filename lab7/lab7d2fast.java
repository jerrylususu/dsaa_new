//package lab7;
//
//import java.util.Arrays;
//
//
//
//
//import java.io.*;
//import java.math.*;
//import java.util.StringTokenizer;
///**
// * Built using CHelper plug-in
// * Actual solution is at the top
// * Author: Wavator
// */
//public class lab7d2fast {
//    public static void main(String[] args){
//        InputStream inputStream = System.in;//new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
//        OutputStream outputStream = System.out;
//        InputReader in = new InputReader(inputStream);
//        PrintWriter out = new PrintWriter(outputStream);
//        Task solver = new Task();
//        solver.solve(in, out);
//        out.close();
//    }
//    static class Task {
//        public void solve(InputReader in, PrintWriter out) {
//            int totalnum = in.nextInt();
//
//            while(totalnum-->0){
//                int n = in.nextInt();
//                int k = in.nextInt();
//                MyHeapInt heap = null;
//                boolean isMax = true;
//                int size = -1;
//                if(k>n/2){
//                    // max heap
//                    size = n-k+1;
//                    heap = new MaxHeapInt(size);
//                } else {
//                    // min heap
//                    size = k;
//                    isMax = false;
//                    heap = new MinHeapInt(size);
//                }
//
//                for (int i = 0; i < n; i++) {
//                    int tmp = in.nextInt();
//                    if(heap.size()<size){
//                        // not full
//                        heap.add(tmp);
//                    } else {
//                        // already full
//                        if(isMax){
//                            // is max heap
//                            if(tmp<heap.peek()){
////                            heap.remove();
////                            heap.add(tmp);
//                                heap.getarr()[0] = tmp;
//                                heap.shiftdown(0);
//                                // potential performance issue here
//                            }
//                        } else {
//                            // is min heap
//                            if(tmp>heap.peek()){
////                            heap.remove();
////                            heap.add(tmp);
//                                heap.getarr()[0] = tmp;
//                                heap.shiftdown(0);
//                            }
//                        }
//
//                    }
//                }
//
//                out.println(heap.peek());
//                out.flush();
//
//            }
//        }
//
//    }
//    static class InputReader {
//        public BufferedReader reader;
//        public StringTokenizer tokenizer;
//        public InputReader(InputStream stream) {
//            reader = new BufferedReader(new InputStreamReader(stream), 32768);
//            tokenizer = null;
//        }
//        public String next() {
//            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
//                try {
//                    tokenizer = new StringTokenizer(reader.readLine());
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            return tokenizer.nextToken();
//        }
//        public int nextInt() {
//            return Integer.parseInt(next());
//        }
//        public long nextLong() {
//            return Long.parseLong(next());
//        }
//
//        public double nextDouble() {
//            return Double.parseDouble(next());
//        }
//
//        public char[] nextCharArray() {
//            return next().toCharArray();
//        }
//
//        //         public boolean hasNext() {
////             try {
////                 return reader.ready();
////             } catch(IOException e) {
////                 throw new RuntimeException(e);
////             }
////         }
//        public boolean hasNext() {
//            try {
//                String string = reader.readLine();
//                if (string == null) {
//                    return false;
//                }
//                tokenizer = new StringTokenizer(string);
//                return tokenizer.hasMoreTokens();
//            } catch(IOException e) {
//                return false;
//            }
//        }
//        public BigInteger nextBigInteger() {
//            return new BigInteger(next());
//        }
//
//        public BigDecimal nextBigDecinal() {
//            return new BigDecimal(next());
//        }
//    }
//}
//
//
//interface MyHeapInt{
//
//    public int[] getarr();
//
//    public int size();
//
//    public void swap(int i, int j);
//
//    public void add(int i);
//
//
//    public int peek();
//
//    public int remove();
//
//    public void shiftdown(int i);
//}
//
//class MinHeapInt implements MyHeapInt {
//    int[] arr;
//    int lastptr; // last ptr for actual storage
//    int cursize; // current height of heap
//
//    public MinHeapInt(int size) {
//        this.arr = new int[size];
//        this.lastptr = -1;
//        this.cursize = 0;
//    }
//
//    public int[] getarr(){
//        return this.arr;
//    }
//
//    public int size(){
//        return cursize;
//    }
//
//    public void swap(int i, int j) {
//        int tmp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = tmp;
//    }
//
//    public void add(int i) {
//        // put in
//        cursize++;
//        lastptr++;
//        arr[lastptr] = i;
//
//        // try up
//        int cur = lastptr;
//        int parent = (cur % 2 == 0) ? (cur - 2) / 2 : (cur - 1) / 2;
//        while (parent >= 0) {
//            if (arr[parent] > arr[cur]) {
//                int tmp = arr[parent];
//                arr[parent] = arr[cur];
//                arr[cur] = tmp;
//                cur = parent;
//            } else {
//                break;
//            }
//
//            if (cur == 0) {
//                break;
//            }
//            parent = (cur % 2 == 0) ? (cur - 2) / 2 : (cur - 1) / 2;
//        }
//    }
//
//    public void shiftdown(int pos) {
//        int cur = pos;
//        while (cur <= lastptr) {
//
//            if (2 * cur + 1 <= lastptr && 2 * cur + 2 <= lastptr) {
//                int l = arr[2 * cur + 1];
//                int r = arr[2 * cur + 2];
//
//                int small = -1, big = -1;
//                if (l <= r) {
//                    small = 2 * cur + 1;
//                    big = 2 * cur + 2;
//                } else {
//                    small = 2 * cur + 2;
//                    big = 2 * cur + 1;
//                }
//
//                if (arr[cur] <= arr[small]) {
//                    break;
//                } else {
//                    int swaprec = -1;
//                    swaprec = small;
//
////                System.out.println("swapped"+swaprec+cur);
//
//                    int tmp = arr[swaprec];
//                    arr[swaprec] = arr[cur];
//                    arr[cur] = tmp;
//                    cur = swaprec;
//                }
//            } else if (2 * cur + 1 <= lastptr && 2 * cur + 2 > lastptr) {
//                if (arr[cur] > arr[2 * cur + 1]) {
//                    int tmp = arr[cur];
//                    arr[cur] = arr[2 * cur + 1];
//                    arr[2 * cur + 1] = tmp;
//                    cur = 2 * cur + 1;
//                } else {
//                    break;
//                }
//            } else {
//                break;
//            }
//        }
//    }
//
//    public int remove() {
//        int res = arr[0];
//        arr[0] = arr[lastptr];
//        lastptr--;
//        cursize--;
//
//        int cur = 0;
//        while (cur <= lastptr) {
//
//            if (2 * cur + 1 <= lastptr && 2 * cur + 2 <= lastptr) {
//                int l = arr[2 * cur + 1];
//                int r = arr[2 * cur + 2];
//
//                int small = -1, big = -1;
//                if (l <= r) {
//                    small = 2 * cur + 1;
//                    big = 2 * cur + 2;
//                } else {
//                    small = 2 * cur + 2;
//                    big = 2 * cur + 1;
//                }
//
//                if (arr[cur] <= arr[small]) {
//                    break;
//                } else {
//                    int swaprec = -1;
//                    swaprec = small;
//
////                System.out.println("swapped"+swaprec+cur);
//
//                    int tmp = arr[swaprec];
//                    arr[swaprec] = arr[cur];
//                    arr[cur] = tmp;
//                    cur = swaprec;
//                }
//            } else if (2 * cur + 1 <= lastptr && 2 * cur + 2 > lastptr) {
//                if (arr[cur] > arr[2 * cur + 1]) {
//                    int tmp = arr[cur];
//                    arr[cur] = arr[2 * cur + 1];
//                    arr[2 * cur + 1] = tmp;
//                    cur = 2 * cur + 1;
//                } else {
//                    break;
//                }
//            } else {
//                break;
//            }
//        }
//        return res;
//    }
//
//    public int peek() {
//        return arr[0];
//    }
//
//    public void debug() {
//        System.out.println(Arrays.toString(Arrays.copyOfRange(this.arr, 0, 10)));
//    }
//
//}
//
//class MaxHeapInt implements MyHeapInt {
//    int[] arr;
//    int lastptr; // last ptr for actual storage
//    int cursize; // current height of heap
//
//    public MaxHeapInt(int size) {
//
//        this.arr = new int[size];
//        this.lastptr = -1;
//        this.cursize = 0;
//    }
//
//    public int[] getarr() {
//        return this.arr;
//    }
//
//    public int size() {
//        return cursize;
//    }
//
//    public void swap(int i, int j) {
//        int tmp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = tmp;
//    }
//
//    public void add(int i) {
//        // put in
//        cursize++;
//        lastptr++;
//        arr[lastptr] = i;
//
//        // try up
//        int cur = lastptr;
//        int parent = (cur % 2 == 0) ? (cur - 2) / 2 : (cur - 1) / 2;
//        while (parent >= 0) {
//            if (arr[parent] < arr[cur]) {
//                int tmp = arr[parent];
//                arr[parent] = arr[cur];
//                arr[cur] = tmp;
//                cur = parent;
//            } else {
//                break;
//            }
//
//            if (cur == 0) {
//                break;
//            }
//            parent = (cur % 2 == 0) ? (cur - 2) / 2 : (cur - 1) / 2;
//        }
//    }
//
//    public void shiftdown(int pos) {
//        int cur = pos;
//        while (cur <= lastptr) {
//
//            if (2 * cur + 1 <= lastptr && 2 * cur + 2 <= lastptr) {
//                int l = arr[2 * cur + 1];
//                int r = arr[2 * cur + 2];
//
//                int small = -1, big = -1;
//                if (l <= r) {
//                    small = 2 * cur + 1;
//                    big = 2 * cur + 2;
//                } else {
//                    small = 2 * cur + 2;
//                    big = 2 * cur + 1;
//                }
//
//                if (arr[cur] >= arr[big]) {
//                    break;
//                } else {
//                    int swaprec = -1;
//                    swaprec = big;
//
////                System.out.println("swapped"+swaprec+cur);
//
//                    int tmp = arr[swaprec];
//                    arr[swaprec] = arr[cur];
//                    arr[cur] = tmp;
//                    cur = swaprec;
//                }
//            } else if (2 * cur + 1 <= lastptr && 2 * cur + 2 > lastptr) {
//                if (arr[cur] < arr[2 * cur + 1]) {
//                    int tmp = arr[cur];
//                    arr[cur] = arr[2 * cur + 1];
//                    arr[2 * cur + 1] = tmp;
//                    cur = 2 * cur + 1;
//                } else {
//                    break;
//                }
//            } else {
//                break;
//            }
//        }
//    }
//
//    public int remove() {
//        int res = arr[0];
//        arr[0] = arr[lastptr];
//        lastptr--;
//        cursize--;
//
//        int cur = 0;
//        while (cur <= lastptr) {
//
//            if (2 * cur + 1 <= lastptr && 2 * cur + 2 <= lastptr) {
//                int l = arr[2 * cur + 1];
//                int r = arr[2 * cur + 2];
//
//                int small = -1, big = -1;
//                if (l <= r) {
//                    small = 2 * cur + 1;
//                    big = 2 * cur + 2;
//                } else {
//                    small = 2 * cur + 2;
//                    big = 2 * cur + 1;
//                }
//
//                if (arr[cur] >= arr[big]) {
//                    break;
//                } else {
//                    int swaprec = -1;
//                    swaprec = big;
//
////                System.out.println("swapped"+swaprec+cur);
//
//                    int tmp = arr[swaprec];
//                    arr[swaprec] = arr[cur];
//                    arr[cur] = tmp;
//                    cur = swaprec;
//                }
//            } else if (2 * cur + 1 <= lastptr && 2 * cur + 2 > lastptr) {
//                if (arr[cur] < arr[2 * cur + 1]) {
//                    int tmp = arr[cur];
//                    arr[cur] = arr[2 * cur + 1];
//                    arr[2 * cur + 1] = tmp;
//                    cur = 2 * cur + 1;
//                } else {
//                    break;
//                }
//            } else {
//                break;
//            }
//        }
//        return res;
//    }
//
//    public int peek() {
//        return arr[0];
//    }
//
//    public void debug() {
//        System.out.println(Arrays.toString(Arrays.copyOfRange(this.arr, 0, 10)));
//    }
//}
