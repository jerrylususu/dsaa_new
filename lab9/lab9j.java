package lab9;

// hdu 6370

// first tried a brute force
// tle

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class lab9j {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while(totalnum-->0){
            int n = in.nextInt();
            MyNode[] nodes = new MyNode[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new MyNode(i);
            }

            for (int i = 0; i < n; i++) {
                int to = in.nextInt()-1;
                boolean toIsWolf = in.next().charAt(0)=='w';
                MyEdge e = new MyEdge(i,to,toIsWolf);
                nodes[i].out = e;
                nodes[to].in.add(e);
            }

            // try to brute force traversal
            int count = 0;
            for (int i = 0; i < n; i++) {
                if(nodes[i].out.toIsWolf){
                    continue;
                }
                if(nodes[i].isWolf){
                    continue;
                }

                int start = i;
                MyEdge curedge = nodes[i].out;
                // move around villager edge
                while(!curedge.toIsWolf){
                    curedge = nodes[curedge.to].out;
                }
                // we found a wolf
                if(curedge.to==start){
                    if(!nodes[curedge.to].marker){ // haven't visited before
                        count++;
                        MyNode wolfnode = nodes[curedge.to];
                        wolfnode.isWolf = true;
                        wolfnode.marker = true; // now visited

                        // do backward cleaning
                        LinkedList<MyNode> q = new LinkedList<>();
                        q.addLast(wolfnode);
                        while(!q.isEmpty()){
                            MyNode curnode = q.pollFirst();
                            for(MyEdge e:curnode.in){
                                if(!e.toIsWolf){
                                    q.addLast(nodes[e.from]);
                                }
                            }
                            if(!curnode.marker){
                                count++;
                                curnode.isWolf=true;
                                curnode.marker=true;
                            }
                        }
                    }
                }
            }
            System.out.println(count);
        }
    }

    static class MyEdge{
        int from, to, sum;
        boolean toIsWolf;

        public MyEdge(int from, int to, boolean toIsWolf) {
            this.from = from;
            this.to = to;
            this.sum = from+to;
            this.toIsWolf = toIsWolf;
        }
    }

    static class MyNode{
        int id;
        boolean isWolf = false;
        boolean marker = false;
        MyEdge out = null;
        List<MyEdge> in;

        public MyNode(int id) {
            this.id = id;
            this.in = new LinkedList<>();
        }

        @Override
        public String toString() {
            return "MyNode{" +
                    "id=" + id +
                    ", isWolf=" + isWolf +
                    ", marker=" + marker +
                    ", out=" + out +
                    ", in=" + in.size() +
                    '}';
        }
    }
}
