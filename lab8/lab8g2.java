//package lab8;
//
//import java.io.DataInputStream;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Arrays;
//
//// changed to array-based instead of object-based
//
//// wtf? even int[] based graph building can lead to TLE?
//
//
//public class lab8g2 {
//    public static void main(String[] args) throws IOException{
////        Scanner in = new Scanner(System.in);
//        Reader in = new Reader();
//        int totalnum = in.nextInt();
//
//        while(totalnum-->0){
//            int n = in.nextInt(), m = in.nextInt();
//            int[] par = new int[n]; // for storing parents
//            Arrays.fill(par,-1);
//            for (int i = 0; i < n-1; i++) {
//                int s = in.nextInt()-1, f = in.nextInt()-1;
//                // s for son and f for father
//                par[s]=f;
//            }
//            for (int i = 0; i < m; i++) {
//                int ps = in.nextInt()-1, pf = in.nextInt()-1;
//                // p for possible
//                int cur = ps;
//                boolean found = false;
//                while(cur!=-1){
//                    if(cur==pf){
//                        found = true; break;
//                    } else {
//                        cur = par[cur];
//                    }
//                }
//                System.out.println(found?"Yes":"No");
//            }
//        }
//
//    }
//
//    static class Reader
//    {
//        final private int BUFFER_SIZE = 1 << 16;
//        private DataInputStream din;
//        private byte[] buffer;
//        private int bufferPointer, bytesRead;
//
//        public Reader()
//        {
//            din = new DataInputStream(System.in);
//            buffer = new byte[BUFFER_SIZE];
//            bufferPointer = bytesRead = 0;
//        }
//
//        public Reader(String file_name) throws IOException
//        {
//            din = new DataInputStream(new FileInputStream(file_name));
//            buffer = new byte[BUFFER_SIZE];
//            bufferPointer = bytesRead = 0;
//        }
//
//        public String readLine() throws IOException
//        {
//            byte[] buf = new byte[64]; // line length
//            int cnt = 0, c;
//            while ((c = read()) != -1)
//            {
//                if (c == '\n')
//                    break;
//                buf[cnt++] = (byte) c;
//            }
//            return new String(buf, 0, cnt);
//        }
//
//        public int nextInt() throws IOException
//        {
//            int ret = 0;
//            byte c = read();
//            while (c <= ' ')
//                c = read();
//            boolean neg = (c == '-');
//            if (neg)
//                c = read();
//            do
//            {
//                ret = ret * 10 + c - '0';
//            }  while ((c = read()) >= '0' && c <= '9');
//
//            if (neg)
//                return -ret;
//            return ret;
//        }
//
//        public long nextLong() throws IOException
//        {
//            long ret = 0;
//            byte c = read();
//            while (c <= ' ')
//                c = read();
//            boolean neg = (c == '-');
//            if (neg)
//                c = read();
//            do {
//                ret = ret * 10 + c - '0';
//            }
//            while ((c = read()) >= '0' && c <= '9');
//            if (neg)
//                return -ret;
//            return ret;
//        }
//
//        public double nextDouble() throws IOException
//        {
//            double ret = 0, div = 1;
//            byte c = read();
//            while (c <= ' ')
//                c = read();
//            boolean neg = (c == '-');
//            if (neg)
//                c = read();
//
//            do {
//                ret = ret * 10 + c - '0';
//            }
//            while ((c = read()) >= '0' && c <= '9');
//
//            if (c == '.')
//            {
//                while ((c = read()) >= '0' && c <= '9')
//                {
//                    ret += (c - '0') / (div *= 10);
//                }
//            }
//
//            if (neg)
//                return -ret;
//            return ret;
//        }
//
//        private void fillBuffer() throws IOException
//        {
//            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
//            if (bytesRead == -1)
//                buffer[0] = -1;
//        }
//
//        private byte read() throws IOException
//        {
//            if (bufferPointer == bytesRead)
//                fillBuffer();
//            return buffer[bufferPointer++];
//        }
//
//        public void close() throws IOException
//        {
//            if (din == null)
//                return;
//            din.close();
//        }
//    }
//
//}
//
