package lab5;

import java.util.*;

public class lab5f4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while(totalnum-->0){
            int n = in.nextInt();
            String[] sarr = new String[n];
            for (int i = 0; i < n; i++) {
                sarr[i] = in.next();
            }

            Arrays.sort(sarr, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.length()-o2.length();
                }
            });

            String s0 = sarr[0];
            HashSet<String> li = new HashSet<>(s0.length()*s0.length());
            for(int i=0;i<=s0.length();i++){
                for(int j=i+1;j<=s0.length();j++){
                    li.add(s0.substring(i,j));
                }
            }
//            System.out.println(li);

            ArrayList<String> res = new ArrayList<>(s0.length()*s0.length());
            for(String s:li){
                int[] next = buildNext(s);
                char[] p = s.toCharArray();
                boolean passall = true;
                for(int i=1;i<n;i++){
                    boolean localok = false;
                    int c1=0,c2=0;
                    char[] t = sarr[i].toCharArray();
                    while(c1<t.length){
                        if(c2==-1||t[c1]==p[c2]){
                            c1++;c2++;
                        } else {
                            c2=next[c2];
                        }
                        if(c2==p.length){
                            localok=true;
                            break;
                        }
                    }
                    if(!localok){
                        passall=false;
                        break;
                    }
                }
                if(passall){
                    res.add(s);
                }
            }
//            System.out.println(res);

            if(res.size()==0){
                System.out.println("Hong");
            } else {
                Collections.sort(res, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        if(o1.length()!=o2.length()){
                            return -o1.length()+o2.length();
                        } else {
                            return o1.compareTo(o2);
                        }
                    }
                });
                System.out.println(res.get(0));
            }




        }
    }
    public static int[] buildNext(String s1){
        char[] p = s1.toCharArray();
        int[] next = new int[p.length + 1];
        next[0] = -1;
        int j = 0, k = -1;

        // build next
        while (j < p.length) {
            if (k == -1 || p[j] == p[k]) {
                next[j + 1] = k + 1; // next[j+1] = next[j]+1
                j++;
                k++;
            } else {
                k = next[k];
            }
        }
        return next;
    }

}
