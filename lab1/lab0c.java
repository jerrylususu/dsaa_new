package lab1;

import java.util.Scanner;

public class lab0c {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        while(times-->0){

            int x = in.nextInt();
            int y = in.nextInt();

            if(x==1&&y==1){
                System.out.println("Bob");
            } else {
                System.out.println("Alice");
            }

        }
    }
}
