package lab1;

import java.util.*;

public class lab0f2 {

    final static char[] cons = {'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','x','z'};
    final static char[] narr = {'a','e','i','o','u','w','y'};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();

        while(times-->0){
            String sin = in.next();

//            pre-processing
            int[][] arr = new int[19][19];
            boolean[] shown = new boolean[19];

            int len = sin.length();
            for(int i=0;i<len-1;i++){
                int pos1= getPos(sin.charAt(i));
                int pos2= getPos(sin.charAt(i+1));

                if(pos1!=-1){
                    shown[pos1]=true;
                }
                if(pos2!=-1){
                    shown[pos2]=true;
                }

                if(pos1!=-1&&pos2!=-1){
                    arr[pos1][pos2]++;
                }
            }

//            real counting
            //            find unique letters
            List<Integer> li = new ArrayList<>(19);
            for(int i=0;i<19;i++){
                if(shown[i]){
                    li.add(i);
                }
            }

            //            compare and go
            int maxPair = 0;
            int size = li.size();
            int all = (2<<size-1)-1; // non empty real subset
            for(int i=0;i<all;i++){
                boolean[] here = new boolean[19];
//                List<Integer> judgelist = new ArrayList<>();
                int now = i;
//                generate lists
                for(int j=0;j<size;j++){
                    if((now&1)==1){
                        here[li.get(j)]=true;
//                        judgelist.add(li.get(j));
                    }
                    now=now>>1;
                }
                int cnt=0;

                for(int m=0;m<19;m++){
                    for(int n=0;n<19;n++){
                        boolean x = here[m];
                        boolean y = here[n];
                        if((x&&!y)||(!x&&y)){
                            cnt+=arr[m][n];
                        }
                    }
                }

                if(cnt>maxPair){
                    maxPair=cnt;
                }
            }

            System.out.println(maxPair);
        }
    }

    public static int getPos(char c){
        for(int i=0;i<cons.length;i++){
            if(cons[i]==c){
                return i;
            }
        }
        return -1;
    }

}
