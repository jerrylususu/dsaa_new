//package lab6;
//
//public class lab6cgen {
//    public static void main(String[] args) {
//        StringBuilder sb = new StringBuilder();
//        int totalnum = 10;
//        sb.append(totalnum+"\n");
//        while (totalnum-->0){
//            int n = (int) (Math.random()*100);
//            int size = n;
//            sb.append(n+"\n");
//            for (int i = 0; i < n; i++) {
//                sb.append((int)(Math.random()*1000)+" ");
//            }
//            sb.append("\n");
//
//            int op = (int) (Math.random()*100);
//            int realop = 0;
//            StringBuilder sb2 = new StringBuilder();
//            for (int i = 0; i < op; i++) {
//                int opn = (int)(Math.random()*3)+1;
//                if(opn==1){
//                    size++;
//                    int d = (int)(Math.random()*1000);
//                    sb2.append(1+" "+d+"\n");
//                } else if (opn==2&&size>0){
//                    size++;
//                    sb2.append(2+"\n");
//                } else if (opn==3&&size>0) {
//                    size--;
//                    sb2.append(3+"\n");
//                }
//
//            }
//            sb.append(realop+"\n");
//            sb.append(sb2);
//            sb2.delete(0,sb2.length());
//
//        }
//        System.out.print(sb);
//    }
//}
