package lab1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class lab0a {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();

        while(times-->0){
            int inn = in.nextInt();
            List<String> ins = new ArrayList<>(inn);

            for(int i=0;i<inn;i++){
                ins.add(in.next().toLowerCase());
            }

            int findn = in.nextInt();
            List<String> finds = new ArrayList<>(findn);
            for(int i=0;i<findn;i++) {
            	finds.add(in.next().toLowerCase());
            }
            Collections.sort(finds);

            boolean has=false;
            for(String s:ins){
            	if(Collections.binarySearch(finds, s)!=-1) {
            		System.out.println("Appeared");
            		has=true;
            		break;
            	}
            }
            if(!has){
                System.out.println("Not appeared");
            }


        }
    }

}
