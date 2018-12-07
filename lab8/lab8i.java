package lab8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class lab8i {

    static class MyEdge{
        int fromid;
        int toid;
        int weight;
        int lastedgeid;

        public MyEdge(int fromid, int toid, int weight, int lastedgeid) {
            this.fromid = fromid;
            this.toid = toid;
            this.weight = weight;
            this.lastedgeid = lastedgeid;
        }

        @Override
        public String toString() {
            return "MyEdge{" +
                    "fromid=" + fromid +
                    ", toid=" + toid +
                    ", weight=" + weight +
                    ", lastedgeid=" + lastedgeid +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();;

        while(totalnum-->0){
            int noden = in.nextInt();
            int edgen = in.nextInt();

            int[] head = new int[noden];
            Arrays.fill(head,-1);
            MyEdge[] edgearr = new MyEdge[2*edgen];
            int cnt = 0;

            for (int i = 0; i < edgen; i++) {
                int from = in.nextInt()-1, to = in.nextInt()-1, w = in.nextInt();
                edgearr[cnt] = new MyEdge(from,to,w,head[from]);
                head[from] = cnt;
                cnt++;

                edgearr[cnt] = new MyEdge(to,from,w,head[from]);
                head[to] = cnt;
                cnt++;
            }

            TarjanHelperUndirected h = new TarjanHelperUndirected(head,edgearr);
            h.runTarjan(0);
            ArrayList<ArrayList<Integer>> cirs = h.cirs;
            System.out.println(cirs);

//            for(ArrayList<Integer> li:cirs){
//                if(li.size()==1) continue;
//
//                int end = li.get(0);
//                MyEdge cur = findEdge(li.get(0),li.get(1),head,edgearr);
//                int min = cur.weight;
//                while(cur.toid!=end){
//                    cur = findEdge(cur.toid,cur.)
//
//                }
//
//            }
        }
    }



    public static MyEdge findEdge(int fromid, int toid, int[] head,MyEdge[] edgearr){
        MyEdge cur = edgearr[head[fromid]];
        while(cur!=null){
            if(cur.toid==toid){
                return cur;
            } else {
                cur = edgearr[cur.lastedgeid];
            }
        }
        return null;
    }

    static class TarjanHelperUndirected{
        int[] dfn;
        int[] low;
        boolean[] instack;
        boolean[] vis;
        int[] nextlook;
        int noden;
        int[] head;
        int[] parent;
        MyEdge[] edgearr;
        Stack<Integer> s;
        ArrayList<ArrayList<Integer>> cirs;
        int index;

        public TarjanHelperUndirected(int[] head, MyEdge[] edgearr) {
            this.head = head;
            this.edgearr = edgearr;
            noden = head.length;
            dfn = new int[noden];
            low = new int[noden];
            instack = new boolean[noden];
            vis = new boolean[noden];
            parent = new int[noden];
            Arrays.fill(parent,-1);
            Arrays.fill(dfn,-1);
            Arrays.fill(low,-1);
            nextlook = new int[noden];
            System.arraycopy(head,0,nextlook,0,noden);
            index=0;
            s = new Stack<>();
            cirs = new ArrayList<>();
        }

        public void runTarjan(int u){
//            System.out.println(u+" "+s);
            index++;
            dfn[u] = index;
            low[u] = index;
            s.push(u);
            vis[u]=true;
            instack[u] = true;
            while(nextlook[u]!=-1){
                MyEdge v = edgearr[nextlook[u]];
                nextlook[u] = v.lastedgeid;
                if(parent[u]==v.toid){
                    continue;
                }
                if(!vis[v.toid]){
                    parent[v.toid]=u;
                    runTarjan(v.toid);
                    low[u] = Math.min(low[v.toid],low[u]);
                } else if (instack[v.toid]){
                    low[u] = Math.min(low[u],dfn[v.toid]);
                }
            }
            if(dfn[u]==low[u]){
                ArrayList<Integer> li = new ArrayList<>();
                int v = s.peek();
                do{
                    v = s.pop();
                    instack[v] = false;
//                    System.out.println(v+1);
                    li.add(v+1);
                }while(u!=v);
                cirs.add(li);
//                System.out.println("fin");
            }

        }

    }

    static class TarjanHelperDirected{
        int[] dfn;
        int[] low;
        boolean[] instack;
        boolean[] vis;
        int[] nextlook;
        int noden;
        int[] head;
        MyEdge[] edgearr;
        Stack<Integer> s;
        ArrayList<ArrayList<Integer>> cirs;
        int index;

        public TarjanHelperDirected(int[] head, MyEdge[] edgearr) {
            this.head = head;
            this.edgearr = edgearr;
            noden = head.length;
            dfn = new int[noden];
            low = new int[noden];
            instack = new boolean[noden];
            vis = new boolean[noden];
            Arrays.fill(dfn,-1);
            Arrays.fill(low,-1);
            nextlook = new int[noden];
            System.arraycopy(head,0,nextlook,0,noden);
            index=0;
            s = new Stack<>();
            cirs = new ArrayList<>();
        }

        public void runTarjan(int u){
//            System.out.println(u+" "+s);
            index++;
            dfn[u] = index;
            low[u] = index;
            s.push(u);
            vis[u]=true;
            instack[u] = true;
            while(nextlook[u]!=-1){
                MyEdge v = edgearr[nextlook[u]];
                nextlook[u] = v.lastedgeid;
                if(!vis[v.toid]){
                    runTarjan(v.toid);
                    low[u] = Math.min(low[v.toid],low[u]);
                } else if (instack[v.toid]){
                    low[u] = Math.min(low[u],dfn[v.toid]);
                }
            }
            if(dfn[u]==low[u]){
                ArrayList<Integer> li = new ArrayList<>();
                int v = s.peek();
                do{
                    v = s.pop();
                    instack[v] = false;
//                    System.out.println(v+1);
                    li.add(v+1);
                }while(u!=v);
                cirs.add(li);
//                System.out.println("fin");
            }

        }

    }


}
