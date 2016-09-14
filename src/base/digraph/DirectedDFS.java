package base.digraph;

/**
 * 有向图--可达性
 * 深度优先遍历dfs
 */
public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];

        marked[s] = true;
        dfs(G, s);
    }

    public boolean marked(int v) {
        return marked[v];
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }
}
