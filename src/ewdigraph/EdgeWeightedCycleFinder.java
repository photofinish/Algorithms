package ewdigraph;

import base.Stack;

/**
 *
 */
public class EdgeWeightedCycleFinder {

    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private Stack<DirectedEdge> cycle;
    private boolean[] onStack;

    public EdgeWeightedCycleFinder(EdgeWeightedDigraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) dfs(G, v);
        }
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        //TODO
//        onStack[v] = true;
//        marked[v] = true;
//        for (DirectedEdge e : G.adj(v)) {
//            int w = e.to();
//            if (this.hasCycle()) return;
//            else if (!marked[w]) {
//                edgeTo[w] = e;
//                dfs(G, w);
//            }
//            else {
//                double weight = e.weight();
//                for (DirectedEdge x = edgeTo[v]; x.from() != w; x = edgeTo[x.from()])
//                    weight += x.weight();
//            }
//
//
//
//
//            else if (onStack[w]) {
//                cycle = new Stack<>();
//                for (DirectedEdge x = edgeTo[v]; x.from() != w ; x = edgeTo[x.from()])
//                    cycle.push(x.to());
//                cycle.push(w);
//                cycle.push(v);
//            }
//        }
//        onStack[v] = false;
    }

}
