package lab8;

import java.util.*;

public class lab8i2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();;

        while(totalnum-->0){
            int noden = in.nextInt();
            int edgen = in.nextInt();

            MyNode[] nodes = new MyNode[noden];
            for (int i = 0; i < noden; i++) {
                nodes[i] = new MyNode(i);
            }

            MyEdge[] edges = new MyEdge[edgen];
            for (int i = 0; i < edgen; i++) {
                int from=in.nextInt()-1, to=in.nextInt()-1, w=in.nextInt();
                MyEdge e = new MyEdge(i,from,to,w);
                nodes[from].edges.add(e);
                nodes[to].edges.add(e);
                edges[i] = e;
            }

            // check circles
            List<List<MyEdge>> cirs = findCircles(nodes,edges);

            // remove min edges
            for (List<MyEdge> li:cirs){
                int minweight = li.get(0).weight;
                int minid = 0;
                int size = li.size();
                for (int i = 1; i < size; i++) {
                    if(li.get(i).weight<minweight){
                        minid = i;
                        minweight = li.get(i).weight;
                    }
                }
                MyEdge minedge = li.get(minid);
//                nodes[minedge.from].edges.remove(minedge);
//                nodes[minedge.to].edges.remove(minedge);
//                li.remove(minid);
                minedge.removed = true;
                for(MyEdge edge:li){
                    edge.weight+=minweight;
                }
            }

            // do the tree change
            Arrays.sort(edges, new Comparator<MyEdge>() {
                @Override
                public int compare(MyEdge o1, MyEdge o2) {
                    return o2.weight-o1.weight;
                }
            });
            UnionSet<MyNode> us = new UnionSet<>(noden);
            for (int i = 0; i < noden; i++) {
                us.arr[i] = new UnionSetNode<>(i,nodes[i]);
            }
            long ans = 0;
            for (int i = 0; i < edgen; i++) {
                MyEdge curedge = edges[i];
                if(curedge.removed){
                    continue;
                }
//                System.out.println(i);
                ans+=us.arr[curedge.from].size*us.arr[curedge.to].size*curedge.weight;
                us.union(curedge.from,curedge.to);
            }
            System.out.println(ans);



        }
    }

    static class UnionSet<T>{
        UnionSetNode<T>[] arr;
        int size;

        public UnionSet(int size) {
            this.size = size;
            this.arr = new UnionSetNode[size];
        }

        public T find(int i){
            UnionSetNode<T> node = arr[i];
            while(node.parent!=-1){
                node = arr[node.parent];
            }
            return node.thing;
        }

        public List<UnionSetNode> findLink(int i){
            List<UnionSetNode> ll = new ArrayList<>();
            UnionSetNode<T> node = arr[i];
            ll.add(node);
            while(node.parent!=-1){
                node = arr[node.parent];
                ll.add(node);
            }
            return ll;
        }

        public void union(int i, int j){
            UnionSetNode<T> n1 = arr[i], n2 = arr[j];

            List<UnionSetNode> ll1 = findLink(n1.id);
            List<UnionSetNode> ll2 = findLink(n2.id);

            boolean ll1bigger = ll1.size()>ll2.size();
            UnionSetNode<T> f = (ll1bigger)?ll1.get(ll1.size()-1):ll2.get(ll2.size()-1);

            int fid = f.id;
            int ffid = f.parent;

            for(UnionSetNode<T> node:ll1){
                node.parent = fid;
            }
            for(UnionSetNode<T> node:ll2){
                node.parent = fid;
            }

            f.parent=ffid;
            f.size+=(ll1bigger)?ll2.size():ll1.size();
        }
    }

    static class UnionSetNode<T>{
        int id;
        int parent = -1;
        int size = 1;
        T thing;

        public UnionSetNode(int id, T thing) {
            this.id = id;
            this.thing = thing;
        }

        @Override
        public String toString() {
            return "UnionSetNode{" +
                    "id=" + id +
                    ", parent=" + parent +
                    ", size=" + size +
                    '}';
        }
    }

    public static List<List<MyEdge>> findCircles(MyNode[] nodes, MyEdge[] edges){
        List<List<MyEdge>> cirs= new ArrayList<>();
        boolean[] instack = new boolean[nodes.length];

        Stack<MyEdge> sedge = new Stack<>();
        Stack<MyNode> snode = new Stack<>();

        int latercnt = 0;

        snode.push(nodes[0]);
        while(!snode.isEmpty()){
            MyNode curnode = snode.peek();
            while (curnode.visno>=curnode.edges.size()){
                snode.pop();
                if(!sedge.isEmpty())
                    sedge.pop();
                if(snode.isEmpty()){
                    break;
                }
                curnode = snode.peek();
            }

            boolean needskip = false;
            if(snode.isEmpty()){
                needskip = true;
                break;
            }
            if(needskip){
                continue;
            }
            while (snode.peek().visno>=snode.peek().edges.size()){
                MyNode n = snode.pop();
                if(!sedge.isEmpty())
                    sedge.pop();
                instack[n.id] = false;
                if(snode.isEmpty()){
                    needskip = true;
                    break;
                }
            }
            if(needskip){
               continue;
            }
            MyEdge curedge = curnode.edges.get(curnode.visno++);

            while (curedge.visited){
                if(curnode.visno>=curnode.edges.size()){
                    snode.pop();
                    if(!sedge.isEmpty())
                        sedge.pop();

                    needskip = true;
                    break;
                }
                curedge = curnode.edges.get(curnode.visno++);
            }

            if(needskip){
                continue;
            }

            curedge.visited = true;
            snode.push(nodes[curedge.sum-curnode.id]);
            sedge.push(curedge);
            instack[curnode.id] = true;

            if(instack[curedge.sum-curnode.id]){
                // has met
                int backend = curedge.sum-curnode.id;
                List<MyEdge> li = new ArrayList<>();
                Stack<MyEdge> lateredge = new Stack<>();
                Stack<MyNode> laternode = new Stack<>();
                boolean later = false;
//                System.out.println(snode);
                snode.pop();
                while(snode.peek().id!=backend){
                    MyEdge popedge = sedge.pop();
                    li.add(popedge);
                    if(later){
                        lateredge.push(popedge);
                    }
                    MyNode popnode = snode.pop();
                    popnode.visno++;
                    if(popnode.visno<popnode.edges.size()){
                        later = true;
                        latercnt++;
                    }
                    if(later){
                        laternode.push(popnode);
                    }
                    instack[popnode.id]=false;
                }
                MyEdge popedge = sedge.pop();
                li.add(popedge);
                if(later){
                    lateredge.push(popedge);
                }

                cirs.add(li);


                if(later){
                    // do rebuild
                    while(!lateredge.empty()){
                        sedge.push(lateredge.pop());
                    }
                    while(!laternode.empty()){
                        snode.push(laternode.pop());
                    }
                }
            }
        }


//       for(List<MyEdge> li:cirs){
//           System.out.println(li);
//       }

        return cirs;

    }

    static class MyEdge{
        int from;
        int to;
        int sum;
        int id;
        int weight;
        boolean removed = false;
        boolean visited = false;

        @Override
        public String toString() {
            return String.format("(%d) %d <-- %d --> %d",id,from+1,weight,to+1);
        }

        public MyEdge(int id, int from, int to, int weight) {
            this.from = from;
            this.id = id;
            this.to = to;
            this.sum = from+to;
            this.weight = weight;
        }
    }

    static class MyNode{
        int id;
        int visno = 0;
        int listno = 0;
        List<MyEdge> edges;

        public MyNode(int id) {
            this.id = id;
            this.edges = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "MyNode{" +
                    "id=" + id +
                    ", visno=" + visno +
                    ", edges=" + edges.size() +
                    '}';
        }
    }

}
