package digraph;

import ewdigraph.EdgeWeightedDigraph;

/**
 * 有向图--拓扑排序
 * 深度优先遍历，在递归回溯时,把遍历的顶点压入栈。遍历完成的栈先入后出顺序即拓扑排序。
 */
public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph G) {
        DirectedCycle cyclefinder = new DirectedCycle(G);
        if (!cyclefinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public boolean isDAG() {
        return order != null;
    }

    public Iterable<Integer> order() {
        return order;
    }
}
