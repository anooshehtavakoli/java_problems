package treesandgraphs;

import java.util.LinkedList;

/**
 *
 * 4.1: Given a directed graph, disign an algorithm to find out whether there is a route between two nodes.
 *
 * Created by anoosheh on 1/16/18.
 */
/*
public class RouteBetweenNodes {
    /*
    this can be solved by just simple graph traversal, such as depth first search or breath first search.
    we start with one of the two nodes and during traversal, check if we visit the other node or not.
    we should mark any node found in the course of algorithm as "already visited" to avoid cycles and repetition of the nodes.
     */
/*
    class Node {
        public String name;
        public Node[] children;
    }

    class Graph {
        public Node[] nodes;
    }

    enum State { Unvisited, Visited, Visiting}

    boolean search(Graph g, Node start, Node end) {
        if (start == end) {
            return true;
        }

        // operate as Queue
        LinkedList<Node> q = new LinkedList<Node>();

        for (Node u : g.getNodes()) {
            u.state = State.Unvisited;
        }
        start.state = State.Visiting;
        q.add(start);
        Node u;
        while (!q.isEmpty()) {
            u = q.removeFirst();  // i.e., dequeue()
            if (u != null) {
                for (Node v : u.getAdjacent()) {
                    if (v.state == State.Unvisited) {
                        if (v == end) {
                            return true;
                        } else {
                            v.state = State.Visiting;
                            q.add(v);
                        }
                    }
                }
                u.state = State.Visited;
            }
        }
        return false;
    }
}
*/