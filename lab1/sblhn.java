package lab1;

import java.util.Scanner;

public class sblhn {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        double p = Math.floor(Math.sqrt(i));
        double d = i-p*(p-1)/2;
        double k = d/8;
        double n = d%8;
        if(k%2==0){
            System.out.println(1+n);
        }else {
            System.out.println(9-n);
        }

    }

}
