//package lab2;
//
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.Scanner;
//
//public class lab2g5 {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int total = in.nextInt();
//
//        while(total-->0){
//            int size = in.nextInt();
//            gNode[] arr = new gNode[size];
//            gNode[] pos = new gNode[size];
//            for(int i=0;i<size;i++){
//                arr[i]=new gNode(0,in.nextInt());
//                pos[i]=arr[i];
//            }
//            Arrays.sort(arr, new Comparator<gNode>() {
//                @Override
//                public int compare(gNode o1, gNode o2) {
//                    return o1.val-o2.val;
//                }
//            });
//            for(int i=0;i<size;i++){
//                arr[i].n = i;
//                if(i!=0){
//                    arr[i-1].next=arr[i];
//                    arr[i].prev = arr[i-1];
//                }
//            }
//            int midpos = (size+1)/2-1;
//            gNode midcur = arr[midpos];
//            int[] rec = new int[(size+1)/2];
//            rec[0] = midcur.val;
//
//            int move =0;
//            double curmidval = midcur.val;
//            int t=1;
//            for(int i=size-1;i>0;i=i-2){
//               int diff1 = pos[i].val-midcur.val;
//               int diff2 = pos[i-1].val-midcur.val;
//
//               if(diff1>0){
//                   diff1=1;
//               } else if(diff1<0){
//                   diff1=-1;
//               }
//               if(diff2>0){
//                   diff2=1;
//               } else if(diff2<0){
//                   diff2=-1;
//               }
//
//               for(int j=i;j>=i-1;j--){
//                   if(pos[j].prev!=null){
//                       if(pos[j].next!=null){
//                           pos[j].prev.next=pos[j].next;
//                       } else {
//                           pos[j].prev.next=null;
//                       }
//                   }
//                   if(pos[j].next!=null){
//                       if(pos[j].prev!=null){
//                           pos[j].next.prev=pos[j].prev;
//                       }else {
//                           pos[j].next.prev=null;
//                       }
//                   }
//               }
//
//
//
//
//               int sum = diff1+diff2;
//               if(sum>0){
//                   if(midcur.prev!=null){
//                       midcur=midcur.prev;
//                   }
//               }else if(sum<0){
//                   if(midcur.next!=null){
//                       midcur=midcur.next;
//                   }
//               }
//
//               rec[t] = midcur.val;
//               t++;
//
//            }
//            //rec[(size+1)/2-1] = pos[0].val;
//            StringBuilder sb = new StringBuilder();
//            for(int i=(size+1)/2-1;i>=0;i--){
//                sb.append(rec[i]+" ");
//            }
//            sb.deleteCharAt(sb.length()-1);
//            System.out.println(sb.toString());
//        }
//    }
//}
//
//class gNode{
//    int n;
//    int val;
//    gNode next;
//    gNode prev;
//
//    public gNode(int n, int val) {
//        this.n = n;
//        this.val = val;
//    }
//
//    @Override
//    public String toString() {
//        return "gNode{" +
//                "n=" + n +
//                ", val=" + val +
//                '}';
//    }
//}
