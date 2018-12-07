package lab5;




import java.io.*;
import java.math.*;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab5gFSAfast {
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
            int n = in.nextInt();
            char[][] sarr = new char[n][];
            int allMinLen = Integer.MAX_VALUE;
            for(int i=0;i<n;i++){
                sarr[i] = in.next().toCharArray();
                if(allMinLen>sarr[i].length){
                    allMinLen = sarr[i].length;
                }
            }
            String op = in.next();
            char[] ops = op.toCharArray();

            HashMap<Character,Integer>[] hmarr = new HashMap[n];
            int[][][] jparr = new int[n][][];
            for (int i = 0; i < n; i++) {
                String s = String.valueOf(sarr[i]);
                hmarr[i] = buildChar2Int(s);
                jparr[i] = buildFSANew(s,hmarr[i]);
            }
//        int[] curstates = new int[n];
            Stack<Integer>[] curstates = new Stack[n];
            for(int i=0;i<n;i++){
                curstates[i] = new Stack<Integer>();
                curstates[i].push(0);
            }

            Stack<Integer> stack = new Stack<>();
            stack.push(allMinLen);
            System.out.println(stack.peek());
            int cur = -1, size = 0;
            char[] t = new char[ops.length];
            for(int i=0;i<ops.length;i++){
//            System.out.println("before"+Arrays.toString(t));
//            System.out.println("arr before"+Arrays.toString(curstates));
                if(ops[i]=='-'){
                    if(size>0){
                        cur--;
                        size--;
                        stack.pop();
                        for (int j = 0; j < n; j++) {
                            curstates[j].pop();
                        }
                    }
                } else {
                    cur++;
                    size++;
                    t[cur]=ops[i];
//                System.out.println("after"+Arrays.toString(t));
                    int localmin = Integer.MAX_VALUE;
                    for(int j=0;j<n;j++){

                        int state = curstates[j].peek();
                        char c = t[cur];
                        int jumpnum=-1;
                        int[][] arr = jparr[j];

                        HashMap<Character,Integer> hm = hmarr[j];
                        if(hm.containsKey(c)){
                            jumpnum=hm.get(c);
                        }
                        if(jumpnum!=-1){
                            state=arr[jumpnum][state];
                        } else {
                            state=0;
                        }
                        curstates[j].push(state);

                        int remain = sarr[j].length - curstates[j].peek();

                        if(remain<localmin){
                            localmin=remain;
                        }
                    }
                    stack.push(localmin);
                }
                if(stack.isEmpty()){
                    System.out.println(allMinLen);
                } else {
                    System.out.println(stack.peek());
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
    public static int[][] buildFSANew(String s, HashMap<Character,Integer> hm){
        int len = s.length();
        int width = hm.size();
        int[][] arr = new int[width][len+1];

        for(int i=0;i<=len;i++){
            //printArr(arr);
            //System.out.printru'n'f'r'pln();
            int movenext = 0;
            if(i<len){
                movenext = hm.get(s.charAt(i));
            } else {
                movenext = -1;
            }

            if(i==0){
                arr[movenext][0] = 1;
            } else {
                if(movenext!=-1){
                    arr[movenext][i] = i+1;
                }

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

        return arr;
    }
}
