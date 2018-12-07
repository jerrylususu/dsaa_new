package lab5;

import java.util.*;

public class lab5f3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totaln = in.nextInt();

        while(totaln-->0) {
            // input
            int n = in.nextInt();
            String[] sarr = new String[n];
            for (int i = 0; i < n; i++) {
                sarr[i] = in.next();
            }

            // sort
            Arrays.sort(sarr, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.length() - o2.length();
                }
            });
//            System.out.println(Arrays.toString(sarr));

            String ts=null;
            Set<String> li = new HashSet<>();
            Set<String> oldli = new HashSet<>();
            oldli.add(sarr[0]);
            for (int i = 1; i <n; i++) {
                ts=sarr[i];

                char[] p1 = ts.toCharArray();
                for(String ps:oldli){
                    char[] p2 = ps.toCharArray();
                    int[][] dp = new int[p1.length+1][p2.length+1];
                    int max=0, s1=0, s2=0;
                    Set<String> tmpli = new HashSet<>();
                    for(int c1=0;c1<=p1.length;c1++){
                        for(int c2=0;c2<=p2.length;c2++){
                            if(c1==0||c2==0){
                                dp[c1][c2]=0;
                            } else if (p1[c1-1]==p2[c2-1]){
                                dp[c1][c2]=dp[c1-1][c2-1]+1;
                                if(dp[c1][c2]>max){
//                                    tmpli.clear();
                                    max = dp[c1][c2];
                                    s1=c1;
                                    s2=c2;
                                    tmpli.add(String.valueOf(p1).substring(c1-max,c1));
                                } else if (dp[c1][c2]==max&&c1!=s1){
                                    tmpli.add(String.valueOf(p1).substring(c1-max,c1));
                                }
                            } else {
                                dp[c1][c2]=0;
                            }
                        }
                    }
                    System.out.println(tmpli);
//                    System.out.println(max+" "+s1+" "+s2);
                    ps = String.valueOf(p1).substring(s1-max,s1);
                    li.addAll(tmpli);
//                    System.out.println(ps);
                }
                oldli.clear();
                oldli.addAll(li);
//                System.out.println(oldli);
                li.clear();
//                System.out.println(li);
                if(oldli.size()==0){
                    break;
                }
            }
            System.out.println(oldli);
            if(oldli.size()>0){
                ArrayList<String> endli = new ArrayList<>(oldli.size());
                for(String s:oldli){
                    endli.add(s);
                }
                Collections.sort(endli, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        if(o1.length()!=o2.length()){
                            return o2.length()-o1.length();
                        } else {
                            return o1.compareTo(o2);
                        }

                    }
                });
                System.out.println(endli.get(0));
            } else {
                System.out.println("Hong");
            }


        }
    }
}
