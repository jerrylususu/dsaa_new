package lab3;

import java.io.*;
import java.math.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab2g5fast {
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

            while (total-- > 0) {
                int size = in.nextInt();
                gNode[] arr = new gNode[size];
                gNode[] pos = new gNode[size];
                for (int i = 0; i < size; i++) {
                    arr[i] = new gNode(0, in.nextInt());
                    pos[i] = arr[i];
                }
                Arrays.sort(arr, new Comparator<gNode>() {
                    @Override
                    public int compare(gNode o1, gNode o2) {
                        return o1.val - o2.val;
                    }
                });
                for (int i = 0; i < size; i++) {
                    arr[i].n = i;
                    if (i != 0) {
                        arr[i - 1].next = arr[i];
                        arr[i].prev = arr[i - 1];
                    }
                }
                int midpos = (size + 1) / 2 - 1;
                gNode midcur = arr[midpos];
                int[] rec = new int[(size + 1) / 2];
                rec[0] = midcur.val;

                int move = 0;
                double curmidval = midcur.val;
                int t = 1;
                for (int i = size - 1; i > 0; i = i - 2) {
                    int diff1 = pos[i].val - midcur.val;
                    int diff2 = pos[i - 1].val - midcur.val;

                    if (diff1 > 0) {
                        diff1 = 1;
                    } else if (diff1 < 0) {
                        diff1 = -1;
                    }
                    if (diff2 > 0) {
                        diff2 = 1;
                    } else if (diff2 < 0) {
                        diff2 = -1;
                    }

                    for (int j = i; j >= i - 1; j--) {
                        if (pos[j].prev != null) {
                            if (pos[j].next != null) {
                                pos[j].prev.next = pos[j].next;
                            } else {
                                pos[j].prev.next = null;
                            }
                        }
                        if (pos[j].next != null) {
                            if (pos[j].prev != null) {
                                pos[j].next.prev = pos[j].prev;
                            } else {
                                pos[j].next.prev = null;
                            }
                        }
                    }


                    int sum = diff1 + diff2;
                    if (sum > 0) {
                        if (midcur.prev != null) {
                            midcur = midcur.prev;
                        }
                    } else if (sum < 0) {
                        if (midcur.next != null) {
                            midcur = midcur.next;
                        }
                    }

                    rec[t] = midcur.val;
                    t++;

                }
                //rec[(size+1)/2-1] = pos[0].val;
                StringBuilder sb = new StringBuilder();
                for (int i = (size + 1) / 2 - 1; i >= 0; i--) {
                    sb.append(rec[i] + " ");
                }
                sb.deleteCharAt(sb.length() - 1);
                out.println(sb.toString());
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


class gNode{
    int n;
    int val;
    gNode next;
    gNode prev;

    public gNode(int n, int val) {
        this.n = n;
        this.val = val;
    }

    @Override
    public String toString() {
        return "gNode{" +
                "n=" + n +
                ", val=" + val +
                '}';
    }
}
