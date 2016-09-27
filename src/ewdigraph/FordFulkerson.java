package ewdigraph;

import base.Queue;

/**
 * 流量网络的边
 */
class FlowEdge {
    private final int v;
    private final int w;
    private final double capacity;
    private double flow;

    FlowEdge(int v, int w, double capacity) {
        this.v = v;
        this.w = w;
        this.capacity = capacity;
        this.flow = 0;
    }

    public int from() { return v; }
    public int to() { return w; }
    public double capacity() { return capacity; }
    public double flow() { return flow; }

    public int other(int vertex) {
        if (vertex == v) return w;
        else if (vertex == w)return v;
        else throw new RuntimeException();
    }

    public double residualCapacityTo(int vertex) {
        if (vertex == from()) return flow;
        else if (vertex == to()) return capacity - flow;
        else throw new RuntimeException();
    }

    public void addResidualFlowTo(int vertex, double delta) {
        if (vertex == from()) flow -= delta;
        else if (vertex == to()) flow += delta;
        else throw new RuntimeException();
    }

    public String toString() {
        return String.format("%d->%d %.2f %.2f", v, w, capacity, flow);
    }
}

class FlowNetwork {
    // TODO
    private final int V;
    private int E;

    public FlowNetwork(int V) {
        this.V = V;
        E = 0;
    }

    public int V() { return V; }
    public int E() { return E; }
    public void addEdge(FlowEdge e) {

    }

    public Iterable<FlowEdge> adj(int v) {
        return null;
    }

    public Iterable<FlowEdge> edges() {
        return null;
    }

    @Override
    public String toString() {
        return "";
    }
}

public class FordFulkerson {
    private boolean[] marked;
    private FlowEdge[] edgeTo;
    private double value;   // 当前最大流量

    FordFulkerson(FlowNetwork G, int s, int t) {
        while (hasAugmentingPath(G, s, t)) {    // 寻找一条增广路径
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = edgeTo[v].other(v)) // 计算能增大多少流量
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
            for (int v = t; v != s; v = edgeTo[v].other(v)) // 加大流量
                edgeTo[v].addResidualFlowTo(v, bottle);
            value += bottle;
        }
    }

    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        marked = new boolean[G.V()];   // 标记已知的点
        edgeTo = new FlowEdge[G.V()]; // 路径上到点的最后一条边
        Queue<Integer> q = new Queue<>();

        marked[s] = true;
        q.enqueue(s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (FlowEdge e : G.adj(v)) {
                int w = e.other(v);
                if (e.residualCapacityTo(w) > 0 && !marked[w]) {  // 正向边返回剩余流量，逆向边返回流量
                    edgeTo[w] = e;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
        return marked[t];
    }

    private boolean localEq(FlowNetwork G, int v) {
        // 检查定点的局部平衡
        double EPSILION = 1E-11;
        double netflow = 0.0;
        for (FlowEdge e : G.adj(v))
            if (v == e.from()) netflow -= e.flow();
            else netflow += e.flow();
        return Math.abs(netflow) < EPSILION;
    }

    private boolean isFeasible(FlowNetwork G, int s, int t) {
        // 确认每条边的流量非负且小于等于边容量
        for (int v = 0; v < G.V(); v++)
            for (FlowEdge e : G.adj(v))
                if (e.flow() < 0 || e.flow() > e.capacity())
                    return false;

        // 检查每个顶点的局部平衡
        for (int v = 0; v < G.V(); v++)
            if (v != s && v != t && !localEq(v))
                return false;
        return true;
    }
}
