package lab5;

import java.util.Scanner;

public class lab5a {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();

        while(total-->0){
            int n = in.nextInt();
            int max=0, cur=0;
            char c = 'a';
            for(int i=0;i<n;i++){
                String s = in.next();
                if(i==0){
                    c = s.charAt(s.length()-1);
                    cur++;
                    max++;
                } else {
                    if(s.charAt(s.length()-1)==c){
                        cur++;
                    } else {
                        if(cur>max){
                            max=cur;
                        }

                        cur=1;
                    }
                }
            }
            if(cur>max){
                max=cur;
            }
            System.out.println(max);


        }
    }
}
