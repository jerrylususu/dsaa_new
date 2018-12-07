//package lab6;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class lab6c {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//         while(totalnum-->0){
//             int initn = in.nextInt();
//             MinHeap<Integer> mh = new MinHeap<>(200010);
//             for (int i = 0; i < initn; i++) {
//                 mh.add(new MinHeap.Node<>(in.nextInt()));
//             }
//             int opn = in.nextInt(), op=-1;
//             for (int i = 0; i < opn; i++) {
//                 op = in.nextInt();
//                 if(op==1){
//                     //add
//                     mh.add(new MinHeap.Node<>(in.nextInt()));
//                 } else if(op==2){
//                     //delete
//                     mh.remove();
//                 } else if(op==3){
//                     //query
//                     System.out.println(mh.peek());
//                 }
////                 mh.debug();
//             }
//
//
//         }
//    }
//}
//
//class MinHeap<T extends Comparable<T>>{
//    Node<T>[] arr;
//    int lastptr; // last ptr for actual storage
//    int cursize; // current size of heap
//
//    public MinHeap(int size){
//        this.arr = new Node[size];
//        this.lastptr = -1;
//        this.cursize =0;
//    }
//
//    static class Node<T extends Comparable<T>> implements Comparable<Node>{
//        private T data;
//
//        public Node(T d){
//            this.data = d;
//        }
//
//        public T getData(){
//            return data;
//        }
//
//        public void setData(T d){
//            this.data=d;
//        }
//
//        @Override
//        public int compareTo(Node o){
//            return this.data.compareTo((T) o.getData());
//        }
//
//        @Override
//        public String toString() {
//            return data.toString();
//        }
//    }
//    public void swap(int i, int j){
//        Node<T> tmp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = tmp;
//    }
//
//    public void shiftUp(int pos){
//        int cur=pos;
//        int parent=(cur%2==0)?(cur-2)/2:(cur-1)/2;
//        while(parent>=0){
//            if(arr[parent].compareTo(arr[cur])>0){
//                swap(parent,cur);
//                cur=parent;
//            } else {
//                break;
//            }
//
//            if(cur==0){
//                break;
//            }
//            parent=(cur%2==0)?(cur-2)/2:(cur-1)/2;
//        }
//    }
//
//    public void shiftDown(int pos){
//        int cur = pos;
//        while(cur<=lastptr){
//            if(2*cur+1<=lastptr&&2*cur+2<=lastptr) {
//                Node<T> l = arr[2 * cur + 1];
//                Node<T> r = arr[2 * cur + 2];
//
//                int small = -1, big = -1;
//                if (l.compareTo(r)<=0) {
//                    small = 2 * cur + 1;
//                    big = 2 * cur + 2;
//                } else {
//                    small = 2 * cur + 2;
//                    big = 2 * cur + 1;
//                }
//
//                if (arr[cur].compareTo(arr[small])<=0) {
//                    break;
//                } else {
//                    int swaprec = -1;
//                    swaprec = small;
//
////                System.out.println("swapped"+swaprec+cur);
//
//                    swap(swaprec,cur);
//                    cur = swaprec;
//                }
//            } else if (2*cur+1<=lastptr && 2*cur+2>lastptr){
//                if(arr[cur].compareTo(arr[2*cur+1])>0){
//                    swap(cur,2*cur+1);
//                    cur=2*cur+1;
//                } else {
//                    break;
//                }
//            } else {
//                break;
//            }
//        }
//    }
//
//    public void add(Node<T> i){
//        // put in
//        cursize++;
//        lastptr++;
//        arr[lastptr]=i;
//
//        // try up
//        shiftUp(lastptr);
//    }
//
//    public Node<T> remove(){
//        Node<T> res = arr[0];
//        arr[0] = arr[lastptr];
//        lastptr--;
//        cursize--;
//
//        shiftDown(0);
//        return res;
//    }
//
//    public Node<T> peek(){
//        return arr[0];
//    }
//
//    public void debug(){
//        System.out.println(Arrays.toString(Arrays.copyOfRange(this.arr,0,10)));
//    }
//
//}
//
//
//class MinHeapInt{
//    int[] arr;
//    int lastptr; // last ptr for actual storage
//    int cursize; // current size of heap
//
//    public MinHeap(int size){
//        this.arr = new int[size];
//        this.lastptr = -1;
//        this.cursize =0;
//    }
//
////    public void heapify(int cur){
////        int left = 2*cur+1, right=2*cur+2;
////        int small = -1, big = -1;
////        if(arr[left]>arr[right]){
////            small=right;big=left;
////        } else {
////            small=left;big=right;
////        }
////
////
////        }
//
//
//
////    }
//
//    public void swap(int i, int j){
//        int tmp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = tmp;
//    }
//
//    public void add(int i){
//        // put in
//        cursize++;
//        lastptr++;
//        arr[lastptr]=i;
//
//        // try up
//        int cur=lastptr;
//        int parent=(cur%2==0)?(cur-2)/2:(cur-1)/2;
//        while(parent>=0){
//            if(arr[parent]>arr[cur]){
//                int tmp = arr[parent];
//                arr[parent] = arr[cur];
//                arr[cur] = tmp;
//                cur=parent;
//            } else {
//                break;
//            }
//
//            if(cur==0){
//                break;
//            }
//            parent=(cur%2==0)?(cur-2)/2:(cur-1)/2;
//        }
//    }
//
//    public int remove(){
//        int res = arr[0];
//        arr[0] = arr[lastptr];
//        lastptr--;
//        cursize--;
//
//        int cur = 0;
//        while(cur<=lastptr){
//
//            if(2*cur+1<=lastptr&&2*cur+2<=lastptr) {
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
//            } else if (2*cur+1<=lastptr && 2*cur+2>lastptr){
//                if(arr[cur]>arr[2*cur+1]){
//                    int tmp = arr[cur];
//                    arr[cur]=arr[2*cur+1];
//                    arr[2*cur+1]=tmp;
//                    cur=2*cur+1;
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
//    public int peek(){
//        return arr[0];
//    }
//
//    public void debug(){
//        System.out.println(Arrays.toString(Arrays.copyOfRange(this.arr,0,10)));
//    }
//
//}
