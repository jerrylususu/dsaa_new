package lab6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class lab6a3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while(totalnum-->0){
            int n = in.nextInt();
            List<TreeNode> li = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                li.add(new TreeNode(i+1));
            }
            for (int i = 0; i < n-1; i++) {
                int n1 = in.nextInt()-1;
                int n2 = in.nextInt()-1;
                TreeNode tn1 = li.get(n1);
                TreeNode tn2 = li.get(n2);
                tn1.li.add(tn2);
                tn2.li.add(tn1);
            }
            List<Integer> leaves = new ArrayList<>(n);
            for (TreeNode tn:li){
                if(tn.li.size()==1&&tn.val!=1){
                    leaves.add(tn.val);
                }
            }
            for (int i = 0; i < leaves.size(); i++) {
                if(i!=leaves.size()-1) System.out.print(leaves.get(i)+" ");
                else System.out.println(leaves.get(i));
            }
        }
    }
}

class TreeNode{
    int val;
    List<TreeNode> li;

    public TreeNode(int i){
        this.val=i;
        this.li=new ArrayList<>();
    }

    public void putNode(TreeNode n){
        li.add(n);
    }

    public List<TreeNode> getNodes(){
        return li;
    }

}
