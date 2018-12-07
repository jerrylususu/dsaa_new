package lab4;

import java.util.Scanner;

public class lab3c {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();

        while(total-->0){
            int n = in.nextInt();
            int m = in.nextInt();
            int[] arr = new int[n];
            for(int i=0;i<n;i++){
                arr[i]=in.nextInt();
            }
            int slow=0;
            int fast=2;
            long sum=0;
            while(slow<=n-3){
                if(fast!=n){
                    for(int i=slow+2;i<fast;i++){
                        sum+=i-slow-1;
                    }
                    while(arr[fast]-arr[slow]<=m){
                        sum+=fast-slow-1;
                        fast++;
                        if(fast==n){
                            break;
                        }
                    }
                } else {
                    // already reached the max
                    for(int i=slow+2;i<=n-1;i++){
                        sum+=i-slow-1;
                    }
                }
                slow++;
            }
            System.out.println(sum);
        }
    }
}
