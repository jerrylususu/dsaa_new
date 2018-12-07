//package lab6;
//
//import java.util.Comparator;
//import java.util.PriorityQueue;
//import java.util.Scanner;
//
//public class lab6cpq {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//        while(totalnum-->0){
//            int n = in.nextInt();
//            PriorityQueue<Integer> li = new PriorityQueue<>();
//            for (int i = 0; i < n; i++) {
//                li.add(in.nextInt());
//            }
//            int opn = in.nextInt();
//            for (int i = 0; i < opn; i++) {
//                int op = in.nextInt();
//                switch (op){
//                    case 1: li.add(in.nextInt()); break;
//                    case 2: li.remove(); break;
//                    case 3:
//                        System.out.println(li.size()>0?li.peek():0); break;
//                }
//            }
//        }
//    }
//}
