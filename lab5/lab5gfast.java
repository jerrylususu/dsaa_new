package lab5;





import java.io.*;
        import java.math.*;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab5gfast {
    public static void main(String[] args){
        InputStream inputStream = System.in;//new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }
    static class Task {
        public void solve(InputReader in, PrintWriter out) {
            // getting input
//            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            String[] sarr = new String[n];
            for (int i = 0; i < n; i++) {
                sarr[i] = in.next();
            }
            int[][] nexts = new int[n][];
            for (int i = 0; i < n; i++) {
                nexts[i] = buildNext(sarr[i]);
            }
            String ops = in.next();
            char[] op = ops.toCharArray();

            // do the calc
            int totalop = op.length;
            char[] t = new char[totalop];
            int cur = -1;
            int min = Integer.MAX_VALUE;
            Stack<Integer> stack = new Stack<>();
            for(int i=0;i<n;i++){
                if(sarr[i].length()<min){
                    min=sarr[i].length();
                }
            }
            stack.push(min);
            int minlen = min;
            min =  Integer.MAX_VALUE;
            System.out.println(stack.peek());

            for (int i = 0; i < totalop; i++) {
//            System.out.println(Arrays.toString(t));
                // do operation
                if(op[i]=='-'){
                    if(cur!=-1){
                        cur--;
                        stack.pop();
                    }
                    // backspace

                } else {
                    // alphabet
                    cur++;
                    t[cur]=op[i];

                    for(int j=0;j<n;j++){
                        // go over current next
//                        int c1=cur-2*sarr[j].length()>=0?cur-2*sarr[j].length():0,c2=0;
                        int c1=0,c2=0;
                        while(c1<=cur){
                            if(c2==-1||t[c1]==sarr[j].charAt(c2)){
                                c1++;c2++;
                            } else {
                                c2 = nexts[j][c2];
                            }
                            if(c2==sarr[j].length()){
                                break;
                            }
                        }
//                    System.out.println(sarr[j].length()+" "+c2);
                        int thisleft = sarr[j].length()-c2;
                        if(thisleft<min){
                            min=thisleft;
                        }
                    }

                    stack.push(min);
                    min=Integer.MAX_VALUE;
                }

                if(stack.isEmpty()){
                    stack.push(minlen);
                }
                System.out.println(String.valueOf(t));
                System.out.println(stack.peek());
            }
        }

    }
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
        public int nextInt() {
            return Integer.parseInt(next());
        }
        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        //         public boolean hasNext() {
//             try {
//                 return reader.ready();
//             } catch(IOException e) {
//                 throw new RuntimeException(e);
//             }
//         }
        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch(IOException e) {
                return false;
            }
        }
        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }
    }
    public static int[] buildNext(String s1){
        char[] p = s1.toCharArray();
        int[] next = new int[p.length+1];
        next[0] = -1;
        int j = 0, k = -1;

        // build next
        while (j < p.length) {
            if (k == -1 || p[j] == p[k]) {
                next[j + 1] = k + 1; // next[j+1] = next[j]+1
//                if(p[j+1]==p[k+1]){
//                    next[j+1]=next[k+1];
//                }else{
//                    next[j+1]=k+1;
//                }
                j++;k++;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
