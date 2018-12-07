package lab1;

import java.util.Scanner;

public class lab0e {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();

        while(times-->0){
            int size = in.nextInt();
            int[] arr = new int[size];
            for(int i=0;i<size;i++){
                arr[i] = in.nextInt();
            }

            int max=arr[0],max_diff=Integer.MIN_VALUE;
            for(int i=1;i<size;i++){
//                动态规划
//                后边的要么最大 要么能扩大当前的最大差值
                if(max-arr[i]>max_diff){
                    max_diff=max-arr[i];
                }
                if(arr[i]>max){
                    max=arr[i];
                }
            }

            System.out.println(max_diff);

        }

    }
}
