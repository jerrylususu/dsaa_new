//package lab2;
//
//import java.io.*;
//import java.math.*;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.StringTokenizer;
///**
// * Built using CHelper plug-in
// * Actual solution is at the top
// * Author: Wavator
// */
//public class lab2f3fast {
//    public static void main(String[] args){
//        InputStream inputStream = System.in;//new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
//        OutputStream outputStream = System.out;
//        InputReader in = new InputReader(inputStream);
//        PrintWriter out = new PrintWriter(outputStream);
//        Task solver = new Task();
//        solver.solve(in, out);
//        out.close();
//    }
//    static class Task {
//        public void solve(InputReader in, PrintWriter out) {
//            int total = in.nextInt();
//
//            while(total-->0) {
//                //input
//                int n = in.nextInt();
//                int k = in.nextInt();
//                fnode[] arr = new fnode[n];
//                for (int i = 0; i < n; i++) {
//                    arr[i] = new fnode(in.nextInt(), i);
//                }
//                //create link
//                fnode head = arr[0];
//                for (int i = 0; i < n; i++) {
//                    if (i == 0) {
//                        arr[0].next = arr[1];
//                        arr[0].prev = new fnode(-1, -1);
//                    } else if (i == n - 1) {
//                        arr[n - 1].prev = arr[n - 2];
//                        arr[n - 1].next = new fnode(-1, n);
//                    } else {
//                        arr[i].next = arr[i + 1];
//                        arr[i].prev = arr[i - 1];
//                    }
//                }
//                //sort
//                Arrays.sort(arr, new Comparator<fnode>() {
//                    @Override
//                    public int compare(fnode o1, fnode o2) {
//                        return o1.val - o2.val;
//                    }
//                });
//
//                //begin process
//                int sum = 0;
//                //start from small end
//                for (int i = 0; i < n - k + 1; i++) {   // <- possible problem n-k+1
//
//                    // wing expand
//                    int lengthcnt = 1;
//                    fnode left = arr[i];
//                    fnode right = arr[i];
//                    while (right.next != null && right.next.val != -1 && lengthcnt < k) { // <- possible problem <k-1
//                        right = right.next;
//                        lengthcnt++;
//                    }
//                    if (lengthcnt != k) {
//                        while (left.prev != null && left.prev.val != -1 && lengthcnt < k) {
//                            left = left.prev;
//                            lengthcnt++;
//                        }
//                    }
//                    boolean successexpand = true;
//                    if (lengthcnt != k) {
//                        //that's possibly too small for k?
//                        successexpand = false;
//                    }
//
//
//                    // wing expand finish
//                    // start move
//                    fnode curnode = arr[i];
//                    if (successexpand) {
//                        int rangecnt = 1;
//                        int currentval = arr[i].val;
//                        fnode d1left = left, d1right = right;
//                        boolean d1 = false;
//                        while (d1left.next != null && d1left.next.val != -1 && d1right.next != null && d1right.next.val != -1 && d1left.val != currentval) {
//                            d1left = d1left.next;
//                            d1right = d1right.next;
//                            int tmpcnt = 1;
//                            if (d1left.prev != null) {
//                                if (d1left.orgn - d1left.prev.orgn > 1) {
//                                    tmpcnt = d1left.orgn - d1left.prev.orgn;
//                                }
//                            }
//                            if (d1right.next != null) {
//                                if (d1right.next.orgn - d1right.orgn > 1) {
//                                    tmpcnt *= (d1right.next.orgn - d1right.orgn);
//                                }
//                            }
//                            d1 = true;
//                            rangecnt += tmpcnt;
//                        }
//                        boolean d1played = false;
//                        if (!d1) {
//                            boolean moded = false;
//                            int tmpcnt = 1;
//                            if (d1left.prev != null) {
//                                if (d1left.orgn - d1left.prev.orgn > 1) {
//                                    tmpcnt = d1left.orgn - d1left.prev.orgn;
//                                    moded = true;
//                                }
//                            }
//                            if (d1right.next != null) {
//                                if (d1right.next.orgn - d1right.orgn > 1) {
//                                    tmpcnt *= (d1right.next.orgn - d1right.orgn);
//                                    moded = true;
//                                }
//                            }
//                            if (moded) {
//                                rangecnt--;
//                                rangecnt += tmpcnt;
//                                d1played = true;
//                            }
//                        }
//                        fnode d2left = left, d2right = right;
//                        boolean d2 = false;
//                        while (d2left.prev != null && d2left.prev.val != -1 && d2right.prev != null && d2right.prev.val != -1 && d2right.val != currentval) {
//                            d2left = d2left.prev;
//                            d2right = d2right.prev;
//                            int tmpcnt = 1;
//                            if (d2left.prev != null) {
//                                if (d2left.orgn - d2left.prev.orgn > 1) {
//                                    tmpcnt = d2left.orgn - d2left.prev.orgn;
//                                }
//                            }
//                            if (d2right.next != null) {
//                                if (d2right.next.orgn - d2right.orgn > 1) {
//                                    tmpcnt *= (d2right.next.orgn - d2right.orgn);
//                                }
//                            }
//                            d2 = true;
//                            rangecnt += tmpcnt;
//                        }
//                        if (!d2 && !d1played) {
//                            boolean moded = false;
//                            int tmpcnt = 1;
//                            if (d2left.prev != null) {
//                                if (d2left.orgn - d2left.prev.orgn > 1) {
//                                    tmpcnt = d2left.orgn - d2left.prev.orgn;
//                                    moded = true;
//                                }
//                            }
//                            if (d2right.next != null) {
//                                if (d2right.next.orgn - d2right.orgn > 1) {
//                                    tmpcnt *= (d2right.next.orgn - d2right.orgn);
//                                    moded = true;
//                                }
//                            }
//                            if (moded) {
//                                rangecnt--;
//                                rangecnt += tmpcnt;
//                            }
//                        }
//                        //System.out.println(rangecnt + " " + currentval);
//                        sum += rangecnt * currentval;
//                    }
//                    // end move
//                    // remove current node
//                    if (curnode.prev != null) {
//                        if (curnode.next != null) {
//                            curnode.prev.next = curnode.next;
//                        } else {
//                            curnode.prev.next = null;
//                        }
//                    }
//                    if (curnode.next != null) {
//                        if (curnode.prev != null) {
//                            curnode.next.prev = curnode.prev;
//                        } else {
//                            curnode.next.prev = null;
//                        }
//                    }
//                }
//
//                out.println(sum);
//                out.flush();
//            }
//        }
//
//    }
//    static class InputReader {
//        public BufferedReader reader;
//        public StringTokenizer tokenizer;
//        public InputReader(InputStream stream) {
//            reader = new BufferedReader(new InputStreamReader(stream), 32768);
//            tokenizer = null;
//        }
//        public String next() {
//            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
//                try {
//                    tokenizer = new StringTokenizer(reader.readLine());
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            return tokenizer.nextToken();
//        }
//        public int nextInt() {
//            return Integer.parseInt(next());
//        }
//        public long nextLong() {
//            return Long.parseLong(next());
//        }
//
//        public double nextDouble() {
//            return Double.parseDouble(next());
//        }
//
//        public char[] nextCharArray() {
//            return next().toCharArray();
//        }
//
//        //         public boolean hasNext() {
////             try {
////                 return reader.ready();
////             } catch(IOException e) {
////                 throw new RuntimeException(e);
////             }
////         }
//        public boolean hasNext() {
//            try {
//                String string = reader.readLine();
//                if (string == null) {
//                    return false;
//                }
//                tokenizer = new StringTokenizer(string);
//                return tokenizer.hasMoreTokens();
//            } catch(IOException e) {
//                return false;
//            }
//        }
//        public BigInteger nextBigInteger() {
//            return new BigInteger(next());
//        }
//
//        public BigDecimal nextBigDecinal() {
//            return new BigDecimal(next());
//        }
//    }
//}
//
//class fnode{
//    int val;
//    int orgn;
//    int largen;
//    fnode prev;
//    fnode next;
//
//    public fnode(int n, int o){
//        this.val=n;
//        this.orgn = o;
//    }
//
//    @Override
//    public String toString() {
//        return "fnode{" +
//                "val=" + val +
//                ", orgn=" + orgn +
//                ", largen=" + largen +
//                '}';
//    }
//}