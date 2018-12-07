package lab5;

import java.util.Scanner;

public class lab5b {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();

        while(total-->0){
            int l1 = in.nextInt();
            int l2 = in.nextInt();

            char[] s1 = in.next().toCharArray();
            char[] s2 = in.next().toCharArray();

            int pos = -1;
            for(int i=0;i<l1;i++){
                if(s1[i]=='*'){
                    pos = i; break;
                }
            }

            boolean ok = true;

            if(pos==-1){
                // not exist *
                if(l1!=l2){
                    ok=false; break;
                }
                for(int c=0;c<l1;c++){
                    if(s1[c]!=s2[c]){
                        ok=false; break;
                    }
                }
            } else {
                // exist *
                if(pos==0){
                    // at start
                    for(int c=0;c<l1-1;c++){
                        int p1 = l1-1-c;
                        int p2 = l2-1-c;
                        //System.out.println(s1[p1]+" "+s2[p2]);
                        if(s1[p1]!=s2[p2]){
                            ok=false; break;
                        }
                    }
                } else if (pos==l1-1){
                    // at end
                    for(int c=0;c<l1-1;c++){
                        //System.out.println(s1[c]+" "+s2[c]);
                        if(s1[c]!=s2[c]){
                            ok=false; break;
                        }
                    }
                } else {
                    // at middle
                    int e1 = pos-1, e2 = pos+1;
                    for(int c=0;c<=e1;c++){
                        if(s1[c]!=s2[c]){
                            ok=false; break;
                        }
                    }
                    for(int c=0;c<l1-e2;c++){
                        int p1 = l1-1-c;
                        int p2 = l2-1-c;
                        //System.out.println(s1[p1]+" "+s2[p2]);
                        if(s1[p1]!=s2[p2]){
                            ok=false; break;
                        }
                    }
                }
            }

            System.out.println(ok?"YES":"NO");

        }
    }
}
