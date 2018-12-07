package lab1;

import java.util.Scanner;

public class lab0g2 {

    final static int[][] comb={{1,2,3},{1,3,2},{2,1,3},{2,3,1},{3,1,2},{3,2,1}};
    final static int[][] judges={{2,0,2,0,1,2},{2,0,2,1,1,1},{2,0,2,2,1,0},{1,1,2,1,1,1},{1,0,2,0,2,2},{1,0,1,1,3,1},{1,1,1,0,2,2},{1,2,0,0,2,2}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();

        while (times-- > 0) {
            double[] arr = new double[3];
            for(int i=0;i<3;i++){
                arr[i] = in.nextDouble();
            }
            double x = in.nextDouble();
            double y = in.nextDouble();

            boolean ok = false;

            for(int[] one:comb){
                double a = arr[one[0]-1];
                double b = arr[one[1]-1];
                double c = arr[one[2]-1];

                if(multijudge(a,b,c,x,y)){
                    ok=true;
                    break;
                }
            }

            if(ok){
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }

    }

    public static boolean multijudge(double a,double b,double c,double x,double y){
        for(int[] j:judges){
            if(judge(j[0],j[1],j[2],j[3],j[4],j[5],a,b,c,x,y)){
                return true;
            }
        }
        return false;
    }

    public static boolean judge(double ax,double bx,double cx,double ay,double by,double cy,double a,double b,double c,double x,double y){
        double xr = ax*a+bx*b+cx*c;
        double yr = ay*a+by*b+cy*c;

        if((x>=xr&&y>=yr)||(x>=yr&&y>=xr)){
            return true;
        } else {
            return false;
        }
    }
}
