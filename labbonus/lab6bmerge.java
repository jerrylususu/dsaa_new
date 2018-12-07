package labbonus;

import java.util.Arrays;
import java.util.Scanner;

import java.io.*;
import java.math.*;
import java.util.StringTokenizer;
/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab6bmerge {
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
                int[] arr = new int[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = in.nextInt();
                }
                arr = mergeSort(arr,0,n);
                System.out.println(arr[k-1]);
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

    public static int[] mergeSort(int[] arr,int start,int end) {
        if(end-start==1) {
            return new int[] {arr[start]};
        }
        if(end-start==2) {
            if(arr[start]<=arr[start+1]) {
                return new int[] {arr[start],arr[start+1]};
            } else {
                return new int[] {arr[start+1],arr[start]};
            }
        }
        int middle = start+(end-start)/2;
//		if(middle==start) {
//			return new int[] {arr[start]};
//		}
        int[] left = mergeSort(arr, start, middle);
        int[] right = mergeSort(arr, middle, end);
        return merge(left,right);
    }

    public static int[] merge(int[] left,int[] right) {
        int pl = 0, pr = 0;
        int[] res = new int[left.length+right.length];
        int count = 0;
        while(pl<left.length&&pr<right.length) {
            if(left[pl]<right[pr]) {
                res[count] = left[pl];
                pl++;
            } else {
                res[count] = right[pr];
                pr++;
            }
            count++;
        }
        if(pl!=left.length) {
            while(pl<left.length) {
                res[count] = left[pl];
                pl++;
                count++;
            }
        } else if (pr!=right.length) {
            while(pr<right.length) {
                res[count] = right[pr];
                pr++;
                count++;
            }
        } else {
            ;
        }
        return res;
    }
}

