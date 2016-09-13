package base;

import java.util.Iterator;

/**
 * Created by Home on 2016/9/13.
 */
public class Queue<E> implements Iterable<E> {

    private Node first;
    private Node last;
    private int N;

    private class Node {
        E ele;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void enqueue(E e) {
        Node node = last;
        last = new Node();
        last.ele = e;
        if (isEmpty()) first = last;
        else node.next = last;
        N++;
    }

    public E dequeue() {
        Node node = first;
        first.next = first;
        if (isEmpty()) last = null;
        N--;
        return node.ele;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return first != null;
            }

            @Override
            public E next() {
                E e = first.ele;
                first = first.next;
                return e;
            }
        };
    }
}
