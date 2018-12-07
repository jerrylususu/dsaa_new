package lab5;

import java.util.Scanner;

public class lab5f {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while(totalnum-->0){
            int n = in.nextInt();
            String[] sarr = new String[n];
            for(int i=0;i<n;i++){
                sarr[i] = in.next();
            }

            String cursame = null; // the longest same substring current
            for(int i=1;i<n;i++){
                String s1 = null, s2 = null;
                int l1 = -1, l2 = -1;
                if(i==1){
                    s1 = sarr[i]; s2 = sarr[i-1];
                    l1 = s1.length(); l2 = s2.length();
                } else {
                    s1 = sarr[i]; s2 = cursame;
                    l1 = s1.length(); l2 = s2.length();
                }
                //System.out.println(s1+" "+s2);

                int[][] dp = new int[l1+1][l2+1];
                int max=0;
                int e2=0; // end of same substring in s2

                for(int c1=0;c1<=l1;c1++){
                    for(int c2=0;c2<=l2;c2++){
                        if(c1==0||c2==0){
                            // boundary
                            dp[c1][c2]=0;
                        } else if (s1.charAt(c1-1)==s2.charAt(c2-1)){
                            // same
                            dp[c1][c2]=dp[c1-1][c2-1]+1;
                            if(dp[c1][c2]>max){
                                max=dp[c1][c2];
//                                System.out.println(c1+" "+c2+" "+max);
                                e2=c2;
                            }
                        } else {
                            // different
                            dp[c1][c2]=0;
                        }
                    }
                }

//                System.out.println(e2+" "+max);

//                System.out.println(s2);
//                if(e2==s2.length()-1&&max==s2.length()){
//                    cursame = s2.substring(e2-max+1,e2+1);
//                } else {
//                    cursame = s2.substring(e2-max-1,e2-1);
//                }
                if(e2==s2.length()&&max<s2.length()){
                    e2--;
                }
                cursame = s2.substring(e2-max,e2);
                //System.out.println(cursame);

            }

            //System.out.println(cursame.length());
            System.out.println(cursame);
        }
    }
}
