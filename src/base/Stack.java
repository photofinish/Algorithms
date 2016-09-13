package base;

import java.util.Iterator;

/**
 * Created by Home on 2016/9/13.
 */
public class Stack<E> implements Iterable<E> {

    private Node first;
    private int N;

    private class Node {
        E ele;
        Node next;
    }

    public boolean isEmpty() { return first == null; }
    public int size() { return N; }
    public void push(E e) {
        Node node = first;
        first = new Node();
        first.ele = e;
        first.next = node;
        N++;
    }

    public E pop() {
        Node node = first;
        first = first.next;
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
