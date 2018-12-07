//package lab8;
//
//import java.util.*;
//
//public class lab8e {
//
//    static final byte[][] move = {{1,0},{-1,0},{0,1},{0,-1}};
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while(totalnum-->0){
//
//            // read input
//            int row = in.nextInt(), col = in.nextInt();
//            byte[][] arr = new byte[row][col];
//            boolean[][] vis = new boolean[row][col];
//
//            for (int i = 0; i < row; i++) {
//                for (int j = 0; j < col; j++) {
//                    arr[i][j] = in.nextByte();
//                }
//            }
//
//            int cnt = 0;
//            int[] nextpos = new int[2];
//
//
//            while(nextpos[0]!=-1&&nextpos[1]!=-1){
////                System.out.println(Arrays.toString(nextpos));
//                // try to select the region of this group
//                regionMark(arr,vis,nextpos[0],nextpos[1],row,col);
//                cnt++;
//
//                // generate next coordinate
//                genNextPos(arr,vis,nextpos,row,col);
//            }
//
//            // output
//            System.out.println(cnt);
//
//        }
//    }
//
//    public static void regionMark(byte[][] arr, boolean[][] vis, int srow, int scol, int row, int col){
//        byte color = arr[srow][scol];
//        LinkedList<Map.Entry<Integer,Integer>> q = new LinkedList<>();
//        q.add(new AbstractMap.SimpleEntry<>(srow, scol));
//        vis[srow][scol] = true;
//
//        while(!q.isEmpty()){
//            Map.Entry<Integer,Integer> curentry = q.pollFirst();
//            int currow = curentry.getKey(), curcol = curentry.getValue();
//            for(byte[] m:move){
//
//                int newrow = currow+m[0], newcol = curcol+m[1];
//                if(!(0<=newrow && newrow<=row-1)){
//                    continue;
//                }
//
//                if(newcol==-1){
//                    newcol = col-1;
//                } else if (newcol == col){
//                    newcol = 0;
//                }
//
//                if(arr[newrow][newcol]==color && !vis[newrow][newcol]){
//                    vis[newrow][newcol] = true;
//                    q.addLast(new AbstractMap.SimpleEntry<>(newrow,newcol));
//                }
//            }
//        }
//    }
//
//    public static void genNextPos(byte[][] arr, boolean[][] vis, int[] nextpos, int row, int col){
//        int i=nextpos[0], j=nextpos[1];
//        while(i<row){
//            while(j<col){
//                if(!vis[i][j]){
//                    nextpos[0] = i;
//                    nextpos[1] = j;
//                    return;
//                }
//                j++;
//            }
//            i++;
//            j=0;
//        }
//        nextpos[0] = -1;
//        nextpos[1] = -1;
//    }
//}
