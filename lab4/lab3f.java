package lab4;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class lab3f {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();
        int magic=1000000007;

        while(total-->0){

            int n = in.nextInt();
            int m = in.nextInt();

            long[][][] arr = new long[n][m][m];

            for(int i=0;i<n;i++){
                for(int y=0;y<m;y++){
                    for(int x=0;x<m;x++){
                        arr[i][y][x] = in.nextLong();
                    }
                }
            }

            //output(arr[0],m);
            //output(arr[1],m);

            String s = in.next();
            char[] infix = tokenize(s);
            LinkedList<Character> postfix = infix2postfix(infix);
            //System.out.println(Arrays.toString(infix));
            //System.out.println(postfix);

            int cur = 0;
            int len = postfix.size();
            LinkedList<long[][]> val = new LinkedList<>();

            for(char c:postfix){
                if(48<=c && c<=57){
                    //is matrix
                    int p = c-48;
                    if(p==0){
                        p=10;
                    }
                    val.addLast(arr[p-1]);
                } else {
                    //is op
                    long[][] x1 = val.pollLast();
                    long[][] x2 = val.pollLast();
                    long[][] tmp = new long[m][m];
                    if(c=='+'){
                        for(int y=0;y<m;y++){
                            for(int x=0;x<m;x++){
                                tmp[y][x] = (x2[y][x] + x1[y][x]) %magic;
                            }
                        }
                    } else if (c=='-'){
                        for(int y=0;y<m;y++){
                            for(int x=0;x<m;x++){
                                tmp[y][x] = (x2[y][x] - x1[y][x] + magic)%magic;
                            }
                        }
                    } else if (c=='*'){
                        //output(x1,m);
                        //output(x2,m);
                        for(int y=0;y<m;y++){
                            for(int x=0;x<m;x++){
                                long tmpsum = 0;
                                for(int a=0;a<m;a++){
                                        tmpsum = (tmpsum + (long)x2[y][a] * x1[a][x] % magic)%magic;
                                        //System.out.println(tmpsum);
                                }
                                //System.out.println(tmpsum);
                                tmp[y][x] = tmpsum;
                            }
                        }
                    }
                    val.addLast(tmp);
                }
            }

            long[][] last=val.peekLast();
            output(last,m);

        }
    }

    public static void output(long[][] arr, int m){
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
                if(j!=m-1){
                    System.out.print(arr[i][j]+" ");
                } else {
                    System.out.println(arr[i][j]);
                }
            }
        }
    }

    public static char[] tokenize(String s){
        int cur=0,len=s.length();
        LinkedList<Character> li = new LinkedList<>();
        while(cur<len){
            if(s.charAt(cur)!='1'){
                li.add(s.charAt(cur));
            } else{
                if(cur+1<len&&s.charAt(cur+1)!='0'){
                    li.add(s.charAt(cur));
                } else {
                    if(cur+1>=len||cur==len-1){
                        li.add(s.charAt(cur));
                    } else {
                        li.add('0');
                        cur++;
                    }

                }
            }
            cur++;
        }
        char[] arr = new char[li.size()];
        int tmp=0;
        for(char c:li){
            arr[tmp]=c;
            tmp++;
        }
        return arr;
    }

    public static LinkedList<Character> infix2postfix(char[] infix){
        LinkedList<Character> op = new LinkedList<>();
        LinkedList<Character> post = new LinkedList<>();
        int len = infix.length;
        for(int i=0;i<len;i++){
            //System.out.println(post);
            char c = infix[i];
            if(48<=c && c<=57){
                //is digit
                post.addLast(c);
            } else {
                //is op
                if(c=='('){
                    //process operand
                    op.addLast(c);
                } else if (c==')'){
                    while(op.size()>=1&&op.peekLast()!='('){
                        post.addLast(op.removeLast());
                    }
                    op.removeLast();
                } else {
                    if(c=='+'||c=='-'){
                        while (op.size()>=1&&op.peekLast()!='('&&op.peekLast()!=')'){
                            post.addLast(op.removeLast());
                        }
                        op.addLast(c);
                    } else if (c=='*'){
                        while(op.size()>=1&&op.peekLast()!='('&&op.peekLast()!=')'&&op.peekLast()!='+'&&op.peekLast()!='-'){
                            post.addLast(op.removeLast());
                        }
                        op.addLast(c);
                    }
                }
            }
        }
        while(op.size()>=1){
            post.addLast(op.removeLast());
        }
        return post;
    }
}


