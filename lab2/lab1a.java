package lab2;

import java.util.Scanner;

public class lab1a {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();

        while(times-->0){
            int total = in.nextInt();
            int target = in.nextInt();

            int[] arr = new int[total];
            for(int i=0;i<total;i++){
                arr[i] = in.nextInt();
            }

            if(biSearch(arr,target)>=0){
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }


        }
    }
    public static int biSearch(int []array,int a){
        int lo=0;
        int hi=array.length-1;
        int mid;
        while(lo<=hi){                //防死循环
            mid=lo+(hi-lo)/2;         //防超限
            if(array[mid]==a){
                return mid+1;
            }else if(array[mid]<a){
                lo=mid+1;             //防死循环
            }else{
                hi=mid-1;             //防死循环
            }
        }
        return -1;
    }



}
