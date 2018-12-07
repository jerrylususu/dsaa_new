package lab1;

import java.util.Scanner;

public class lab0d {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();

        while(times-->0){
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();

            generate(a,b,c);

        }

    }

    public static void generate(int a, int b, int c){

        if(2*b<2*c+1) {


            for (int i = 0; i < 2 * b; i++) {
                System.out.print(repeat(2 * b - i, '.'));
                if (i % 2 == 0) {
                    System.out.print(diBian(b, "+-"));
                    System.out.print(xieBian(i, ".+"));
                } else {
                    System.out.print(diBian(b, "/."));
                    System.out.print(xieBian(i, "|/"));
                }
                System.out.println();
            }

            for (int i = 0; i < 2 * c + 1 - 2 * b; i++) {
                if (i % 2 == 0) {
                    System.out.print(diBian(b, "+-"));
                    System.out.print(xieBian(2 * b, ".+"));
                } else {
                    System.out.print(diBian(b, "/."));
                    System.out.print(xieBian(2 * b, "|/"));
                }
                System.out.println();
            }


            for (int i = 1; i <= 2 * b; i++) {
                if (i % 2 != 0) {
                    System.out.print(diBian(b, "|."));
                    System.out.print(xieBian(2 * b - i, "/|"));
                } else {
                    System.out.print(diBian(b, "+-"));
                    System.out.print(xieBian(2 * b - i, ".+"));
                }
                System.out.print(repeat(i, '.'));
                System.out.println();
            }

        } else {



        }
    }

    public static String repeat(int a, char c){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<a;i++){
            sb.append(c);
        }
        return sb.toString();
    }

    public static String xieBian(int a,String sp){
        StringBuilder sb = new StringBuilder();
        int len = sp.length();
        for(int i=0;i<a;i++){
            sb.append(sp.charAt(i%len));
        }
        return sb.toString();
    }

    public static String diBian(int a,String sp){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<a;i++){
            sb.append(sp);
        }
        sb.append(sp.charAt(0));
        return sb.toString();
    }
}
