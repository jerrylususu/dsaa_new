package lab5;

import java.util.HashMap;
import java.io.*;
import java.math.*;
import java.util.StringTokenizer;
/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab5c {
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
                int l1 = in.nextInt();
                String all = in.next();
                int l2 = in.nextInt();
                String key = in.next();
                HashMap<Character,Integer> hm = buildChar2Int(key);
                int[][] arr = buildFSA(key,hm);

                printArr(arr);

                int cnt=0,cur=0,tmp=0;
                while(cur<l1){
                    if(hm.containsKey(all.charAt(cur))){
                        // in list
                        tmp = arr[hm.get(all.charAt(cur))][tmp];
                        cur++;
                        if(tmp==l2){
                            cnt++;
                        }
                    } else {
                        // out of list
                        tmp=0;
                        cur++;
                    }
                }
                out.println(cnt);
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

    public static HashMap<Character,Integer> buildChar2Int(String s){
        int t=0, len=s.length();
        HashMap<Character,Integer> hm = new HashMap<>();
        for(int i=0;i<len;i++){
            if(!hm.containsKey(s.charAt(i))){
                hm.put(s.charAt(i),t);
                t++;
            }
        }
        return hm;
    }


    public static int[][] buildFSA(String s, HashMap<Character,Integer> hm){
        int len = s.length();
        int width = hm.size();
        int[][] arr = new int[width][len+1];

        for(int i=0;i<len;i++){
            //printArr(arr);
            //System.out.printru'n'f'r'pln();
            int movenext = hm.get(s.charAt(i));
            if(i==0){
                arr[movenext][0] = 1;
            } else {
                arr[movenext][i] = i+1;
                for(int j=0;j<width;j++){
                    if(j==movenext) continue;
                    int tmp=0;
                    for(int k=1;k<i;k++){
                        tmp=arr[hm.get(s.charAt(k))][tmp];
                    }
                    tmp = arr[j][tmp];
                    arr[j][i] = tmp;
                }
            }
        }

        for(int j=0;j<width;j++){
            int tmp=0;
            for(int k=1;k<len;k++){
                tmp=arr[hm.get(s.charAt(k))][tmp];
            }
            tmp = arr[j][tmp];
            arr[j][len] = tmp;
        }

        return arr;
    }

    public static void printArr(int[][] arr){
        for(int[] i:arr){
            for(int j:i){
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }
}
