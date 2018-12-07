package lab7;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import java.io.*;
        import java.math.*;
        import java.util.StringTokenizer;
/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab7e4fast {
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
            int totalnum = in.nextInt();

            while(totalnum-->0){
                int n = in.nextInt();
                int k = in.nextInt();
                int[] res = new int[n];
                if(k>=n){
                    for (int i = 0; i < n; i++) {
                        res[i] = in.nextInt();

                    }
                    Arrays.sort(res);
                } else {
                    PriorityQueue<Integer> pq = new PriorityQueue<>(k + 1);
                    int rescur = 0;
                    for (int i = 0; i < n; i++) {
                        int tmp = in.nextInt();
                        if (pq.size() < k) {
                            pq.add(tmp);
                        } else {
                            pq.add(tmp);
                            res[rescur] = pq.poll();
                            rescur++;
//                            pq.add(tmp);
//                            res[rescur] = pq.poll();
//                            rescur++;
                        }
                    }
                    while (pq.size() > 0) {
                        res[rescur] = pq.poll();
                        rescur++;
                    }
                }
                for (int i = 0; i < n; i++) {
                    out.print(res[i]);
                    if(i!=n-1){
                        out.print(" ");
                    }
                }
                out.println();

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

