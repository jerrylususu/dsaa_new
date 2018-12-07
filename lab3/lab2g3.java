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
//public class lab2g3 {
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
//                int movecnt=0;
//                double midvalue=0;
//                for(int i=0;i<n;i++){
//                    if(i==0){
//                        head = new gNode(in.nextInt());
//                        sb.append(head.n+" ");
//                        mid=head;
//                        midvalue=head.n;
//                        cur=head;
//                        //cur.prev=cur;
//                        //cur.next=cur;
//                    } else {
//                        int tmp = in.nextInt();
//                        if(tmp>=midvalue){
//                            // start to insert at mid
//                            cur=mid;
//                            while(cur.n<tmp&&cur.next!=null){
//                                cur=cur.next;
//                            }
//                            if(cur.next==null){
//                                // at the end point
//                                cur.next=new gNode(tmp);
//                                cur.next.prev=cur;
//                            } else {
//                                // not at the end point
//                                cur.prev.next=new gNode(tmp);
//                                cur.prev.next.prev = cur.prev;
//                                cur.prev.next.next=cur;
//                                cur.prev=cur.prev.next;
//                            }
//
//                            movecnt++;
//                        } else {
//                            // start to insert at head
//                            cur=head;
//                            while(cur.n<tmp&&cur.next!=null){
//                                cur=cur.next;
//                            }
//                            if(cur.next==null){
//                                cur.prev=new gNode(tmp);
//                                cur.prev.next=cur;
//                            } else {
//                                cur.prev.next=new gNode(tmp);
//                                cur.prev.next.prev = cur.prev;
//                                cur.prev.next.next=cur;
//                                cur.prev=cur.prev.next;
//                            }
//                            movecnt--;
//                        }
//                        //set pos of mid
//                        if(movecnt==0){
//                            //no need to move
//                        } else if(movecnt==2){
//                            //move right 1
//                            mid=mid.next;
//                            midvalue=mid.n;
//                        } else if(movecnt==-2){
//                            //move left 1
//                            mid=mid.prev;
//                            midvalue=mid.n;
//                        } else if(movecnt==1){
//                            midvalue=(mid.n+mid.next.n)/2.0;
//                        } else if(movecnt==-1){
//                            midvalue=(mid.n+mid.prev.n)/2.0;
//                        }
//                        //out put to sb
//                        if(i%2==0){
//                            sb.append(mid.n+" ");
//                            movecnt=0;
//                        }
//                    }
//                }
//                sb.deleteCharAt(sb.length()-1);
//                System.out.println(sb.toString());
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