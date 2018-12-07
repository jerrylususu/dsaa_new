package lab3;

import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.math.*;
import java.util.StringTokenizer;

public class lab2a {
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
                int s1 = in.nextInt();
                int s2 = in.nextInt();
                int[] a1 = new int[s1];
                int[] a2 = new int[s2];
                for(int i=0;i<s1;i++){
                    a1[i] = in.nextInt();
                }
                for(int i=0;i<s2;i++){
                    a2[i] = in.nextInt();
                }
                int[] a3 = new int[s1+s2];

                int c1=0,c2=0,c3=0;
                while(c1<s1&&c2<s2){
                    if(a1[c1]<=a2[c2]){
                        a3[c3]=a1[c1];
                        c1++;
                    } else {
                        a3[c3]=a2[c2];
                        c2++;
                    }
                    c3++;
                }

                if(c1==s1&&c2<s2){
                    System.arraycopy(a2,c2,a3,c3,s2-c2);
//                    while(c2<s2){
//                        a3[c3]=a2[c2];
//                        c2++;
//                        c3++;
//                    }
                } else if (c2==s2&&c1<s1){
                    System.arraycopy(a1,c1,a3,c3,s1-c1);
//                    while(c1<s1){
//                        a3[c3]=a1[c1];
//                        c1++;
//                        c3++;
//                    }
                }

                int size=s1+s2-1;
                for(int i=0;i<size;i++){
                    out.print(a3[i]+" ");
                }
                out.println(a3[size]);
                out.flush();


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