/*
package lab7;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class lab7a3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while(totalnum-->0){
            int n = in.nextInt();

            int tmp = 1;
            int depmax = 1;
            while(tmp<=n){
                tmp = tmp*2;
                depmax++;
            }

            MyNode[] arr = new MyNode[n];
            for (int i = 0; i < n; i++) {
                arr[i] = new MyNode(i+1);
            }

            for (int i = 0; i < n; i++) {
                MyNode curnode = arr[i];
                int l = in.nextInt(), r = in.nextInt();
                if(l!=0){
                    curnode.left = arr[l-1];
                    curnode.left.parent = curnode;
                }
                if(r!=0){
                    curnode.right = arr[r-1];
                    curnode.right.parent = curnode;
                }
            }

            MyNode root = arr[0];
            boolean[] prevcheck = new boolean[1];
            prevcheck[0] = true;
            while(root.parent!=null){
                root = root.parent;
            }
            root.depth=1;

            int[] depcount = new int[depmax];
            setDepth(root,depcount,prevcheck);
//            System.out.println(Arrays.toString(depcount));
            boolean check = true;
            if(!prevcheck[0]||depcount[depcount.length-1]!=0){
                check = false;
            } else {
                LinkedList<MyNode> queue = new LinkedList<>();
                queue.add(root);
                boolean metempty = false;
                int currentlayer = 1;
                while(queue.height()!=0){
                    MyNode current = queue.pollFirst();

                    if(current.depth!=currentlayer){
                        currentlayer = current.depth;
                        metempty = false;
                    }

                    if(metempty&&((current.left!=null)||(current.right!=null))){
                        check = false;
                        break;
                    }

                    if(current.left!=null){
                        queue.addLast(current.left);
                    } else {
                        metempty = true;
                    }

                    if(current.right!=null&&!metempty){
                        queue.addLast(current.right);
                    } else {
                        if(metempty&&current.right!=null){
                            check = false;
                            break;
                        } else {
                            metempty = true;
                        }

                    }
                }


            }



            System.out.println(check?"Yes":"No");



        }
    }

    public static void setDepth(MyNode node,int[] depcount,boolean[] prevcheck){
        if(node.depth-1<=depcount.length-1){
            depcount[node.depth-1]++;
        } else {
            prevcheck[0] = false;
            return;
        }

        if(node.left!=null){
            node.left.depth = node.depth+1;
            setDepth(node.left,depcount,prevcheck);
        }

        if(node.right!=null){
            node.right.depth = node.depth+1;
            setDepth(node.right,depcount,prevcheck);
        }
    }
}

class MyNode{
    int no;
    int depth;
    MyNode left;
    MyNode right;
    MyNode parent;

    public MyNode(int i){
        no=i;
        depth = -1;
    }

    @Override
    public String toString() {
        return "MyNode{" +
                "no=" + no +
                '}';
    }
}

*/
