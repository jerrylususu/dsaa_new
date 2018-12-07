package lab2;

import java.util.*;

public class lab1d{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int days = in.nextInt();
        int orders = in.nextInt();

        long[] arr = new long[days];
        for(int i=0;i<days;i++){
            arr[i] = in.nextLong();
        }

        long[] r = new long[orders];
        int[] s = new int[orders];
        int[] e = new int[orders];

        for(int i=0;i<orders;i++){
            r[i] = in.nextLong();
            s[i] = in.nextInt()-1;
            e[i] = in.nextInt()-1;
        }

        boolean ok=true;
        int move=0;

        d: for(int i=0;i<orders;i++){
            for(int j=s[i];j<=e[i];j++){
                if(arr[j]<=0){
                    ok=false;
                    move=i+1;
                    break d;
                }
                arr[j]-=r[i];
                if(arr[j]<0){
                    ok=false;
                    move=i+1;
                    break d;
                }
            }
        }

        if(ok){
            System.out.println(0);
        }else{
            System.out.println(-1);
            System.out.println(move);
        }

    }
}