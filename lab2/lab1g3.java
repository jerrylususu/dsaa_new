package lab2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class lab1g3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();
            int k = in.nextInt();

            int[] s = new int[n]; //score
            int[] c = new int[n]; //credit
            double max = 0;

            for(int i=0;i<n;i++){
                c[i] = in.nextInt();
            }
            for(int i=0;i<n;i++){
                s[i] = in.nextInt();
                if(s[i]*1.0/c[i]>max){
                    max=s[i]*1.0/c[i];
                }
            }

            double left=0,right=max,mid=0;
            while(right-left>1e-6){
                mid=left+(right-left)/2;
                boolean ck = check(mid,s,c,k);
                if(ck){
                    left=mid;
                }else{
                    right=mid;
                }
            }

            System.out.println(mid);

        }
    }

    public static boolean check(double m, int[] s,int[] c, int k){
        double s1=0,s2=0;
        int size=s.length;
        STmp[] arr = new STmp[size];
        for(int i=0;i<size;i++){
            arr[i] = new STmp();
            arr[i].id = i;
            arr[i].res = c[i]*(s[i]-m);
        }
        Arrays.sort(arr, new Comparator<STmp>() {
            @Override
            public int compare(STmp o1, STmp o2) {
                return (o1.res>o2.res)?1:0;
            }
        });
        System.out.println(Arrays.toString(arr));
        for(int i=k;i<size;i++){
            s1+=c[i]*s[i];
            s2+=c[i];
        }
        return (s1-s2>=m);
    }

}

class STmp{
    double res;
    int id;

    @Override
    public String toString() {
        return "STmp{" +
                "res=" + res +
                ", id=" + id +
                '}';
    }
}
