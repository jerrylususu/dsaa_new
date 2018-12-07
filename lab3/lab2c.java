package lab3;

import java.util.Scanner;

public class lab2c {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int a=1;
        while(a!=0){
            a = in.nextInt();
            if(a==0){
                break;
            }
            String s = in.next();
            char[] arr = s.toCharArray();

            int cur = a;
            int[] check=new int[26];
            int len=arr.length;

            int exit=0;

            for(int i=0;i<len;i++){
                int pos = (int)arr[i] - (int)'A';
                if(check[pos]==0){
                    if(cur==0){
                        exit++;
                        check[pos]--;
                    } else {
                        cur--;
                        check[pos]++;
                    }
                } else if (check[pos]==-1){
                    check[pos]++;
                } else if (check[pos]==1) {
                    check[pos]--;
                    cur++;
                }
            }

            System.out.println(exit);


        }
    }
}
