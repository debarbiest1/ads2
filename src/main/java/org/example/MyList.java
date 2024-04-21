package org.example;
import java.util.Iterator;

public interface MyList<T> extends Iterable<T> {
    interface MyIterator<E> extends Iterator<E>{

    }
    MyList.MyIterator<T> myIterator();
    void add(T item);
    void set(int index, T item);
    void add(int index, T item);
    void addFirst(T item);
    void addLast(T item);
    T get (int index);
    T getFirst();
    T getLast();
    T remove(int index);
    void removeFirst();
    void removeLast();
    void sort();
    int indexOf(Object object);
    int lastIndexOf(Object object);
    boolean exists(Object object);
    public Object[] toArray();
    void clear();
    int size();


}
