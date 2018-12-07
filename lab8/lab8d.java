//package lab8;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.Scanner;
//
//public class lab8d {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while(totalnum-->0){
//            int n = in.nextInt();
//            int m = in.nextInt();
//
//            int[] num = new int[n];
//            for (int i = 0; i < n; i++) {
//                num[i] = i;
//            }
//
//            boolean[][] rec = new boolean[n][n];
//
//            for (int i = 0; i < m; i++) {
//                int n1 = in.nextInt()-1, n2 = in.nextInt()-1;
//                rec[n1][n2] = true;
//                rec[n2][n1] = true;
//            }
//
//            int cnt = 0;
//            ArrayList<int[]> resli = new ArrayList<>();
//
//            boolean a = true,b=true,c=true,d=true;
//
//            for (int i = 0; i < n; i++) {
//                if(a){
//                    for (int j = i+1; j < n; j++) {
//                        if(b){
//                            for (int k = j+1; k < n; k++) {
//                                if(c){
//                                    for (int l = k+1; l < n; l++) {
//                                        if(d){
//
//
//
//
//                                        } else {
//                                            d = true;
//                                            continue;
//                                        }
//                                    }
//                                } else {
//                                    d = true;
//                                    c = true;
//                                    continue;
//                                }
//
//                            }
//                        } else {
//                            d = true;
//                            c = true;
//                            b = true;
//                            continue;
//                        }
//                    }
//                } else {
//                    d = true;
//                    c = true;
//                    b = true;
//                    a = true;
//                    continue;
//                }
//
//            }
//
//            System.out.println(cnt);
//
//
//
//        }
//    }
//
//    public static boolean checkClique(boolean[][] rec, int[] select){
//        boolean check;
//        for (int i = 0; i < 4; i++) {
//            for (int j = i+1; j < 4; j++) {
//                if(!rec[select[i]][select[j]]){
//                    check = false;
//                    return false;
//                }
//            }
//        }
//        return true;
//
//
//    }
//
//    public static ArrayList<int[]> genP(int[] arr){
//        if(arr.length<4){
//            return null;
//        }
//        ArrayList<int[]> resli = new ArrayList<>();
//        int n = arr.length;
//        for (int i = 0; i < n; i++) {
//            for (int j = i+1; j < n; j++) {
//                for (int k = j+1; k < n; k++) {
//                    for (int l = k+1; l < n; l++) {
//                        resli.add(new int[]{arr[i],arr[j],arr[k],arr[l]});
//                    }
//                }
//
//            }
//        }
//        return resli;
//    }
//}
