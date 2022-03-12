package com.shwetajulur.datastructures.Queue;

import java.util.Iterator;

public class Queue<T> implements Iterable<T> {

    private java.util.LinkedList<T> list = new java.util.LinkedList<>();

    public Queue() { }

    public Queue(T elem) {
        enqueue(elem);
    }

    public int getSize() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public void enqueue(T elem) {
        if (elem == null) throw new IllegalArgumentException();
        else list.addLast(elem);
    }

    public T dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        else return list.removeFirst();
    }

    public T peek() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        else return list.peekFirst();
    }


    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
