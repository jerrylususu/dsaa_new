package lab2;

import java.util.*;

public class lab1e{

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int l = in.nextInt();
            int n = in.nextInt();
            int m = in.nextInt();
            int[] pos = new int[n+2];
            for(int i=1;i<=n;i++){
                pos[i] = in.nextInt();
            }
            pos[n+1]=l;
            Arrays.sort(pos);
            //System.out.println(Arrays.toString(pos));

            int left=0, right=l, mid=0;
            while(left<=right){
                mid = left+(right-left)/2;
                //System.out.println(mid);
                boolean ck = check(mid,l,pos,m);
                //System.out.println(ck);
                if(ck){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            }

            //System.out.println(mid);

            while(!check(mid,l,pos,m)){
                mid++;
            }

            System.out.println(mid);

        }

    }

    public static boolean check(int maxd,int l, int[] pos, int m){
        int pos_cnt = pos.length;
        for(int i=1;i<pos_cnt;i++){
            if(pos[i]-pos[i-1]>maxd){
                return false;
            }
        }
        int people=0,last_person=0,curpos=0;
        for(int i=1;i<pos_cnt;i++){
            if(pos[i]-last_person<=maxd){
                curpos=pos[i];
            }else{
                people++;
                last_person=curpos;
                curpos=pos[i];
            }
        }
        if(curpos-last_person<=maxd){
            people++;
        }
        return (people<=m);

    }

}
