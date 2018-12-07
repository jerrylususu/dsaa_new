//package lab7;
//
//import java.util.*;
//
//// using my own MinHeapInt as underlying
//// TLE
//
//public class lab7g4 {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while(totalnum-->0){
//            int n = in.nextInt();
//            int k = (int)Math.sqrt(n);
//            int[] arr = new int[n];
//            HashMap<Map.Entry<Integer,Integer>, Integer> hm = new HashMap<>();
//            // entry: start, step (x,y)
//            for (int i = 0; i < n; i++) {
//                arr[i] = in.nextInt();
//            }
//            for (int step = 1; step <= k+1; step++) {
//                for (int type = 0; type < step; type++) {
//                    int tmpsize = 0;
//                    int cursor = n-1-type;
//                    MinHeapInt m = new MinHeapInt(k);
//                    while(cursor>=0){
//                        if(m.size()<k){
//                            m.add(arr[cursor]);
//                        } else if (m.peek()<arr[cursor]){
//                            m.arr[0] = arr[cursor];
//                            m.shiftdown(0);
//                        }
//
//                        tmpsize++;
//                        if(tmpsize>=k){
//                            hm.put(new AbstractMap.SimpleEntry<>(cursor+1,step),m.peek());
//                        }
//                        cursor-=step;
//                    }
//                }
//            }
////            System.out.println("build finish");
//            for (int i = 0; i < n; i++) {
//                int x = in.nextInt(), y = in.nextInt();
//                AbstractMap.SimpleEntry key = new AbstractMap.SimpleEntry(x,y);
//                if(hm.containsKey(key)){
//                    System.out.println(hm.get(key));
//                } else {
//                    System.out.println(-1);
//                }
//            }
//
//
//        }
//    }
//}
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