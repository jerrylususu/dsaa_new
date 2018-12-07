//package lab6;
//
//import java.util.Scanner;
//
//public class lab6a2 {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//        StringBuilder sb = new StringBuilder();
//
//        while(totalnum-->0){
//            int n = in.nextInt();
//            boolean[] arr = new boolean[n];
//            for (int i = 0; i < n-1; i++) {
//                int p = in.nextInt()-1;
//                int s = in.nextInt()-1;
//                if(arr[p]==false){
//                    arr[p]=true;
//                }
//            }
//            for (int i = 0; i < n; i++) {
//                if(arr[i]==false){
//                    sb.append((i+1)+" ");
//                }
//            }
//            sb.deleteCharAt(sb.length()-1);
//            System.out.println(sb);
//            sb.delete(0, sb.length());
//        }
//    }
//}
