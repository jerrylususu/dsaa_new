package lab4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class lab3e {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();

        while(total-->0){
            int len = in.nextInt();
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) {
                arr[i] = in.nextInt();
            }
            int min = arr[len-1], minp=len-1;
            int[] minval = new int[len];
            int[] minpos = new int[len];
            for(int i=len-1;i>=0;i--){
                if(arr[i]<min){
                    min=arr[i];
                    minp=i;
                }
                minval[i]=min;
                minpos[i]=minp;
            }
            stack s = new stack(len+10);
            LinkedList<Integer> li = new LinkedList<>();
            int cur=0;
            while(cur<len){
                System.out.println(Arrays.toString(s.arr));
                if(s.size==0){
                    s.push(arr[cur]);
                    cur++;
                } else {
                    if(minval[cur]>=s.peek()){
                        li.add(s.pop());
                        //cur++;
                    } else if (minval[cur]<s.peek()){
                        int endpos = minpos[cur];
                        for(int i=cur;i<=endpos;i++){
                            s.push(arr[i]);
                        }
                        cur=endpos+1;
                    }
                }
            }
            while(s.size>0){
                li.addLast(s.pop());
            }
            StringBuilder sb = new StringBuilder();
            for(int i:li){
                sb.append(i+" ");
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb.toString());

        }
    }
}

class stack{
    int maxsize;
    int size;
    int[] arr;
    int topptr;

    public stack (int s){
        this.maxsize = s;
        this.size = 0;
        this.topptr = -1;
        arr = new int[maxsize];
    }

    public void push(int c){
        topptr++;
        if(topptr<=maxsize){
            size++;
            arr[topptr]=c;
        } else {
            System.out.println("Full");
        }
    }

    public int pop(){
        if(size>=0){
            int tmp = arr[topptr];
            topptr--;
            size--;
            return tmp;
        } else {
            System.out.println("Empty");
            return '0';
        }
    }

    public int peek(){
        return arr[topptr];
    }

}
