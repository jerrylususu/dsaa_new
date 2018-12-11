package lab9;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class lab9b4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }

    static class MyEdge{
        int from,to,sum,weight;

        public MyEdge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.sum = from + to;
            this.weight = weight;
        }
    }

    static class MyNode{
        int id;
        List<MyEdge> edges;

        public MyNode(int id) {
            this.id = id;
            this.edges = new LinkedList<>();
        }
    }
}
