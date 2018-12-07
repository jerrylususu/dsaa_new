package labbonus;

import java.util.Arrays;
import java.util.Scanner;

public class lab6old {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while (totalnum-- > 0) {
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            int size = (n * (n - 1)) / 2;
            if(size<=0){
                System.out.println(0);
            } else {
                int[] diff = new int[size];
                int it = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = i + 1; j < n; j++) {
                        diff[it] = Math.abs(arr[j] - arr[i]);
                        it++;
                    }
                }

                Arrays.sort(diff);
//            System.out.println(Arrays.toString(diff));
                int ask = -1;
                if (it % 2 == 0) {
                    ask = size / 2 - 1;
                } else {
                    ask = (size - 1) / 2;
                }

                if (size == 0) {
                    System.out.println(0);
                } else {
                    System.out.println(diff[ask]);
                }
            }

        }
    }
}
