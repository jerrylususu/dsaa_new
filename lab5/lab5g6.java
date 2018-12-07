package lab5;

import java.util.Arrays;
import java.util.Scanner;

public class lab5g6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        char[][] sarr = new char[n][];
        int allMinLen = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            sarr[i] = in.next().toCharArray();
            if(allMinLen>sarr[i].length){
                allMinLen = sarr[i].length;
            }
        }
        String op = in.next();
        char[] ops = op.toCharArray();
//        for(int i=0;i<n;i++){
//            System.out.println(Arrays.toString(optimazedNext(sarr[i])));
//            System.out.println(Arrays.toString(generateNext(String.valueOf(sarr[i]))));
//            System.out.println(Arrays.toString(generateNext2(String.valueOf(sarr[i]))));
//            System.out.println(Arrays.toString(combineNext(String.valueOf(sarr[i]))));
//        }
        // pre-process
        int[][] nexts = new int[n][];
        for(int i=0;i<n;i++){
//            nexts[i] = optimazedNext(sarr[i]);
            nexts[i] = combineNext(String.valueOf(sarr[i]));
        }

        // calculate
        int[] mins = new int[ops.length];
        Arrays.fill(mins,Integer.MAX_VALUE);
        for(int i=0;i<n;i++){
            // iterate through arrays
            int pattern_cur = 0, text_cur=0;
            int[] alreadysteps = new int[ops.length];
            char[] pattern = sarr[i];
            int[] currentnext = nexts[i];
            for(int operation_cur=0;operation_cur<ops.length;operation_cur++){
//                System.out.println(operation_cur);
                // for each operation
                if(ops[operation_cur]=='-'){
                    // is backspace
                    if(text_cur>0){
                        text_cur--;
                    }
                    pattern_cur = alreadysteps[text_cur];
                } else {
                    // is alphabet
                    if(pattern_cur<pattern.length){
                        while(pattern_cur!=0 && ops[operation_cur]!=pattern[pattern_cur]){
                            pattern_cur=currentnext[pattern_cur];
                        }
                    } else {
                        pattern_cur = 0;
                    }

                    if(ops[operation_cur]==pattern[pattern_cur]){
                        pattern_cur++;
                    }
                    text_cur++;
                    alreadysteps[text_cur]=pattern_cur;
                }
                if(pattern.length-alreadysteps[text_cur]<mins[operation_cur]){
                    mins[operation_cur] = pattern.length-alreadysteps[text_cur];
                }
            }
        }

        // output
        System.out.println(allMinLen);
        for(int i=0;i<mins.length;i++){
            System.out.println(mins[i]);
        }
    }

    public static int[] optimazedNext(char[] p){
        int[] oldnext = new int[p.length+1];
        int[] newnext = new int[p.length+1];
        for(int i=1;i<p.length;i++){
            int j = oldnext[i];
            while(j!=0 && p[i]!=p[j]){
                // keep to find the same or jump out
                j=oldnext[j];
            }
            if(p[i]==p[j]){
                // go forward
                newnext[i+1] = j+1;
            } else {
                newnext[i+1] = 0;
            }
            oldnext[i+1] = newnext[i+1];
//            System.out.println(i);

            if(i!=p.length-1){
//                System.out.printf("i=%d,j=%d,P[i+1]=%c,P[j+1]=%c\n",i,j,p[i+1],p[j+1]);
                if(newnext[i+1]==j+1 && p[i+1]==p[j+1]){
//                    System.out.printf("f[i+1]=%d,j+1=%d,P[i+1]=%c,P[j+1]=%c\n",newnext[i+1],j+1,p[i+1],p[j+1]);
                    newnext[i+1]=newnext[j+1];
                }
            }

        }
        return  newnext;
    }

    public static int[] combineNext(String s){
        int[] old = generateNext2(s);
        int[] optimized = generateNext(s);
        System.arraycopy(optimized,0,old,0,optimized.length);
        for (int i = 0; i < old.length; i++) {
            if(old[i]==-1){
                old[i]=0;
            }
        }
        return old;
    }

    public static int[] generateNext(String s){
        char[] p = s.toCharArray();
        int[] next = new int[p.length+1];
        next[0] = -1;
        int j=1, k=-1;
        while(j<p.length-1){
            if(k==-1||p[j]==p[k]){
//                 next[j]=k
                if(p[j+1]==p[k+1]){ // if the same, if failed, must be failed again
                    next[j+1] = next[k+1]; // just jump and go deeper 1 layer
                } else {
                    next[j+1] = k + 1; // next[j+1] = next[j]+1
                }
                j++;
                k++;

//                if(p[++j]==p[++k]){ // if the same, if failed, must be failed again
//                    next[j] = next[k]; // just jump and move forward
//                } else {
//                    next[j] = k; // next[j+1] = next[j]+1
//                }

//                next[j+1] = k + 1;
//                j++;
//                k++;
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

    public static int[] generateNext2(String s){
        char[] p = s.toCharArray();
        int[] next = new int[p.length+1];
        next[0] = -1;
        int j=1, k=-1;
        while(j<p.length){
            if(k==-1||p[j]==p[k]){
//                 next[j]=k
//                if(p[j+1]==p[k+1]){ // if the same, if failed, must be failed again
//                    next[j+1] = next[k+1]; // just jump and go deeper 1 layer
//                } else {
//                    next[j+1] = k + 1; // next[j+1] = next[j]+1
//                }
//                j++;
//                k++;

//                if(p[++j]==p[++k]){ // if the same, if failed, must be failed again
//                    next[j] = next[k]; // just jump and move forward
//                } else {
//                    next[j] = k; // next[j+1] = next[j]+1
//                }

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
