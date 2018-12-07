package labbonus;

import java.util.Scanner;

public class lab6a {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while(totalnum-->0){
            int n = in.nextInt();
            int max = Integer.MIN_VALUE;
            for(int i=0;i<n;i++){
                int tmp =in.nextInt();
                if(tmp>max){
                    max=tmp;
                }
            }
            System.out.println(max);
        }
    }

}
