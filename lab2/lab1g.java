package lab2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class lab1g {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int total = in.nextInt();
        Tmp[] arr = new Tmp[size];
        for(int i=0;i<size;i++){
            arr[i] = new Tmp();
            arr[i].credit = in.nextInt();
        }
        for(int i=0;i<size;i++){
            arr[i].score = in.nextInt();
            arr[i].per = arr[i].score/arr[i].credit;
        }
        Arrays.sort(arr, 0, arr.length, new Comparator<Tmp>() {
            @Override
            public int compare(Tmp o1, Tmp o2) {
                return (o1.per>o2.per)?1:-1;
            }
        });
        System.out.println(Arrays.toString(arr));
        double sum1=0,sum2=0;
        for(int i=total;i<size;i++){
            sum1+=arr[i].credit*arr[i].score;
            sum2+=arr[i].credit;
        }
        System.out.printf("%.3f\n",sum1/sum2);
    }
}

class Tmp{
    double score;
    double credit;
    double per;

    @Override
    public String toString() {
        return "Tmp{" +
                "score=" + score +
                ", credit=" + credit +
                ", per=" + per +
                '}';
    }
}