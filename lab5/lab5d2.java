package lab5;

import java.util.Arrays;
import java.util.Scanner;

public class lab5d2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while(totalnum-->0){
            int len = in.nextInt();
            String s = in.next();
            char[] carr = s.toCharArray();
            int[] next = myNext(carr);
//            System.out.println(Arrays.toString(next));
            int maxlen = next[next.length-1];

            // go reverse from max length
            boolean check = false;
            int j=maxlen;
            int rec=-1; // record the result
            for(;j>0;j--){
                boolean possible = false;

                // do kmp in the middle
                int c1=j, c2=0;
                while(c1<s.length()-j){
                    if(c2==-1||carr[c1]==carr[c2]){
                        c1++;c2++;
                    } else {
                        c2=next[c2];
                    }
                    if(c2==j){ // success full match
                        if(c1<=s.length()-j){ // c1 already added.
                            rec = j;
                            possible = true;
                            break;
                        }
                    }
                }

                // make sure the current prefix = suffix
                if(possible){
                    for(int i=0;i<rec;i++){
                        int l = i;
                        int r = s.length()-rec+i;
                        if(carr[i]!=carr[r]){
                            possible = false;
                            break;
                        }
                    }
                }

                // passed check, exit
                if(possible){
                    check=true;
                    break;
                }
            }

            if(check&&rec>0){
                System.out.println(rec);
            } else {
                System.out.println(0);
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
