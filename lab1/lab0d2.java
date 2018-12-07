package lab1;

import java.util.Scanner;

public class lab0d2 {

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

    public static void generate(int a,int b,int c){

        int length = 2*b+2*a+1;
        int height = 2*b+2*c+1;
        char[][] arr = new char[height][length];

        fill(arr,'.');

//        paint front view
        String p1 = "+-";
        String p2 = "|.";
        for(int i=0;i<2*c+1;i++){
            int line = height-1-i;
            for(int j=0;j<2*a+1;j++){
                if(i%2==0){
//                    +-
                    arr[line][j] = p1.charAt(j%2);
                } else {
//                    |.
                    arr[line][j] = p2.charAt(j % 2);
                }
            }

        }
//          paint top view
        for(int i=0;i<2*b;i++){
            int start = 2*b-i;
            String p3 = "+-";
            String p4 = "/.";
            for(int j=0;j<2*a+1;j++){
                if(i%2==0){
                    arr[i][start+j] = p3.charAt(j%2);
                } else {
                    arr[i][start+j] = p4.charAt(j%2);
                }
                print(arr);
                System.out.println();
            }
        }

//        paint side view
        for(int i=0;i<2*b;i++){
            int col=length-1-i;
            int line=i;
            String p5="+|";
            String p6="/.";
            for(int j=0;j<2*c+1;j++){
                if(i%2==0){
                    arr[line+j][col] = p5.charAt(j%2);
                } else {
                    arr[line+j][col] = p6.charAt(j%2);
                }
            }
        }

        print(arr);
    }

    public static void fill(char[][] arr, char tofill){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                arr[i][j] = tofill;
            }
        }
    }

    public static void print(char[][] arr){
        for(char[] i:arr){
            for(char j:i){
                System.out.print(j);
            }
            System.out.println();
        }
    }
}
