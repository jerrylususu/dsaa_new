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
public class lab61fast {

    public static boolean debug = false;
    public static boolean exist = false;

    public static int countLess(int[] arr, int key){
        int i=0,j=1,cnt=0;
        while(i<arr.length&&j<arr.length){
            while(j<arr.length&&arr[j]-arr[i]<key){
                j++;
            }
            while(j<arr.length&&arr[j]-arr[i]==key){
                if(arr[j]-arr[i]==key){
                    exist=true;
                }
                j++;
            }
            if(j<arr.length&&arr[j]-arr[i]>key){
                j--;
            }
            if(j==arr.length){
                j--;
            }
            cnt+=(j-i);
            if(debug) System.out.printf("i=%d, j=%d, cnt=%d\n",i,j,cnt);
            i++;
        }
        return cnt;
    }


    public static int[] mergeSort(int[] arr, int l, int r, long[] cnt){
//        System.out.println("l="+l+" r="+r);
        if(r-l==1||r==l){
            return new int[] {arr[l]};
        }
        int mid = (l+r)/2;
        int[] left=mergeSort(arr,l,mid, cnt);
        int[] right=mergeSort(arr,mid,r,cnt);
        return merge(left,right,cnt);
    }

    public static int[] merge(int[] left, int[] right, long cnt[]){
        int c1=0,c2=0,c=0;
        int[] res=new int[left.length+right.length];
        while(c1<left.length&&c2<right.length){
            if(left[c1]<right[c2]){
                res[c]=left[c1];c1++;
            } else if (left[c1]>right[c2]){
                res[c]=right[c2];c2++;
                cnt[0]+=left.length-c1;
//                System.out.printf("triggered, left=%s, right=%s, c1=%d, c2=%d, c=%d, res=%s\n",
//                        Arrays.toString(left),Arrays.toString(right),c1,c2,c,Arrays.toString(res));
//                // reference: http://www.voidcn.com/article/p-oujususd-bqg.html
            } else {
                res[c]=left[c1];c1++;
            }
            c++;
        }

        if(c1<left.length&&c2==right.length){
            while(c1<left.length){
                res[c]=left[c1];
                c++;c1++;
            }
        } else if (c1==left.length&&c2<right.length){
            while(c2<right.length){
                res[c]=right[c2];
                c++;c2++;
            }
        } else {
            // this is not supposed to happen
            System.out.println("Something wrong... MARK1");
        }

        // fix the rest
        return res;
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
                int[] arr = new int[n];
                for (int i = 0; i < n; i++) {
                    arr[i]=in.nextInt();
                }
                long[] cnt = new long[2];
                arr = mergeSort(arr,0,n,cnt);
                if(debug) System.out.println(Arrays.toString(arr));

                int l=0, r=arr[n-1]-arr[0], mid=-1;
                int lessnum=0;
                int size=n*(n-1)/2;
                int half=(n%2==0)?size/2:size/2+1;
                if(debug) System.out.println(half);
                while(l<=r){
                    mid = (l+r)/2;
                    lessnum = countLess(arr,mid);
                    if(debug) System.out.println("mid="+mid+" lessnum="+lessnum);
                    if(debug) System.out.println("left="+l+" right="+r);
                    if(debug) System.out.println(lessnum<half);

                    if(lessnum<half){
                        if(debug) System.out.println("too small, need to be big");
                        l=mid+1;
                    } else if (lessnum>half){
                        if(debug) System.out.println("too big, need to be small");
                        r=mid-1;
                    } else {
                        break;
                    }
                }

                while(!exist||countLess(arr,mid)<half){
                    mid++;
                }

                System.out.println(mid);

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
