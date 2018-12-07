package lab2;

import java.util.Scanner;

public class lab1f {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        int cnt = 1;

        while(times-->0){
            int total = in.nextInt();
            double[] pos = new double[total];
            double[] weight = new double[total];

            for(int i=0;i<total;i++){
                pos[i] = in.nextDouble();
                weight[i] = in.nextDouble();
            }

            double left = pos[0], right = pos[total-1];
            double mid = 0, midmid = 0;

            while(right-left>1e-4){
                mid = left + (right-left)/2;
                midmid = mid + (right-mid)/2;

                if(calc(mid,pos,weight)<calc(midmid,pos,weight)){
                    right = midmid;
                } else {
                    left = mid;
                }
            }

            System.out.printf("Case #%d: %.0f\n",cnt,calc(mid,pos,weight));
            cnt++;
        }
    }

    public static double calc(double p, double[] pos, double[] weight){
        double sum = 0;
        double size = pos.length;
        for(int i=0;i<size;i++){
            sum += Math.pow(Math.abs(pos[i]-p),3)*weight[i];
        }
        return sum;
    }
}
