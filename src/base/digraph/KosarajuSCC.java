package base.digraph;

/**
 * 有向图--强连通性
 * Kosaraju算法: 计算G反向图的逆后排序（拓扑排序），按此排序深度优先遍历G。
 * 原理：若G中存在路径从s到v, 那在反向图中, 按逆后排序必定是先遍历v才是s.
 * 如果在v的递归遍历中遍历到s.则s-v是强连通性
 */
public class KosarajuSCC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];

        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            dfs(G, w);
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }
}
