package lab2;

import java.util.Scanner;

public class mi1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] split = line.split("\\s+");
        int[] arr = new int[split.length];
        int n = split.length;
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(split[i]);
        }


    }
}
