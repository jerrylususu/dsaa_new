//package lab6;
//
//import java.util.*;
//
//public class lab6a {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int totalnum = in.nextInt();
//
//        while(totalnum-->0){
//            int n = in.nextInt();
//            HashMap<Integer,Boolean> hm = new HashMap<>();
//            for (int i = 0; i < n-1; i++) {
//                int p = in.nextInt();
//                int s = in.nextInt();
//                if(hm.containsKey(p)){
//                    hm.put(p,true);
//                }
//                if(!hm.containsKey(s))
//                    hm.put(s,false);
//            }
//            ArrayList<Integer> li = new ArrayList<>(n);
//            for(Map.Entry<Integer,Boolean> en:hm.entrySet()){
//                if(en.getValue()==false){
//                    li.add(en.getKey());
//                }
//            }
//            Collections.sort(li);
//            for (int i = 0; i < li.size(); i++) {
//                System.out.print(li.get(i)+" ");
//            }
//        }
//    }
//}
