package lab5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class StringRelated {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(true){
            String s = in.next();
            printArr(buildFSAOld(s,buildChar2Int(s)));
            printArr(buildFSANew(s,buildChar2Int(s)));
//            System.out.println(Arrays.toString(myNext(s.toCharArray())));
//            System.out.println(Arrays.toString(genNextFromFSA(s,buildFSANew(s,buildChar2Int(s)),buildChar2Int(s))));
            String s2 = in.next();
            System.out.println(runFSA(s2,s,buildFSANew(s,buildChar2Int(s)),buildChar2Int(s)));
            System.out.println(runKMP(s2,s,myNext(s.toCharArray())));
            System.out.println(runRK(s2,s));
            System.out.println(runBruteForce(s2,s));
        }
    }

    /**
     * plain old Next array, using while
     * @param p the array to be calculated
     * @return the next array
     */
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

    /**
     * plain old Next, using for
     * @param p the array to be calculated
     * @return the next array
     */
    public static int[] modifiedNext(char[] p){
        int next[] = new int[p.length+1];
        int k = -1;
        next[0] = -1;
        for(int j=1;j<p.length;j++){
            while(k>=0 && p[k]!=p[j-1]){
                k = next[k];
            }
            k++; // if -1
            next[j]=k;
        }
        return next;
    }

    /**
     * optimized next, jump forward
     * @param p the array to be calculated
     * @return the optimized next array
     */
    public static int[] myOptimizedNext(char[] p){
        int[] next = new int[p.length];
        int i=0,j=-1;
        next[0]=-1;
        while (i<p.length-1){
            if(j==-1||p[i]==p[j]){
//                next[i+1]=j+1;
                if(p[i+1]==p[j+1]){
                    next[i+1]=next[j+1];
                } else {
                    next[i+1]=j+1;
                }
                i++;j++;
            }else{
                j=next[j];
            }
        }
        return next;
    }


    /**
     * combining usual next with optimized next to get the extension
     * @param p
     * @return
     */
    public static int[] myCombinedNext(char[] p){
        int[] old = myNext(p);
        int[] n = myOptimizedNext(p);
        System.arraycopy(n,0,old,0,n.length);
        return old;

    }

    public static HashMap<Character,Integer> buildChar2Int(String s){
        int t=0, len=s.length();
        HashMap<Character,Integer> hm = new HashMap<>();
        for(int i=0;i<len;i++){
            if(!hm.containsKey(s.charAt(i))){
                hm.put(s.charAt(i),t);
                t++;
            }
        }
        return hm;
    }

    // extended FSA, using another iter to build len+1
    // FSA source: lab5c.java
    public static int[][] buildFSAOld(String s, HashMap<Character,Integer> hm){
        int len = s.length();
        int width = hm.size();
        int[][] arr = new int[width][len+1];

        for(int i=0;i<len;i++){
            //printArr(arr);
            //System.out.printru'n'f'r'pln();
            int movenext = hm.get(s.charAt(i));
            if(i==0){
                arr[movenext][0] = 1;
            } else {
                arr[movenext][i] = i+1;
                for(int j=0;j<width;j++){
                    if(j==movenext) continue;
                    int tmp=0;
                    for(int k=1;k<i;k++){
                        tmp=arr[hm.get(s.charAt(k))][tmp];
                    }
                    tmp = arr[j][tmp];
                    arr[j][i] = tmp;
                }
            }
        }

        for(int j=0;j<width;j++){
            int tmp=0;
            for(int k=1;k<len;k++){
                tmp=arr[hm.get(s.charAt(k))][tmp];
            }
            tmp = arr[j][tmp];
            arr[j][len] = tmp;
        }

        return arr;
    }

    // extended FSA, internally calculate len+1
    // return: int[char][state]
    public static int[][] buildFSANew(String s, HashMap<Character,Integer> hm){
        int len = s.length();
        int width = hm.size();
        int[][] arr = new int[width][len+1];
        int fail=0;

        for(int i=0;i<=len;i++){
            //printArr(arr);
            //System.out.printru'n'f'r'pln();
            int movenext = 0;
            if(i<len){
                 movenext = hm.get(s.charAt(i));
            } else {
                movenext = -1;
            }

            if(i==0){
                arr[movenext][0] = 1;
            } else {
                if(movenext!=-1){
                    arr[movenext][i] = i+1;
                }

                for(int j=0;j<width;j++){
                    if(j==movenext) continue;
//                    int tmp=0;
//                    for(int k=1;k<i;k++){
//                        tmp=arr[hm.get(s.charAt(k))][tmp];
//                    }
//                    tmp = arr[j][tmp];
//                    arr[j][i] = tmp;

                    arr[j][i] = arr[j][fail];

                }

                if(movenext!=-1)
                fail = arr[movenext][fail];
            }
        }

        return arr;
    }

    public static int[] genNextFromFSA(String s,int[][] arr, HashMap<Character,Integer> hm){
        int[] next = new int[arr[0].length];
        next[0] = -1; // initial
        next[next.length-1]=9999; // mark;
        for(int i=1;i<=s.length();i++){
            int tmp = 0;
            for(int j=1;j<i;j++){
                char c = s.charAt(j);
                tmp=arr[hm.get(s.charAt(j))][tmp];
            }
            next[i]=tmp;
        }
        return next;
    }

    public static int runFSA(String t, String p, int[][] arr, HashMap<Character,Integer> hm){
        int state = 0;
        for(int i=0;i<t.length();i++){
            char c = t.charAt(i);
            int jumpnum = -1;
            if(hm.containsKey(c)){
                jumpnum = hm.get(c);
            }
            if(jumpnum!=-1){
                state = arr[jumpnum][state];
            } else {
                state = 0;
            }
            if(state==p.length()){
                return i-p.length()+1;
            }
        }
        return -1;
    }

    public static int runKMP(String t, String p, int[] next){
        int c1=0,c2=0;
        while(c1<t.length()){
            if(c2==-1||t.charAt(c1)==p.charAt(c2)){
                c1++;c2++;
            } else {
                c2=next[c2];
            }
            if(c2==p.length()){
                return c1-c2;
            }
        }
        return -1;
    }


    // with reference to wikipedia: https://en.wikipedia.org/wiki/Rabin%E2%80%93Karp_algorithm
    // RK: Rabin-Karp
    // idea: rolling hashing
    public static int runRK(String ts, String ps){

        if(ts.length()<ps.length()){
            return -1;
        }

        int mod = 101;
        int base = 256;

        char[] t = ts.toCharArray();
        char[] p = ps.toCharArray();
        int phash = 0, rollhash = 0;
        // step 1: build checksum for p
        // just using ASCII here for convenience
        int multiplier = 1;
        for (int i = 0; i < p.length; i++) {
            int pos = p.length-1-i;
            phash = (phash + (int)p[pos]*multiplier) % mod;
            rollhash = (rollhash + (int)t[pos]*multiplier) % mod;

            // record the base**p_len
            if(i<p.length-1){
                multiplier = (multiplier*base)%mod;
            }
        }

//        System.out.printf("phash=%d, rollhash=%d, multiplier=%d\n",phash,rollhash,multiplier);

        // step 2: roll and check
        for(int i = 0;i<=t.length-p.length;i++){
            if(phash!=rollhash){
                // move to next
                rollhash = (((rollhash + mod - ((int)t[i] * multiplier) % mod) * base + (int)t[i+p.length])) % mod;
//                System.out.println("i="+i+" rollhash="+rollhash);
            } else {
                // detailed check
                int c1=i,c2=0;
                while(c2<p.length){
//                    System.out.println(c1+" "+c2);
                    if(t[c1]==p[c2]){
                        c1++;c2++;
                    } else {
                        break;
                    }
                }

                if(c2==p.length){
                    return i;
                }
            }
        }
        return -1;
    }

    public static int runBruteForce(String ts, String ps){
        if(ts.length()<ps.length()){
            return -1;
        }
        int i=0,j=0;
        while(i<ts.length()&&j<ps.length()){
            if(ts.charAt(i)==ps.charAt(j)){
                i++;j++;
            } else {
                i++;j=0;
            }
        }
        if(j==ps.length()){
            return i-ps.length();
        } else {
            return -1;
        }
    }

//    public static int runKP(String t, String p){
//
//    }

    public static void printArr(int[][] arr){
        for(int[] i:arr){
            for(int j:i){
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }


}
