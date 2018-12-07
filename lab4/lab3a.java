package lab4;

import java.util.Scanner;

public class lab3a {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();
        char[] p = {'l','a','n','r','a','n'};

        while(total-->0){
            String s =in.next();
            int c = 0;
            int len = s.length();
            boolean ok = false;
            for(int i=0;i<len;i++) {
                if (s.charAt(i) == p[c]) {
                    c++;
                    if (c == 6) {
                        ok = true;
                        break;
                    }
                }
            }
            System.out.println(ok?"YES":"NO");
        }
    }
}
