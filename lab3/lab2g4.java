//package lab2;
//
//import java.io.*;
//import java.math.*;
//import java.util.StringTokenizer;
///**
// * Built using CHelper plug-in
// * Actual solution is at the top
// * Author: Wavator
// */
//public class lab2g4 {
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
//            while (total-->0){
//                int n = in.nextInt();
//                StringBuilder sb = new StringBuilder();
//                gNode head=null, mid=null, cur=null;
//                head=new gNode(in.nextInt());
//                int midpos=0;
//                int movecnt=0;
//                mid=head;
//                cur=head;
//                boolean prejudge=false;
//                sb.append(mid.n+" ");
//                for(int i=1;i<n;i++){
//                    cur=head;
//                    int tmp = in.nextInt();
//                    int move = 0;
//                    while (cur.n<tmp&&cur.next!=null){
//                        cur=cur.next;
//                        move++;
//                    }
//                    if(cur.next==null){
//                        if(tmp>=cur.n){
//                            cur.next=new gNode(tmp);
//                            cur.next.prev=cur;
//                            movecnt++;
//                            prejudge=true;
//                        } else {
//                            if(cur.prev!=null){
//                                cur.prev.next = new gNode(tmp);
//                                cur.prev.next.next = cur;
//                                cur.prev = cur.prev.next;
//                            } else {
//                                movecnt--;
//                                //midpos++;
//                                prejudge=true;
//                                cur.prev = new gNode(tmp);
//                                cur.prev.next = cur;
//                                head=cur.prev;
//                            }
//
//                        }
//                    } else {
//                        if(cur.prev!=null){
//                            cur.prev.next = new gNode(tmp);
//                            cur.prev.next.next = cur;
//                            cur.prev = cur.prev.next;
//                        } else {
//                            movecnt--;
//                            //midpos++;
//                            prejudge=true;
//                            cur.prev = new gNode(tmp);
//                            cur.prev.next = cur;
//                            head = cur.prev;
//                        }
//
//                    }
//
//
//                    if(prejudge){
//                        prejudge=false;
//                    } else {
//                        if(move>midpos){
//                            movecnt++;
//                        } else {
//                            movecnt--;
//                        }
//                    }
//                    if(i%2==0){
//                        if(movecnt==0){
//
//                        } else if (movecnt==2){
//                            mid=mid.next;
//                            movecnt=0;
//                        } else if (movecnt==-2){
//                            mid=mid.prev;
//                            movecnt=0;
//                        }
//                        sb.append(mid.n+" ");
//                    }
//                }
//                sb.deleteCharAt(sb.length()-1);
//                out.println(sb.toString());
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
//class gNode{
//    int n;
//    gNode prev;
//    gNode next;
//
//    public gNode(int n){
//        this.n=n;
//    }
//
//    @Override
//    public String toString() {
//        return "gNode{" +
//                "n=" + n +
//                '}';
//    }
//}