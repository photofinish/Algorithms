package digraph;

import base.Bag;

/**
 * Created by Home on 2016/9/14.
 */
public class Digraph {

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    Digraph(int V) {
        this.V = V;
        E = 0;
        adj = new Bag[V];
        for (int i = 0; i < V; i++)
            adj[i] = new Bag<>();
    }

    public int V() { return V; }

    public int E() { return E; }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj[v])
                R.addEdge(w, v);
        }
        return R;
    }

}
