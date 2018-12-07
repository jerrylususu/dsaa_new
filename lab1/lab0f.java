package lab1;

import java.util.*;

public class lab0f {

    final static char[] narr = {'a','e','i','o','u','w','y'};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();

        while(times-->0){
            String s = in.next();

//            find unique letters
            Set<Character> set = new HashSet<>();
            for(int i=0;i<s.length();i++){
                set.add(s.charAt(i));
            }

            for(char c:narr){
                if(set.contains(c)){
                    set.remove(c);
                }
            }

            List<Character> li = new ArrayList<>(set.size());
            for(char c:set){
                li.add(c);
            }

//            compare and go
            int maxPair = 0;
            int size = set.size();
            int all = (2<<size-1)-1; // non empty real subset
            for(int i=0;i<all;i++){
                String snow = new String(s);
                int now = i;
                for(int j=0;j<size;j++){
                    if((now&1)==1){
                        snow = snow.replace(li.get(j),Character.toUpperCase(li.get(j)));
                    }
                    now=now>>1;
                }
                System.out.println(snow);
                int cur=0;
                int length=s.length();
                for(int k=0;k<length-1;k++){
                    if(isBeautifulPair(snow.charAt(k),snow.charAt(k+1))){
                        cur++;
                    }
                }

                System.out.println(cur);

                if(cur>maxPair){
                    maxPair=cur;
                }
            }
            System.out.println(maxPair);
        }
    }

    public static boolean isBeautifulPair(char a,char b){
        if(isConsonants(a)&&isConsonants(b)){
            if((Character.isLowerCase(a)&&Character.isUpperCase(b))||(Character.isLowerCase(b)&&Character.isUpperCase(a))){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isConsonants(char c){
        c = Character.toLowerCase(c);
        for(int i=0;i<narr.length;i++){
            if(c==narr[i]){
                return false;
            }
        }
        return true;
    }
}
