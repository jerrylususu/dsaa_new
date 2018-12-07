//package lab7;
//
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class lab7a {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while(totalnum-->0){
//            int n = in.nextInt();
//            int cnt = 1;
////            while(cnt<n){
////                cnt = cnt*2;
////            }
//            cnt = 2*n+2;
//            int[] arr = new int[cnt];
//            arr[0]=1;
//            for (int i = 0; i < n; i++) {
//                int l = in.nextInt(), r = in.nextInt();
//                arr[2*i+1]=l; arr[2*i+2]=r;
//            }
//            boolean check = true;
//            for (int i = 0; i < n; i++) {
//                if(arr[i]==0){
//                    check = false;
//                    break;
//                }
//            }
////            System.out.println(Arrays.toString(arr));
//            System.out.println(check?"Yes":"No");
//
//        }
//    }
//}
