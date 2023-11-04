package edu.hw3.Task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private LinkedList<T> elements;

    public BackwardIterator(Collection<T> collection) {
        this.elements = new LinkedList<>(collection);
    }

    @Override
    public boolean hasNext() {
        return !elements.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return elements.removeLast();
    }
}
