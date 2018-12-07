package lab4;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.io.*;
import java.math.*;
import java.util.StringTokenizer;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab3g2fast {
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
                int len = in.nextInt();
                PriorityQueue<Integer> maxHeap = new PriorityQueue<>(len,Collections.reverseOrder());
                PriorityQueue<Integer> minHeap = new PriorityQueue<>(len);
                LinkedList<Integer> stack = new LinkedList<>();

                for(int i=0;i<len;i++){
                    String s = in.next();
                    if(s.charAt(1)=='u'){
                        //push
                        int tmp = in.nextInt();
                        maxHeap.add(tmp);
                        minHeap.add(tmp);
                        stack.addLast(tmp);
                    }else{
                        //pop
                        if(stack.size()>1){
                            int tmp = stack.removeLast();
                            maxHeap.remove(tmp);
                            minHeap.remove(tmp);
                            System.out.println(maxHeap.peek()-minHeap.peek());
                        } else {
                            stack.removeLast();
                            maxHeap.clear();
                            minHeap.clear();
                            System.out.println(0);
                        }


                    }
                }
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

