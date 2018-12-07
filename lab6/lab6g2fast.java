package lab6;
import java.io.*;
import java.math.*;
import java.util.LinkedList;
import java.util.StringTokenizer;
/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab6g2fast {
    public static void findMax(int i, int[] weights, LinkedList<Integer>[] out, int[] maxSize){
        if(maxSize[i]!=0){
            return;
        }

        int cnt=1;
        if(out[i]!=null){
            for(Integer c:out[i]){
                findMax(c,weights,out,maxSize);
                cnt+=maxSize[c];
            }
        }

        maxSize[i]=cnt;
    }


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
                int[] weights = new int[n];
                for (int i = 0; i < n; i++) {
                    weights[i] = in.nextInt();
                }
                int[] maxSize = new int[n];
                LinkedList<Integer>[] outll = new LinkedList[n];
                for (int i = 0; i < n - 1; i++) {
                    int p1 = in.nextInt()-1, p2 = in.nextInt()-1,s=-1,b=-1;
                    // p for position, s=small, b=big
                    if(weights[p1]<weights[p2]){
                        s=p1;b=p2;
                    } else {
                        s=p2;b=p1;
                    }
                    if(outll[s]==null){
                        outll[s] = new LinkedList<Integer>();
                    }
                    outll[s].add(b);
                }
                for (int i = 0; i < n; i++) {
                    findMax(i,weights,outll,maxSize);
                }

//            System.out.println(Arrays.toString(maxSize));

                int max = Integer.MIN_VALUE;
                for (int i = 0; i < n; i++) {
                    if(maxSize[i]>max){
                        max=maxSize[i];
                    }
                }

                System.out.println(max);

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
