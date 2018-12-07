package lab2;

import java.util.Scanner;

public class lab1b {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        int cnt = 1;

        while(times-->0){
            double y = in.nextDouble();
            double right = 100;
            double left = 0;
            double x = right/2;
            double fx = 35*Math.pow(x,6)+36*Math.pow(x,5)+9*Math.pow(x,2)+8*x-2*y;
            while(Math.abs(fx)>1e-5){
                x = (left+right)/2;
                fx = 35*Math.pow(x,6)+36*Math.pow(x,5)+9*Math.pow(x,2)+8*x-2*y;
                if(fx>0){
                    right=x;
                }else if(fx<0){
                    left=x;
                }else{
                    break;
                }
            }

            //System.out.println(x);
            double ofx = 5*Math.pow(x,7)+6*Math.pow(x,6)+3*Math.pow(x,3)+4*Math.pow(x,2)-2*x*y;
            System.out.printf("Case %d: %.4f\n",cnt,ofx);
            cnt++;

        }
    }
}
