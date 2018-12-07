package lab5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class lab5f2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        int totaln = in.nextInt();
//
//        while(totaln-->0){
//            // input
//            int n = in.nextInt();
//            String[] sarr = new String[n];
//            for (int i = 0; i < n; i++) {
//                sarr[i] = in.next();
//            }
//            Arrays.sort(sarr, new Comparator<String>() {
//                @Override
//                public int compare(String o1, String o2) {
//                    return o1.length()-o2.length();
//                }
//            });
//            System.out.println(Arrays.toString(sarr));
//
//            boolean empty = false;
//            // process one by one
//            String ts =null, ps=sarr[0];
//            for(int i=1;i<n;i++){
//                ts = sarr[i];
//
//                if(ts.length()<ps.length()){
//                    // switch if length is strange
//                    String tmp = ts;
//                    ts = ps;
//                    ps = tmp;
//                }
//
//                // do match
//                int[] next = generateNext(ps);
//                int rec=0, rp=0;
//                int c1=0, c2=0;
//                char[] t = ts.toCharArray();
//                char[] p = ps.toCharArray();
//                while(c1<t.length){
//                    if(c2==-1||t[c1]==p[c2]){
//                        c1++;c2++;
//                    } else {
//                        c2=next[c2];
//                    }
//                    if(c2>rec){
//                        rec=c2;
//                        rp=c1;
//                    }
//                    if(c2==p.length){
//                        rec=c2;
//                        rp=c1;
//                        break;
//                    }
//                }
//
//                System.out.println(ps);
//                System.out.println(rp+" "+rec);
//
//                ps = ps.substring(rp-rec,rp);
//            }
//
//            if(empty){
//                System.out.println("Hong");
//            } else {
//                System.out.println(ps);
//            }
//
//        }



        String s = in.next();
        int[] arr = generateNext(s);
        System.out.println(Arrays.toString(arr));

        String s2 = in.next();
        int cnt=0, rec=0, rp=0;
        int c1=0,c2=0;
        char[] t = s2.toCharArray();
        char[] p = s.toCharArray();
        while(c1<t.length){
            if(c2==-1||t[c1]==p[c2]){
                c1++;c2++;
            } else {
                c2=arr[c2];
            }
            if(c2>rec){
                rec=c2;
                rp=c1;
            }
            if(c2==p.length){
                rec=c2;
                rp=c1;
                break;
            }
        }
        System.out.println(rec+" "+rp);
        System.out.println(s2.substring(rp-rec,rp));
    }

    public static int[] generateNext(String s){
        char[] p = s.toCharArray();
        int[] next = new int[p.length+1];
        next[0] = -1;
        int j=1, k=-1;
        while(j<p.length-1){
            if(k==-1||p[j]==p[k]){
                next[j+1] = k + 1;
                j++;
                k++;
            } else {
                k = next[k];
            }
        }

        k=next[p.length-1];
        while(true){
            if(k==-1||p[p.length-1]==p[k]){
                next[p.length] = k+1;
                break;
            } else {
                k = next[k];
            }
        }

        return next;
    }
}
