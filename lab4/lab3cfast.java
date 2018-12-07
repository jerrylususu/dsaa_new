package lab4;


import java.io.*;
import java.math.*;
import java.util.StringTokenizer;
/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab3cfast {
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
            int total = in.nextInt();

            while(total-->0){
                int n = in.nextInt();
                int m = in.nextInt();
                int[] arr = new int[n];
                for(int i=0;i<n;i++){
                    arr[i]=in.nextInt();
                }
                int slow=0;
                int fast=2;
                long sum=0;
                while(slow<=n-3){
                    if(fast!=n){
                        sum+=((1+fast-slow-2)*(fast-slow-2))/2;
//                        for(int i=slow+2;i<fast;i++){
//                            System.out.println(i-slow-1);
//                            sum+=i-slow-1;
//                        }
                        while(arr[fast]-arr[slow]<=m){
                            sum+=fast-slow-1;
                            fast++;
                            if(fast==n){
                                break;
                            }
                        }
                    } else {
                        // already reached the max
                        sum+=((1+n-slow-2)*(n-slow-2))/2;
//                        for(int i=slow+2;i<=n-1;i++){
//                            System.out.println(i-slow-1);
//                            sum+=i-slow-1;
//                        }
                    }
                    slow++;
                }
                System.out.println(sum);
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
}
