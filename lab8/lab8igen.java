//package lab8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class lab8igen {
    public static void main(String[] args) {
//        int i=0,w=1000000000;
//        System.out.println(1+" "+100001+" "+100000);
//        for (int j = 1; j <= 100000; j++) {
//            System.out.println(j+" "+(j+1)+" "+w);
//        }
        String head = "21 26\n";

        String s =
                "1 2 12\n" +
                        "1 3 100000001\n" +
                        "11 3 100000001\n" +
                        "10 11 100000001\n" +
                        "12 10 100000001\n" +
                        "12 13 100000001\n" +
                        "2 4 100000007\n" +
                        "2 7 100000001\n" +
                        "10 14 100000001\n" +
                        "13 14 100000001\n" +
                        "4 5 100000001\n" +
                        "7 6 100000001\n" +
                        "5 6 100000001\n" +
                        "6 8 100000001\n" +
                        "6 9 100000001\n" +
                        "8 9 100000001\n" +
                        "14 15 100000001\n" +
                        "14 16 100000001\n" +
                        "16 17 100000001\n" +
                        "16 18 100000001\n" +
                        "18 19 100000001\n" +
                        "19 20 100000001\n" +
                        "20 21 100000001\n" +
                        "17 21 100000001\n" +
                        "2 3 100000001\n" +
                        "3 10 100000001\n";

        String[] arr = s.split("\n");
//        System.out.println(arr.length);

        int size = 100;
        System.out.println(size);
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            ArrayList<String> li = new ArrayList<>();
            li.addAll(Arrays.asList(arr));
            LinkedList<String> li2 = new LinkedList<>();
            while(li.size()!=0){
                int pos = r.nextInt(li.size());
                li2.addLast(li.remove(pos));
            }
            System.out.println(head);
            for(String ss:li2){
                System.out.println(ss);
            }
        }

    }
}
