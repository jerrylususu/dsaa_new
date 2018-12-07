package lab3;

import java.util.LinkedList;
import java.util.Scanner;

public class lab2e3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();

        while (total-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int e = in.nextInt();

            int k = 0;
            boolean ok = true;
            if(n==1){
                ok = false;
            }
            // Josephus Ring
            // starting from 2 and go reverse
            for(int i=2;i<=n;i++){
                k = (k+m+1)%i;
            }

            //System.out.println(k);

            System.out.println((k==e)?"Yes":"No");
        }
    }

    public static int calc(int n, int m){
        if(n==1){
            return 0;
        } else {
            int last = calc(n-1,m);
            return (last+m)%n;
        }
    }
}