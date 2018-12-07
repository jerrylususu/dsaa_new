package lab5;

import java.util.Arrays;
import java.util.Scanner;

public class kmpmore {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(true) {
            String s = in.next();
            char[] c = new char[s.length() + 10];
            int[] old = myNext(String.format("%s0", s).toCharArray());
            int[] next = new int[s.length() + 10];
            System.arraycopy(old, 0, next, 0, old.length);
            System.out.println(Arrays.toString(next));
            String s2 = in.next();
            char[] cs = String.format("%s0%s", s, s2).toCharArray();
            System.out.println(Arrays.toString(myNext(String.format("%s0%s", s, s2).toCharArray())));
            int curj = -1,curp=-1;
            for (int curi = old.length; curi <= s.length() + s2.length()+1; curi++) {
                System.out.println(curi);
                curj = next[curi - 1];
                curp=curi-1;
                System.out.println(curi+" "+curp+" "+curj);
                while(curp<curi){
                    if(curj==-1||cs[curp]==cs[curj]){
                        next[curp+1]=curj+1;
                        break;
                    } else {
                        curj=next[curj];
                    }
                }
                System.out.println(Arrays.toString(next));
                System.out.println(s.length()-next[curi]);
            }
        }

    }

    public static int[] myNext(char[] p){
        int[] next = new int[p.length+1];
        next[0]=-1;
        int i=0,j=-1;
        while (i<p.length){
            if(j==-1||p[i]==p[j]){
                next[i+1]=j+1;
                i++;j++;
            }else{
                j=next[j];
            }
//            System.out.println(i);
        }
        return next;
    }
}
