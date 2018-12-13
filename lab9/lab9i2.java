package lab9;

// firstly natively try brute force
// iterate over all possibilities

// that's squared! no need to take square root!
// ac

import java.util.*;

public class lab9i2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int totalnum = in.nextInt();

        while(totalnum-->0){
            int noden = in.nextInt(), setn = in.nextInt();
            MyNode[] nodes = new MyNode[noden];
            for (int i = 0; i < noden; i++) {
                nodes[i] = new MyNode(i, in.nextInt(), in.nextInt());
            }

            List<MyEdge> edges = new ArrayList<>((noden * (noden-1)));

            for (int i = 0; i < noden; i++) {
                for (int j = i+1; j < noden; j++) {
                    MyEdge e = new MyEdge(i,j,calcDist(nodes[i],nodes[j]));
                    edges.add(e);
                    e.enable=true;
                }
            }

            int[] setweights = new int[setn];
            int[][] sets = new int[setn][];

            List<List<MyEdge>> setedges = new ArrayList<>(setn);

            for (int i = 0; i < setn; i++) {
                // get set info
                int setnoden = in.nextInt(), setweight = in.nextInt();
                sets[i] = new int[setnoden];
                setweights[i] = setweight;

                for (int j = 0; j < setnoden; j++) {
                    sets[i][j] = in.nextInt()-1;
                }

                List<MyEdge> li = new ArrayList<>(((setnoden)*setnoden-1)/2);

                // add connections
                for (int j = 0; j < setnoden; j++) {
                    for (int k = j+1; k < setnoden; k++) {
                        MyEdge e = new MyEdge(sets[i][j],sets[i][k],0);
                        e.enable=false;
                        li.add(e);
                        edges.add(e);
                    }
                }

                setedges.add(li);
            }

            // do the kruskal algorithm
            // multiple times...

            Collections.sort(edges, Comparator.comparingLong(o->o.weight));

            int totalamount = 1 << setn;
            long[] mins = new long[totalamount];
            boolean[] setopen = new boolean[setn];
            for (int i = 0; i < totalamount; i++) {
                // i for sets

                long cost = 0;

                // set enable sets;
                for (int j = 0; j < setn; j++) {
                    // check whether to enable set j
                    if( ((1 << j) &i) > 0){
                        cost += setweights[j];

                        // closed before, need to open now
                        if(!setopen[j]){
                            for(MyEdge e:setedges.get(j)){
                                e.enable=true;
                            }
                        }
                        setopen[j] = true;
                    } else {
                        // opened before, need to close now
                        if(setopen[j]){
                            for(MyEdge e:setedges.get(j)){
                                e.enable=false;
                            }
                        }
                        setopen[j] = false;
                    }
                }

                // do more calculation

                UnionSet<MyNode> us = new UnionSet<>(noden);
                for (int j = 0; j < noden; j++) {
                    us.arr[j] = new UnionSetNode<>(j,nodes[j]);
                }

                int linked = 1;

                for (MyEdge e:edges){
                    // only the enabled ones
                    if(e.enable){
                        LinkedList<UnionSetNode<MyNode>> ll1 = us.findLink(e.from), ll2 = us.findLink(e.to);
                        if(ll1.getLast()!=ll2.getLast()){
                            cost+=e.weight;
                            linked++;
                            us.union2(e.from,e.to,ll1,ll2);
                        }
                        if(linked==noden){
                            break;
                        }
                    }
                }

                // save result
                mins[i] = cost;
            }

            long min = mins[0];
            for (int i = 0; i < totalamount; i++) {
                min = Math.min(min, mins[i]);
            }

//            System.out.println(Arrays.toString(mins));
            System.out.println(min);
        }
    }

    public static long calcDist(MyNode i, MyNode j){
        return (i.x-j.x)*(i.x-j.x)+(i.y-j.y)*(i.y-j.y);
    }

    static class MyEdge{
        int from,to,sum;
        long weight;
        boolean enable=true;

        public MyEdge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.sum = from+to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return String.format("%d <- %f %c-> %d",from+1,weight,enable?'-':'/',to+1);
        }
    }

    static class MyNode{
        int id,x,y;
//        List<MyEdge> edges;
//        List<MyEdge>[] setedges;

        public MyNode(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "MyNode{" +
                    "id=" + id +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
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

        public LinkedList<UnionSetNode<T>> findLink(int i){
            LinkedList<UnionSetNode<T>> ll = new LinkedList<>();
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


    public static List<List<Integer>> getC(int[] arr){

        int len = arr.length;
        int maxcnt = 1 << len;

        List<List<Integer>> all = new LinkedList<>();

        for (int p = 1; p < maxcnt; p++) {
            List<Integer> li = new LinkedList<>();
            for (int i = 0; i < len; i++) {
                if((1<<i & p)>0){
                    li.add(arr[i]);
                }
            }
            all.add(li);
        }

        return all;
    }
}
