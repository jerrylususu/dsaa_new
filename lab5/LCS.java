package lab5;

import java.util.*;

public class LCS {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] p1 = in.next().toCharArray();
        char[] p2 = in.next().toCharArray();

        int[][] dp = new int[p1.length+1][p2.length+1];
        int max=0, s1=0, s2=0;
        ArrayList<String> li = new ArrayList<>();
        for(int c1=0;c1<=p1.length;c1++){
            for(int c2=0;c2<=p2.length;c2++){
                if(c1==0||c2==0){
                    dp[c1][c2]=0;
                } else if (p1[c1-1]==p2[c2-1]){
                    dp[c1][c2]=dp[c1-1][c2-1]+1;
                    if(dp[c1][c2]>max){
                        li.clear();
                        max = dp[c1][c2];
                        s1=c1;
                        s2=c2;
//                        System.out.println(1+" "+c1+" "+c2);
                        li.add(String.valueOf(p1).substring(s1-max,s1));
                    } else if (dp[c1][c2]==max&&c1!=s1){
//                        System.out.println(2+" "+c1+" "+c2);
                        li.add(String.valueOf(p1).substring(c1-max,c1));
//                        System.out.println(li);
                    }
                } else {
                    dp[c1][c2]=0;
                }
            }
        }

        System.out.println(max);
        Collections.sort(li);
        System.out.println(li);

//        StringBuilder sb = new StringBuilder();
//        for(int i=0;i<max;i++){
//            sb.insert(0,p1[s1-i-1]);
//        }
//        System.out.println(sb);
//        System.out.println(String.valueOf(p1).substring(s1-max,s1));
    }
}
