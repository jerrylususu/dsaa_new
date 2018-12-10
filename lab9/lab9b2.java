package lab9;

import java.util.*;

// tried to use Kruskal
// can not pass example

public class lab9b2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while (totalnum-- > 0) {
            // getting input
            int noden = in.nextInt();
            int edgen = in.nextInt();

            MyNode[] nodes = new MyNode[noden];
            for (int i = 0; i < noden; i++) {
                nodes[i] = new MyNode(i);
            }

            MyEdge[] edges = new MyEdge[edgen];


            int allmax = 0;
            int allmin = Integer.MAX_VALUE;
            for (int i = 0; i < edgen; i++) {
                int from = in.nextInt() - 1, to = in.nextInt() - 1, w = in.nextInt();
                MyEdge e = new MyEdge(i, from, to, w);
                nodes[from].edges.add(e);
                nodes[to].edges.add(e);
                edges[i] = e;
                allmax = Math.max(allmax, w);
                allmin = Math.min(allmin, w);
            }

            Arrays.sort(edges, new Comparator<MyEdge>() {
                @Override
                public int compare(MyEdge o1, MyEdge o2) {
                    return o1.weight - o2.weight;
                }
            });

            int sum = 0;

            TreeSet<Integer> ts = new TreeSet<>();
            for(MyEdge e:edges){
                boolean b2 = ts.contains(e.to);
                boolean b1 = ts.contains(e.from);

                if(b1&&b2){
                    continue;
                }
                if(!b1){
                    ts.add(e.from);
                }
                if(!b2){
                    ts.add(e.to);
                }
                sum+=e.weight;
            }
            System.out.println(sum);
        }
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
            return String.format("(%d) %d <-- %d %s --> %d",id,from,weight,removed?"/":"-",to);
        }

        public MyEdge(int id, int from, int to, int weight) {
            this.from = from;
            this.id = id;
            this.to = to;
            this.sum = from+to;
            this.weight = weight;
        }
    }

    static class MyNode implements Comparable<MyNode>{
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
            return
                    "id=" + id +
                            ", visno=" + visno +
                            ", edges=" + edges.size()
                    ;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MyNode myNode = (MyNode) o;

            return id == myNode.id;
        }

        @Override
        public int hashCode() {
            return id;
        }


        @Override
        public int compareTo(MyNode o) {
            return this.id-o.id;
        }
    }

    static class UnionSet<T>{
        UnionSetNode<T>[] arr;
        int size;

        public UnionSet(int size) {
            this.size = size;
            this.arr = new UnionSetNode[size];
        }

        public UnionSetNode<T> find(int i){
            UnionSetNode<T> node = arr[i];
            while(node.parent!=-1){
                node = arr[node.parent];
            }
            return node;
        }

        public List<UnionSetNode<T>> findLink(int i){
            List<UnionSetNode<T>> ll = new ArrayList<>();
            UnionSetNode<T> node = arr[i];
            ll.add(node);
            while(node.parent!=-1){
                node = arr[node.parent];
                ll.add(node);
            }
            return ll;
        }

        public void unionWithInfo(int i, int j, UnionSetNode<T> f1, UnionSetNode<T> f2){
            UnionSetNode<T> n1 = arr[i], n2 = arr[j];

            boolean f1bigger = f1.size>=f2.size;
            if(!f1bigger){
                UnionSetNode<T> tmp = f1;
                f1 = f2;
                f2 = tmp;
            }

            f2.parent = f1.id;
            f1.size += f2.size;
        }

        public void union(int i, int j){
            UnionSetNode<T> n1 = arr[i], n2 = arr[j];

            UnionSetNode<T> f1 = find(n1.id);
            UnionSetNode<T> f2 = find(n2.id);

            boolean f1bigger = f1.size>=f2.size;
            if(!f1bigger){
                UnionSetNode<T> tmp = f1;
                f1 = f2;
                f2 = tmp;
            }

            f2.parent = f1.id;
            f1.size += f2.size;
        }

        public void union2(int i, int j, List<UnionSetNode<T>> ll1, List<UnionSetNode<T>> ll2){
            UnionSetNode<T> n1 = arr[i], n2 = arr[j];

            boolean ll1bigger = ll1.size()>=ll2.size();
            UnionSetNode<T> f = (ll1bigger)?ll1.get(ll1.size()-1):ll2.get(ll2.size()-1);

            int fid = f.id;
            int ffid = f.parent;

            if(ll1bigger){
                for(UnionSetNode<T> node:ll1){
                    if(node.id!=fid&&node.parent!=fid&&node.parent!=-1){
                        arr[node.parent].size-=node.size;
                        node.parent=fid;
                        //f.size+=node.size;
                    }
                }
                for(UnionSetNode<T> node:ll2){
                    if(node.id!=fid){
                        UnionSetNode<T> updatenode = node;
                        int removesize = node.size;
//                        if(node.parent!=-1){
//                            arr[node.parent].size-=node.size;
//                        }
                        while(updatenode.parent!=-1){
                            arr[updatenode.parent].size-=removesize;
                            updatenode = arr[updatenode.parent];
                        }
                        node.parent=fid;
                        f.size+=node.size;
                    }
                }
            } else {
                for(UnionSetNode<T> node:ll1){
                    if(node.id!=fid){
                        UnionSetNode<T> updatenode = node;
                        int removesize = node.size;
//                        if(node.parent!=-1){
//                            arr[node.parent].size-=node.size;
//                        }
                        while(updatenode.parent!=-1){
                            arr[updatenode.parent].size-=removesize;
                            updatenode = arr[updatenode.parent];
                        }
                        node.parent=fid;
                        f.size+=node.size;
                    }
                }
                for(UnionSetNode<T> node:ll2){
                    if(node.id!=fid&&node.parent!=fid&&node.parent!=-1){
                        arr[node.parent].size-=node.size;
                        node.parent=fid;
                        //f.size+=node.size;
                    }
                }

            }

            f.parent=ffid;
//            f.size+=(ll1bigger)?ll2.size():ll1.size();
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
}
