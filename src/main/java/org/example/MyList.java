package org.example;
import java.util.Iterator;

public interface MyList<T> extends Iterable<T> { //generic interface with parameter T
    interface MyIterator<E> extends Iterator<E>{

    }
    MyList.MyIterator<T> myIterator();
    void add(T item); //adding an item at the end
    void set(int index, T item); //setting item at the certain index
    void add(int index, T item); //adding item at the certain index
    void addFirst(T item); //adding to the begiining
    void addLast(T item); //adding to the end
    T get (int index); //getting access to item at specific index
    T getFirst(); //getting the first element
    T getLast(); //getting access to last element
    T remove(int index); //removing item at the certain index
    void removeFirst(); //remove the first eleement
    void removeLast();//removing the last one
    void sort(); //sorting the list of elements
    int indexOf(Object object); //will return the index number our element belongs to
    int lastIndexOf(Object object);//will return the index of the last occurrence of the specific object
    boolean exists(Object object); //checks if an item exists in the list
    public Object[] toArray(); //return array that contains all elemenets
    void clear(); //remove all elements
    int size(); //return the number of  elements


}
