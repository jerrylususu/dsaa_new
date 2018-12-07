package lab7;


import java.io.*;
import java.math.*;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab7e3fast {
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
//            Scanner in = new Scanner(System.in);
            int totalnum = in.nextInt();

            while(totalnum-->0){
                int n = in.nextInt();
                int k = in.nextInt();
                MyItem[] arr = new MyItem[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = new MyItem(in.nextInt(),i);
                }
                MyItem[] res = null;
                if(k>=n){
                    Arrays.sort(arr, new Comparator<MyItem>() {
                        @Override
                        public int compare(MyItem o1, MyItem o2) {
                            return o1.val- o2.val;
                        }
                    });
                    res = arr;
                } else {
                    boolean[] visited = new boolean[n];
                    PriorityQueue<MyItem> pq = new PriorityQueue<>(new Comparator<MyItem>() {
                        @Override
                        public int compare(MyItem o1, MyItem o2) {
                            return o2.val-o1.val;
                        }
                    });
                    PriorityQueue<MyItem> pq2 = new PriorityQueue<>(new Comparator<MyItem>() {
                        @Override
                        public int compare(MyItem o1, MyItem o2) {
                            return o1.val-o2.val;
                        }
                    });
                    for (int i = 0; i < n; i++) {
                        if(pq.size()<k){
                            pq.add(arr[i]);
                            pq2.add(arr[i]);
                        } else {
                            if(arr[i].val>pq2.peek().val){
                                MyItem maxmin = pq2.poll();
                                pq.remove(maxmin);
                                pq.add(arr[i]);
                                pq2.add(arr[i]);
                            }
                        }
                    }
                    res = new MyItem[n];
                    int respos = n-1;
                    for (int i = 0; i < k; i++) {
                        res[respos] = pq.poll();
                        visited[res[respos].id]=true;
                        respos--;
                    }
                    respos = 0;
                    int oldpos=0;
                    for (int i = 0; i < n-k; i++) {
                        while(visited[oldpos]){
                            oldpos++;
                        }
                        res[respos] = arr[oldpos];
                        respos++;
                        oldpos++;
                    }
                }

                for (int i = 0; i < n; i++) {
                    System.out.print(res[i].val);
                    if(i!=n-1){
                        System.out.print(" ");
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

class MyItem{
    int val;
    int id;

    public MyItem(int val, int id){
        this.val = val;
        this.id = id;
    }

    @Override
    public String toString() {
        return "MyItem{" +
                "val=" + val +
                ", id=" + id +
                '}';
    }
}