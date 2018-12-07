package lab3;

import java.util.Arrays;
import java.util.Scanner;

public class lab2f {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();

        while(total-->0){
            int n = in.nextInt();
            int k = in.nextInt();
            int[] arr = new int[n];
            for(int i=0;i<n;i++){
                arr[i]=in.nextInt();
            }

            int[] tmp=new int[n];
            int sum=0;

            for(int size=k;size<=n;size++){
                for(int start=0;start<=n-size;start++){
                    //System.out.printf("%d %d\n",size,start);
                    System.arraycopy(arr,start,tmp,0,size);
                    Arrays.sort(tmp,0,size);
                    sum+=tmp[size-k];
                }
            }

            System.out.println(sum);
        }
    }
}
