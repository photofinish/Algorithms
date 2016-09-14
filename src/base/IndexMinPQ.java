package base;

/**
 * Created by Home on 2016/9/14.
 */
public class IndexMinPQ<E extends Comparable<E>> {
    private int N;
    private int[] pq;
    private int[] qp;
    private E[] keys;

    public IndexMinPQ(int max) {
        pq = new int[max + 1]; // 位置 -> 序号
        qp = new int[max + 1]; // 序号 -> 位置
        keys = (E[]) new Comparable[max + 1]; // 序号 -> 优先级键
        for (int i = 0; i <= N; i++) {
            qp[i] = -1;
        }
    }

    public void insert(int k, E e) {
        N++;
        qp[k] = N;
        pq[N] = k;
        keys[k] = e;
        swim(N);
    }

    public void change(int k, E e) {
        keys[k] = e;
        int p = qp[k];
        swim(p);
        sink(p);
    }

    public boolean contains(int k) {
        return qp[k] != -1;
    }

    public void delete(int k) {
        int p = qp[k];
        exch(p, N--);
        swim(p);
        sink(p);
        keys[k] = null;
        qp[k] = -1;
    }

    public E min() {
        return keys[pq[1]];
    }

    public int minIndex() {
        return pq[1];
    }

    public int delMin() {
        int p = pq[1];
        exch(1, N-- );
        sink(1);
        keys[pq[N + 1]] = null;
        qp[pq[N + 1]] = -1;
        return p;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void exch(int i, int j) {
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (less(j, j + 1)) j++;
            if (less(k, j)) return;
            exch(j, k);
            k = j;
        }
    }
}
