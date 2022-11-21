package roguelike.pathfinding;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Node implements Comparable<Node> {
    public int x;
    public int y;

    public Node parent = null;
    public List<Edge> neighbours;

    public double f = Double.MAX_VALUE;
    public double g = Double.MAX_VALUE;
    public double h;

    Node(double h, int x, int y) {
        this.h = h;
        this.x = x;
        this.y = y;
        this.neighbours = new ArrayList<>();
    }

    @Override
    public int compareTo(Node o) {
        return Double.compare(this.f, o.f);
    }

    public void addBranch(int weight, Node node) {
        Node.Edge newEdge = new Node.Edge(weight, node);
        neighbours.add(newEdge);
    }

    public double calculateHeuristic(Node target) {
        return this.h;
    }

    public static class Edge {
        public int weight;
        public Node node;

        Edge(int weight, Node node) {
            this.weight = weight;
            this.node = node;
        }
    }

    public static Node aStar(Node start, Node target){
        PriorityQueue<Node> closed = new PriorityQueue<>();
        PriorityQueue<Node> open = new PriorityQueue<>();

        start.f = start.g + start.calculateHeuristic(target);
        open.add(start);

        while (!open.isEmpty()){
            Node n = open.peek();

            if(n == target){
                return n;
            }

            for(Node.Edge edge : n.neighbours){
                Node m = edge.node;
                double totalWeight = n.g + edge.weight;

                if(!open.contains(m) && !closed.contains(m)){
                    m.parent = n;
                    m.g = totalWeight;
                    m.f = m.g + m.calculateHeuristic(target);
                    open.add(m);
                }else {
                    if(totalWeight < m.g){
                        m.parent = n;
                        m.g = totalWeight;
                        m.f = m.g + m.calculateHeuristic(target);

                        if(closed.contains(m)){
                            closed.remove(m);
                            open.add(m);
                        }
                    }
                }
            }

            open.remove(n);
            closed.add(n);
        }

        return null;
    }
}