package lab3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class lab2f2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();

        while(total-->0) {
            int n = in.nextInt();
            int k = in.nextInt();
            fobj[] arr = new fobj[n];
            for(int i=0;i<n;i++){
                arr[i]=new fobj(i,in.nextInt());
            }
            Arrays.sort(arr, new Comparator<fobj>() {
                @Override
                public int compare(fobj o1, fobj o2) {
                    return o1.s-o2.s;
                }
            });
            HashMap<Integer,Integer> hm = new HashMap<>();
            for(int i=0;i<n;i++){
                hm.put(arr[i].no,i);
            }

            int sum=0;
            int[] tmp = new int[n];
            for(int size=k;size<=n;size++){
                for(int start=0;start<=n-size;start++){
                    for(int i=start,j=0;i<start+size;i++,j++){
                        tmp[j]=arr[hm.get(i)].s;
                        Arrays.sort(tmp,0,size);
                        sum+=tmp[size-k];
                    }
                }
            }
            System.out.println(sum);
        }

    }
}

class fobj{
    int no;
    int s;
    public fobj(int no, int s){
        this.no=no;
        this.s=s;
    }
}
