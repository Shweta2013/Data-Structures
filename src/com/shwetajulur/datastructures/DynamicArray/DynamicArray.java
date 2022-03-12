package com.shwetajulur.datastructures.DynamicArray;

import java.util.*;
import java.util.function.Consumer;

public class DynamicArray<T> implements Iterable<T> {
    private T [] arr;
    private int size = 0;
    private int capacity;

    public DynamicArray() { this(2); };

    public DynamicArray(int capacity) {
        if (capacity <= 0) { throw new IllegalArgumentException(); }
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }


    public boolean isEmpty() {
        return getSize() == 0;
    }

    public T getValueAt(int index) {
        if (index >= getSize()) { throw new IndexOutOfBoundsException(); }
        return arr[index];
    }

    public void setValueAt(int index, T elem) {
        if (index >= getSize()) { throw new IndexOutOfBoundsException(); }
        arr[index] = elem;
    }

    public void clear() {
        for(int i=0; i<capacity; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    public void add(T elem) {
        // check if the array capacity is reached, if so resize the array
        if (size + 1 >= capacity) {
            int new_capacity = 0;
            if (capacity == 0) { new_capacity = 1; }
            else { new_capacity = capacity * 2; }

            T[] new_arr = (T[]) new Object[new_capacity];
            for(int i=0; i<capacity; i++) {
                new_arr[i] = arr[i];
            }
            arr = new_arr;
            capacity = new_capacity;
        }

        arr[size++] = elem;
    }

    public T removeAt(int rm_index) {
        if (rm_index >= size && rm_index < 0) { throw new IndexOutOfBoundsException(); }
        T data = arr[rm_index];
        T[] new_arr = (T[]) new Object[size - 1];
        for (int i=0, j=0; i<size; i++, j++){
            if (i == rm_index) { j--; } // skip over rm_index
            else { new_arr[j] = arr[i]; }
        }
        arr = new_arr;
        capacity = --size;
        return data;
    }

    public boolean remove(Object obj) {
        for(int i=0; i<size; i++) {
            if (Objects.equals(arr[i], obj)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        for(int i=0; i<size; i++)
            if (arr[i].equals(obj)) return i;
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new java.util.Iterator<T> () {
            int index = 0;

            @Override
            public boolean hasNext() { return index < size; }

            @Override
            public T next() { return arr[index++]; }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(size).append("[");
            for(int i=0; i<size-1; i++) sb.append(arr[i] + ",");
            return sb.append(arr[size-1] + "]").toString();
        }
    }
}

class Test {
    public static void main(String[] args) {
        DynamicArray<Integer> DA = new DynamicArray<>();
        System.out.println("Size: " + DA.getSize());
        System.out.println("Capacity: " + DA.getCapacity());
        System.out.println(DA);

        DA.add(10);
        System.out.println("Size: " + DA.getSize());
        System.out.println("Capacity: " + DA.getCapacity());
        System.out.println(DA);

        DA.add(20);
        System.out.println("Size: " + DA.getSize());
        System.out.println("Capacity: " + DA.getCapacity());
        System.out.println(DA);

        DA.add(30);
        System.out.println("Size: " + DA.getSize());
        System.out.println("Capacity: " + DA.getCapacity());
        System.out.println(DA);

        System.out.println("Removed element is: " + DA.removeAt(2));
        System.out.println("Size: " + DA.getSize());
        System.out.println("Capacity: " + DA.getCapacity());
        System.out.println(DA);

    }
}