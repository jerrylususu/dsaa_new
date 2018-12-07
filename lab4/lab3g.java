//package lab3;
//
//import java.util.Scanner;
//
//public class lab3g {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int total = in.nextInt();
//
//        while(total-->0){
//            int len  = in.nextInt();
//            stack max=new stack(len), min=new stack(len), real=new stack(len);
//
//            for(int i=0;i<len;i++){
//                String s = in.next();
//                if(s.charAt(1)=='u'){
//                    //push
//                    int tmp = in.nextInt();
//                    if(real.size==0){
//                        //init
//                        real.push(tmp);
//                        max.push(tmp);
//                        min.push(tmp);
//                    } else {
//                        real.push(tmp);
//                        int curmin = min.peek(), curmax =max.peek();
//                        min.push(tmp<curmin?tmp:curmin);
//                        max.push(tmp>curmax?tmp:curmax);
//                    }
//                } else {
//                    //pop
//                    int res=0;
//                    if(real.size>1){
//                        max.pop();
//                        min.pop();
//                        res = max.peek()-min.peek();
//                        real.pop();
//                    }else{
//                        res=0;
//                    }
//                    System.out.println(res);
//                }
//            }
//
//        }
//
//
//    }
//}
//
//class stack{
//    int maxsize;
//    int size;
//    int[] arr;
//    int topptr;
//
//    public stack (int s){
//        this.maxsize = s;
//        this.size = 0;
//        this.topptr = -1;
//        arr = new int[maxsize];
//    }
//
//    public void push(int c){
//        topptr++;
//        if(topptr<=maxsize){
//            size++;
//            arr[topptr]=c;
//        } else {
//            System.out.println("Full");
//        }
//    }
//
//    public int pop(){
//        if(size>=0){
//            int tmp = arr[topptr];
//            topptr--;
//            size--;
//            return tmp;
//        } else {
//            System.out.println("Empty");
//            return '0';
//        }
//    }
//
//    public int peek(){
//        if(size>=0){
//            return arr[topptr];
//        }else{
//            return -1;
//        }
//    }
//
//}