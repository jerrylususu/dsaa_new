package labbonus;

import java.util.Arrays;
import java.util.Scanner;

public class quicksort {
    public static boolean d = true;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while(totalnum-->0){
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i]=in.nextInt();
            }
            quicksort(arr,0,n);
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void quicksort(int[] arr, int l, int r){
        System.out.println("l="+l+" r="+r);
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
}
