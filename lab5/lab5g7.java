package lab5;

import java.util.Scanner;
import java.util.Stack;

public class lab5g7 {
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

        int[][] nexts = new int[n][];
        for (int i = 0; i < n; i++) {
            nexts[i] = combineNext(String.valueOf(sarr[i]));
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(allMinLen);
        System.out.println(stack.peek());
        int cur = -1, size = 0;
        char[] t = new char[ops.length];
        for(int i=0;i<ops.length;i++){
            if(ops[i]=='-'){
                if(size>0){
                    cur--;
                    size--;
                    stack.pop();
                }
            } else {
                cur++;
                size++;
                t[cur]=ops[i];
                int localmin = Integer.MAX_VALUE;
                for(int j=0;j<n;j++){
                    char[] p = sarr[j];
                    int c1 = size-sarr[j].length>0?size-sarr[j].length:0;
                    int c2 = 0;
                    int[] next = nexts[j];
                    while(c1<size&&c2<p.length){
//                        System.out.println(c1);
                        if(c2==-1||t[c1]==p[c2]){
                            c1++;c2++;
                        } else {
                            c2=next[c2];
                        }
//
//                        while(c2!=0&&t[c1]!=p[c2]){
//                            c2=next[c2];
//                        }
//                        if(t[c1]==p[c2]){
//                            c1++;c2++;
//                        }
                        if(c2==p.length){
                            break;
                        }
                    }
//                    System.out.println(String.valueOf(sarr[j])+" "+c2);
                    int tmp = p.length-c2;
                    if(tmp<localmin){
                        localmin=tmp;
                    }
                }
                stack.push(localmin);
            }
            System.out.println(stack.peek());
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
//        for (int i = 0; i < old.length; i++) {
//            if(old[i]==-1){
//                old[i]=0;
//            }
//        }
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
