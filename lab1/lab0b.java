package lab1;

import java.util.Scanner;

public class lab0b {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        while(times-->0){
            int n = in.nextInt();
            double tmp=3;
            for(int i=0;i<n-1;i++){
                tmp=3*tmp;
                tmp=tmp%1000000007;
            }
            System.out.printf("%.0f\n",tmp-1);
        }
    }
}
