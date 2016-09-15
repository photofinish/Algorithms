package ewdigraph;

import digraph.Topological;

/**
 * 加权有向图--最短路径
 *
 */
public class AcyclicSP {

    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];

        for (int i = 0; i < G.V(); i++)
            distTo[i] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        Topological top = new Topological(G);
    }
}
