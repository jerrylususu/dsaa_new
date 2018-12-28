package lab2;

import java.util.Scanner;

public class lab1c {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();

        while(times-->0){
            int size = in.nextInt();
            int key = in.nextInt();

            int[] arr = new int[size];
            for(int i=0;i<size;i++){
                arr[i] = in.nextInt();
            }

            int left=0, right=size-1;
            int cnt=0, sum=0;
            while(left<size&&arr[left]<key){
                sum=arr[left]+arr[right];
                //System.out.println(right);
                while(left<right&&sum>=key){
                    sum=arr[left]+arr[right];
                    if(sum==key){
                        cnt++;
                        right--;
                    } else if(sum>key){
                        right--;
                    } else {
                        break;
                    }
                }
                left++;
                right=size-1;
                //System.out.println(right);
            }

            System.out.println(cnt);
        }

    }
}
