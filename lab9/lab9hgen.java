package lab9;

import java.util.Random;

public class lab9hgen {
    public static void main(String[] args) {
        int n = 100;
        Random r = new Random();

        for (int i = 0; i < n; i++) {
            System.out.printf("%d %d %d %d%n"
                    ,r.nextInt(1000),r.nextInt(1000),r.nextInt(1000),1);
        }
        System.out.printf("%d %d %d%n"
                ,r.nextInt(1000),r.nextInt(1000),r.nextInt(1000));
        System.out.printf("%d %d %d%n"
                ,r.nextInt(1000),r.nextInt(1000),r.nextInt(1000));

    }
}
