package lab4;

import java.util.Scanner;

public class lab3d {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();

        int[][] map = {{0,1,2,3},{0,1,3,2},{0,2,1,3},{0,2,3,1},{0,3,1,2},{0,3,2,1},{1,0,2,3},{1,0,3,2},{1,2,0,3},{1,2,3,0},
                {1,3,0,2},{1,3,2,0},{2,0,1,3},{2,0,3,1},{2,1,0,3},{2,1,3,0},{2,3,0,1},{2,3,1,0},{3,0,1,2},{3,0,2,1},
                {3,1,0,2},{3,1,2,0},{3,2,0,1},{3,2,1,0}};

        while(total-->0){
            int height = in.nextInt();
            int width = in.nextInt();
            char[][] arr = new char[height][width];
            int sx=0, sy=0, ex=0, ey=0;
            for(int i=0;i<height;i++){
                String s = in.next();
                for(int j=0;j<width;j++){
                    arr[i][j] = s.charAt(j);
                    if(arr[i][j]=='S'){
                        sx=j;sy=i;
                    } else if(arr[i][j]=='E'){
                        ex=j;ey=i;
                    }
                }
            }
            String ins = in.next();
            int len = ins.length();
            int x=sx, y=sy;
            int sum=0;
            for(int p=0;p<24;p++){
                boolean oneok = true;
                //init every time
                x=sx; y=sy;
                for(int s=0;s<len;s++){
                    //get op
                    //System.out.println((int)ins.charAt(s)-48);
                    int op = map[p][(int)ins.charAt(s)-48];
                    //do move
                    if(op==0){
                        y--;
                    } else if(op==1){
                        y++;
                    } else if(op==2){
                        x--;
                    } else if (op==3){
                        x++;
                    }
                    //check valid
                    if(x<0||x>=width||y<0||y>=height){
                        oneok = false;
                        break;
                    }
                    if(arr[y][x]=='#'){
                        oneok = false;
                        break;
                    } else if(arr[y][x]=='E'){
                        break;
                    }

                }
                if(oneok){
                    if(arr[y][x]=='E'){
                        sum++;
                    }
                }
            }
            System.out.println(sum);

        }
    }
}
