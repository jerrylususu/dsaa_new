package labbonus;

import java.util.Arrays;
import java.util.Scanner;

public class lab6emergebinary {

    public static boolean debug = false;
    public static boolean exist = false;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();


        while(totalnum-->0){
            exist=false;
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i]=in.nextInt();
            }
            long[] cnt = new long[2];
            arr = mergeSort(arr,0,n,cnt);
            if(debug) System.out.println(Arrays.toString(arr));

            int l=0, r=arr[n-1]-arr[0], mid=-1;
            long lessnum=0;
            long size=(1l*n*(n-1))/2;
            long half=(size%2==0)?size/2:(size+1)/2;

            if(size==0){
                System.out.println(0);
            } else {
                if(debug) System.out.println(half);
                boolean earlyexit=false;
                while(l<=r){
                    mid = (l+r)/2;
                    lessnum = count2(arr,mid);
//                    if(debug) System.out.println("mid="+mid+" lessnum="+lessnum);
//                    if(debug) System.out.println("left="+l+" right="+r);
//                    if(debug) System.out.println(lessnum<half);

                    if(lessnum>=half){
                        r=mid-1;
                    } else {
                        l=mid+1;
                    }
                }

//            System.out.println(mid);

                if((!exist)||(exist&&lessnum<half)){
                    mid++;
                    lessnum=count2(arr,mid);
                }

                System.out.println(mid);
            }



        }
    }

    public static long count2(int[] arr, int key){
        int i=0,j=1,len=arr.length;
        long sum=0;
        exist=false;
        while(i<len&&j<len){
            while(j<len&&arr[j]-arr[i]<key){
                j++;
            }
            while(j<len&&arr[j]-arr[i]==key){
                j++;
                exist=true;
            }
            if(j==len){
                j--;
            } else if (j<len&&arr[j]-arr[i]>key){
                j--;
            }
            sum+=(j-i);
            i++;
        }
        return sum;
    }


    public static int countLess(int[] arr, int key){
        int i=0,j=1,cnt=0;
        exist=false;
        while(i<arr.length&&j<arr.length){
            while(j<arr.length&&arr[j]-arr[i]<key) {
                j++;
            }
            if(j<arr.length&&arr[j]-arr[i]==key){
                exist=true;
            }
//            while(j<arr.length&&arr[j]-arr[i]==key){
//                if(arr[j]-arr[i]==key){
//                    exist=true;
//                }
//                j++;
//            }
//            if(j<arr.length&&arr[j]-arr[i]>key){
//                j--;
//            }
//            if(j==arr.length){
//                j--;
//            }
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

}
