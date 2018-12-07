package lab5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class kmps {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(true){


        String s = in.next();
        char[] c = s.toCharArray();

            System.out.println(Arrays.toString(myNext(c)));
            System.out.println(Arrays.toString(myOptimizedNext(c)));
            System.out.println(Arrays.toString(myCombinedNext(c)));
            System.out.println(Arrays.toString(TBOldNext(c)));
            System.out.println(Arrays.toString(TBNewNext(c)));
            System.out.println(Arrays.toString(I2ANext(c)));
            System.out.println(Arrays.toString(modifiedNext(c)));
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

    public static int[] TBOldNext(char[] p){
        int m = p.length;
        int[] next = new int[p.length];
        next[0]=-1;
        int k = -1;
        for(int j=1;j<m;j++){
            while(k>=0&&p[k]!=p[j-1]){
                k=next[k];
            }
            k++;
            next[j]=k;
        }
        return next;
    }

    public static int[] TBNewNext(char[] p){
        int m = p.length;
        int[] next = new int[p.length];
        next[0]=-1;
        int k = -1;
        for(int j=0;j<m;j++){
            while(k>0&&p[k]!=p[j-1]){
                k=next[k];
            }
            if(k!=-1&&p[k]==p[j-1]){
                k++;
            }
            next[j]=k;
        }
        return next;
    }

    public static int[] I2ANext(char[] p){
        int m = p.length;
        int next[] = new int[m];
        next[0]=0;
        int k=0;
        for(int q=1;q<m;q++){
            while(k>0&&p[k]!=p[q-1]){
                k=next[k];
            }
            if(p[k]==p[q-1]){
                k=k+1;
            }
            next[q]=k;
        }
        return next;
    }

    public static int[] modifiedNext(char[] p){
        int next[] = new int[p.length+1];
        int k = -1;
        next[0] = -1;
        for(int j=1;j<p.length;j++){
            while(k>=0 && p[k]!=p[j-1]){
                k = next[k];
            }
            k++;
            next[j]=k;
        }
        return next;
    }

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

    // extended FSA
    public static int[][] buildFSA(String s, HashMap<Character,Integer> hm){
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

    public static void printArr(int[][] arr){
        for(int[] i:arr){
            for(int j:i){
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }
}
