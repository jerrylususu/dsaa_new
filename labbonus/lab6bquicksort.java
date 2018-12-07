package labbonus;

        import java.util.*;

public class lab6bquicksort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while(totalnum-->0){
            int n = in.nextInt();
            int k = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(findKth(arr,0,n,k));
//            System.out.println(rearray(arr,0,n));
        }
    }

    public static int findKth(int[] arr, int s, int e, int k){
        if(s==e){
            return arr[s];
        }

        int[] res = rearray(arr,s,e);
        int relp = res[1] ;
        int absp = res[0];
        // if the 1st min, p=0, need to +1 later
        // but to make the array index easier, will add in judge, but not here

        int pivot = res[2];
        // just in case, if we need to return immediately

        if(relp+1==k){
            return pivot;
        } else if (relp+1<k){
            return findKth(arr,absp,e,k-relp);
        } else {
            return findKth(arr,s,absp,k);
        }
    }

    // return: int[3]: absolute position, relative position, pivot
    public static int[] rearray(int[] arr, int s, int e){
        System.out.println("s="+s+" e="+e);
        // choose pivot
        // must be in the current range
        int pivotidx = (int)(Math.random()*(e-s))+s;
//        int pivotidx = 2;
        int pivot = arr[pivotidx];
        System.out.println("idx="+pivotidx+" pivot="+pivot);
        System.out.println(Arrays.toString(arr));

        // rearrange
        int i=s,j=e-1;
        while(i<j){
            // find the correct pair to exchange
            while(arr[i]<pivot){
                i++;
            }
            while(arr[j]>pivot){
                j--;
            }

            // swap while keep the pivot index
            if(i<j){
                System.out.println("swap triggered"+i+j);
                if(i==pivotidx){
                    pivotidx=j;
                } else if(j==pivotidx){
                    pivotidx=i;
                }
                swap(arr,i,j);

                // only move when not pointing at pivot
                if(i==pivotidx){
                    j--;
                } else if(j==pivotidx){
                    i++;
                } else {
                    i++;j--;
                }
                System.out.println(Arrays.toString(arr));
            } else if(i==j){
                break;
            } else {
                // this situation is not possible
                // a number that's both larger than pivot and smaller tha pivot doesn't exist
            }
        }

        System.out.println(Arrays.toString(arr));

//        while(pivotidx-1>=0&&arr[pivotidx-1]==pivot){
//            pivotidx--;
//        }

        int[] res = {pivotidx, pivotidx-s, pivot};

//        pivotidx=pivotidx-s;
        System.out.println("idx="+pivotidx+" this="+(pivotidx-s)+" pivot="+pivot);

        return res;
    }

    public static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
