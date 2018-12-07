package labbonus;

import java.util.Arrays;
import java.util.Scanner;

public class lab6cmerge {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while(totalnum-->0){
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            long[] res = new long[1];
            mergeSort(arr,0,n,res);

//            System.out.println(Arrays.toString(mergeSort(arr,0,n,res)));
            System.out.println(res[0]);

        }
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
                System.out.printf("triggered, left=%s, right=%s, c1=%d, c2=%d, c=%d, res=%s\n",
                        Arrays.toString(left),Arrays.toString(right),c1,c2,c,Arrays.toString(res));
                // reference: http://www.voidcn.com/article/p-oujususd-bqg.html
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

}
