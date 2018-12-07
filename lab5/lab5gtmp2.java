package lab5;

import java.util.Arrays;
import java.util.Scanner;

public class lab5gtmp2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] s = in.next().toCharArray();
        int[] f = new int[s.length+1];
        int[] nex = new int[s.length+1];
        int n = s.length;
        //int j = 0;
        f[0] = f[1] = 0;
        nex[0] = nex[1] = 0;
        for (int i = 1; i < n; i++) {
            int j = f[i];
            while (j!=0 && s[i] != s[j])
                j = f[j];
            f[i + 1] = nex[i + 1] = (s[i] == s[j] ? j + 1 : 0);
            if (nex[i + 1] == j + 1 && s[i + 1] == s[j + 1])
                nex[i + 1] = nex[j + 1];
        }
        System.out.println(Arrays.toString(f));
        System.out.println(Arrays.toString(nex));
    }


}
