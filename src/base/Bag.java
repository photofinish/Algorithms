package base;

import java.util.Iterator;

/**
 * Created by Home on 2016/9/13.
 */
public class Bag<E> implements Iterable<E> {

    private class Node {
        E ele;
        Node next;
    }

    private Node first;

    public void add(E e) {

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
