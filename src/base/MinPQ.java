package base;

import java.util.Comparator;

/**
 * Created by Home on 2016/9/14.
 */
public class MinPQ<E extends Comparable<E>> {

    private E[] pq;
    private int N = 0;

    public MinPQ(int max) {
        pq = (E[]) new Comparable[max + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(E e) {
        pq[++N] = e;
        swim(N);
    }

    public E delMin() {
        E min = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        return min;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        E tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
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
            if (less(k, k + 1)) j++;
            if (less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

}
