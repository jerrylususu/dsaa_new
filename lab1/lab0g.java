package lab1;

import java.util.Scanner;

public class lab0g {

    final static int[][] comb={{1,2,3},{1,3,2},{2,1,3},{2,3,1},{3,1,2},{3,2,1}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times= in.nextInt();

        while(times-->0){
            double[] arr = new double[3];
            for(int i=0;i<3;i++){
                arr[i] = in.nextDouble();
            }
            double x = in.nextDouble();
            double y = in.nextDouble();

            boolean typea = false;
            boolean typeb = false;

            boolean ok = false;

            for(int[] one:comb){
//                System.out.println(Arrays.toString(one));
                double a = arr[one[0]-1];
                double b = arr[one[1]-1];
                double c = arr[one[2]-1];

    //            type a
                double alen1 = 2*a+2*c;
                double alen2 = 2*c+b;

                if((x>=alen1&&y>=alen2)||(y>=alen1&&x>=alen2)){
                    typea=true;
                    ok=true;
                    break;
                }

    //            type b
                double blen1 = 2*c+a;
                double blen2 = 2*c+2*b;

                if((x>=blen1&&y>=blen2)||(y>=blen1&&x>=blen2)){
                    typeb=true;
                    ok=true;
                    break;
                }

                double clen1 = 2*a+c;
                double clen2 = 2*c+2*b;

                if((x>=clen1&&y>=clen2)||(y>=clen1&&x>=clen2)){
                    ok=true;
                    break;
                }

                double dlen1 = 2*a+b;
                double dlen2 = 2*c+2*a;

                if((x>=dlen1&&y>=dlen2)||(y>=dlen1&&x>=dlen2)){
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
}
