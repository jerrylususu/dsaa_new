//package lab3;
//
//import java.util.Scanner;
//
//public class lab3b {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int total = in.nextInt();
//
//        while(total-->0){
//            int len = in.nextInt();
//            String line = in.next();
//
//            stack s = new stack(len);
//            boolean ok = true;
//            if(len%2==0) {
//
//                for (int i = 0; i < len; i++) {
//                    char c = line.charAt(i);
//                    if (c == '(' || c == '[' || c == '{') {
//                        s.push(c);
//                        continue;
//                    }
//                    if (c == ')' || c == ']' || c == '}') {
//                        if(s.size>0) {
//
//
//                            char p = s.pop();
//                            boolean chk = true;
//                            if ((c == ')' && p == '(') || (c == ']' && p == '[') || (c == '}' && p == '{')) {
//                                chk = true;
//                            } else {
//                                chk = false;
//                                ok = false;
//                                break;
//                            }
//                        }else{
//                            ok=false;
//                            break;
//                        }
//                    }
//                }
//            } else {
//                ok=false;
//            }
//            if(ok==false||s.size>0){
//                System.out.println("NO");
//            } else {
//                System.out.println("YES");
//            }
//        }
//    }
//}
//
//class stack{
//    int maxsize;
//    int size;
//    char[] arr;
//    int topptr;
//
//    public stack (int s){
//        this.maxsize = s;
//        this.size = 0;
//        this.topptr = -1;
//        arr = new char[maxsize];
//    }
//
//    public void push(char c){
//        topptr++;
//        if(topptr<=maxsize){
//            size++;
//            arr[topptr]=c;
//        } else {
//            System.out.println("Full");
//        }
//    }
//
//    public char pop(){
//        if(size>=0){
//            char tmp = arr[topptr];
//            topptr--;
//            size--;
//            return tmp;
//        } else {
//            System.out.println("Empty");
//            return '0';
//        }
//    }
//
//}
