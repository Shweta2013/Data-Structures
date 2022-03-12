package com.shwetajulur.datastructures.DoublyLinkedList;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {

    private int size = 0;
    private Node <T> head = null;
    private Node <T> tail = null;

    private static class Node<T> {
        T data;
        Node<T> prev, next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    // Empty the linked list O(n)
    public void clear() {
        Node<T> trav = head;
        while (trav != null) {
            Node<T> next = trav.next;
            trav.prev = trav.next = null;
            trav.data = null;
            trav = next;
        }
        head = tail = null;
        size = 0;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    // Return the size of linked list
    public int getSize() {
        return size;
    }

    // Check if the linked list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T elem) {
        addLast(elem);
    }

    private T remove(Node<T> node) {
        if (isEmpty()) throw new RuntimeException("List is empty");
        else {
            if (node.prev == null) return removeFirst();
            else if (node.next == null) return removeLast();
            else {
                T data = node.data;
                node.prev.next = node.next;
                node.next.prev = node.prev;

                // Memory cleanup
                node.data = null;
                node.prev = node.next = null;
                --size;
                return data;
            }
        }
    }

    public boolean remove(Object obj) {
        Node<T> trav;

        if (obj == null) {
            for(trav=head; trav!= null; trav=trav.next) {
                if (trav.data == null) {
                    remove(trav);
                    return true;
                }
            }
        } else {
            for(trav=head; trav!= null; trav=trav.next) {
                if (trav.data.equals(obj)) {
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    public T removeAt(int index) {
        if (isEmpty()) throw new RuntimeException("List is empty");
        else {
            if (index < 0 && index >= size) throw new IndexOutOfBoundsException();

            int i;
            Node<T> trav;

            if (index == 0) return removeFirst();
            else if (index == size-1) return removeLast();
            else {
                // Naively going through whole list
//                while (i != index-1) {
//                    trav = trav.next;
//                    i++;
//                }
                if (index < size/2) {
                    trav = head;
                    for(i=0; i!= index; i++) trav = trav.next;
                } else {
                    trav = tail;
                    for(i=size-1; i!=index; i--) trav = trav.prev;
                }
                remove(trav);
            }
        }
        return null;
    }

    public void addFirst(T elem) {
        if (isEmpty()) head = tail = new Node(elem, null, null);
        else {
            head.prev = new Node(elem, null, head);
            head = head.prev;
        }
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) { throw new RuntimeException("list is empty"); }
        else {
            T data = head.data;
            head = head.next;
            --size;

            if(isEmpty()) tail = null;
            // Memory clean of prev node;
            else head.prev = null;
            return data;
        }
    }

    private void addLast(T elem) {
        if (isEmpty()) head = tail = new Node(elem, null, null);
        else {
            tail.next = new Node(elem, tail, null);
            tail = tail.next;
        }
        size++;
    }

    public T removeLast() {
        if (isEmpty()) throw new RuntimeException("list is empty");
        else {
            T data = tail.data;
            tail = tail.prev;
            --size;

            if (isEmpty()) head = null;
            else tail.next = null;
            return data;
        }
    }
    public T peekFirst() {
        if (isEmpty()) throw new RuntimeException("list is empty");
        return head.data;
    }

    public T peekLast() {
        if (isEmpty()) throw new RuntimeException("list is empty");
        return tail.data;
    }

    public int indexOf(Object obj) {
        int index=0;
        Node<T> trav;

        if (obj == null) {
            for(trav=head; trav!= null; trav=trav.next, index++)
                if (trav.data == null)
                    return index;

        } else {
            for(trav=head; trav!= null; trav=trav.next, index++)
                if (trav.data.equals(obj))
                    return index;
        }

        return -1;
    }

    public boolean contains(Object obj) {
        if (isEmpty()) throw new RuntimeException("List is empty");
        else return indexOf(obj) >= 0;

    }

    @Override
    public Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> trav = head;
            @Override
            public boolean hasNext() {
                return trav.next != null;
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        else {
            Node<T> trav = head;
            StringBuilder sb = new StringBuilder(size).append("[ ");
            for(int i=0; i<size-1; i++) {
                sb.append("[" + trav.prev + ", " + trav.data + ", " + trav.next + "], ");
                trav = trav.next;
            }
            return sb.append("[" + trav.prev + ", " + trav.data + ", " + trav.next + "]" + " ]").toString();
        }
    }
}

class Test {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> DLL = new DoublyLinkedList<>();
        System.out.println("size:" + DLL.getSize());
        System.out.println("head:" + DLL.getHead());
        System.out.println("tail:" + DLL.getTail());

        DLL.addFirst(20);
        System.out.println(DLL);

        DLL.addFirst(10);
        System.out.println(DLL);

        DLL.add(30);
        System.out.println(DLL);

        DLL.add(40);
        System.out.println(DLL);

        System.out.println("First element: " + DLL.peekFirst());
        System.out.println("First last: " + DLL.peekLast());

        DLL.removeAt(2);
        System.out.println(DLL);

        System.out.println("is linked list contains 10: " + DLL.contains(10));
        System.out.println("is linked list contains 30: " + DLL.contains(30));

        DLL.clear();
        System.out.println(DLL);
    }
}
