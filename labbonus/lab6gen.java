package labbonus;

public class lab6gen {
    public static void main(String[] args) {
        int totalnum = 10;
        StringBuilder sb = new StringBuilder();
        sb.append(totalnum+"\n");
        while(totalnum-->0){
            int len = (int)(Math.random()*1e5);
            sb.append(len+"\n");
            int[] arr = new int[len];
            for(int i=0;i<len;i++){
                arr[i] = (int)(Math.random()*1e9);
                sb.append(arr[i]+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
