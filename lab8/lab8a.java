//package lab8;
//
//import java.util.Scanner;
//
//public class lab8a {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while(totalnum-->0){
//            int n = in.nextInt(); // # of node
//            int m = in.nextInt(); // # of edge
//
//            int[][] arr = new int[n][n];
//            for (int i = 0; i < m; i++) {
//                arr[in.nextInt()-1][in.nextInt()-1] = 1;
//            }
//
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    if(j!=n-1){
//                        System.out.print(arr[i][j]+" ");
//                    } else {
//                        System.out.println(arr[i][j]);
//                    }
//                }
//            }
//
//        }
//    }
//}
