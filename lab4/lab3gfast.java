//package lab3;
//
//import java.io.*;
//import java.math.*;
//import java.util.StringTokenizer;
///**
// * Built using CHelper plug-in
// * Actual solution is at the top
// * Author: Wavator
// */
//public class lab3gfast {
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
//        int total = in.nextInt();
//
//        while(total-->0){
//            int len  = in.nextInt();
//            stack max=new stack(len), min=new stack(len), real=new stack(len);
//
//            for(int i=0;i<len;i++){
//                String s = in.next();
//                if(s.charAt(1)=='u'){
//                    //push
//                    int tmp = in.nextInt();
//                    if(real.size==0){
//                        //init
//                        real.push(tmp);
//                        max.push(tmp);
//                        min.push(tmp);
//                    } else {
//                        real.push(tmp);
//                        int curmin = min.peek(), curmax =max.peek();
//                        min.push(tmp<curmin?tmp:curmin);
//                        max.push(tmp>curmax?tmp:curmax);
//                    }
//                } else {
//                    //pop
//                    int res=0;
//                    if(real.size>1){
//                        max.pop();
//                        min.pop();
//                        res = max.peek()-min.peek();
//                        real.pop();
//                    }else{
//                        res=0;
//                    }
//                    System.out.println(res);
//                }
//            }
//
//        }
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
//class stack{
//    int maxsize;
//    int size;
//    int[] arr;
//    int topptr;
//
//    public stack (int s){
//        this.maxsize = s;
//        this.size = 0;
//        this.topptr = -1;
//        arr = new int[maxsize];
//    }
//
//    public void push(int c){
//        topptr++;
//        if(topptr<=maxsize){
//            size++;
//            arr[topptr]=c;
//        } else {
//            System.out.println("Full");
//        }
//    }
//
//    public int pop(){
//        if(size>=0){
//            int tmp = arr[topptr];
//            topptr--;
//            size--;
//            return tmp;
//        } else {
//            System.out.println("Empty");
//            return '0';
//        }
//    }
//
//    public int peek(){
//        if(size>=0){
//            return arr[topptr];
//        }else{
//            return -1;
//        }
//    }
//
//}