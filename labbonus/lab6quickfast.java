package labbonus;




import java.io.*;
import java.math.*;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class lab6quickfast {
    static boolean d = false;

    public static void quicksort(int[] arr, int l, int r){
        if(d) System.out.println("l="+l+" r="+r);
        if(r-l<=1){
            return;
        }
        if(d) System.out.println(Arrays.toString(arr));
        int wall=l, cur=wall+1, pivot=0;
        int rand=(int)(Math.random()*(r-l))+l;
        swap(arr,rand,r-1);
        pivot=arr[r-1];
        if(d) System.out.println(Arrays.toString(arr));
        while(wall<=r&&arr[wall]<pivot){
            wall++;
        }
        cur=wall+1;
//        wall--;

        while(true){

            if(cur<r-1&&wall<r-1){
                if(d) System.out.printf("pivot=%d, wall=%d, cur=%d\n",pivot,wall,cur);
                if(arr[cur]<=pivot){
                    swap(arr,wall,cur);
                    wall++;
                }
                cur++;
                if(d) System.out.println(Arrays.toString(arr));
            }
            if(cur==r-1){
                if(d) System.out.printf("pivot=%d, wall=%d, cur=%d\n",pivot,wall,cur);
                swap(arr,wall,cur);
                break;
//                wall++;
//                cur=wall+1;
//                if(d) System.out.println(Arrays.toString(arr));
            }
            if(wall==r-1){
                break;
            }
        }
        if(d) System.out.println(Arrays.toString(arr));
        int currentdiv = wall;
        quicksort(arr,l,currentdiv);
        quicksort(arr,currentdiv+1,r);
    }

    public static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

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
                quicksort(arr,0,n);
                System.out.println(arr[k-1]);
//            System.out.println(rearray(arr,0,n));
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
