package org.example;

import java.util.Iterator; //adding only iterator

public class MyArrayList<T> implements MyList<T> {
    private Object[] elements; // array
    private int size; //currentb size of list
    private static final int CAPACITY = 5; //initial capacity

    public MyArrayList() { //constructor to initialize
        this.elements = new Object[CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T item) { //adding an item at the end of list
        if (size == elements.length)
            resize(); //if it's full we increase the capacity
        elements[size++] = item; //adding an element and increase size
    }

    @Override
    public void set(int index, T item) { //setting item at the certain index in the list
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();//if not valid throw the exception
        elements[index] = item; // setting element at certain index
    }

    @Override
    public void add(int index, T item) { //adding item at the certain index in the list
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(); //if not valid throw the exception
        if (size == elements.length) //checks if it's full
            resize(); //increasing it
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = item;
        size++;//incrementing  size
    }

    @Override
    public void addFirst(T item) { //adding to the start of the list
        add(0, item);//at index 0
    }

    @Override
    public void addLast(T item) {
        add(item);
    }//just adding to the end by using add method

    @Override
    public T get(int index) { //getting the access to the item at certain index
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(); //if not valid throw the exception
        return (T) elements[index]; //returning
    }

    @Override
    public T getFirst() { //   we will return the first element in the list
        if (size == 0)  //if list is empty/size is null
            throw new IllegalStateException("list is empty"); //will throw the exception with message
        return (T) elements[0];
    }

    @Override
    public T getLast() { //   we will return the last element in the list
        if (size == 0)  //if list is empty/size is null
            throw new IllegalStateException("List is empty");//will throw the exception with message
        return (T) elements[size - 1];
    }

    @Override
    public T remove(int index) {  // removing the item at the certain index in  list
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(); //checkking validity

        T removedItem = get(index); // getting the item to get removed

        for (int i = index; i < size-1; i++) { // moving elements or shifting
            elements[i] = elements[i + 1];
        }

        // nullifying  the reference to last element of the list
        elements[size - 1] = null;
        size--; //size decreased accordingly

        return removedItem;//return the removed elementt
    }

    @Override
    public void removeFirst() {
        if (size == 0)
            throw new IllegalStateException("list is empty");
        remove(0);
    } //removing te first elemenet

    @Override
    public void removeLast() { //removing te last elemenet
        if (size == 0)
            throw new IllegalStateException("list is empty");
        remove(size - 1);
    }

    @Override
    public void sort() {
        // assume t is comparable
        java.util.Arrays.sort((T[]) elements, 0, size);
    }

    @Override
    public int indexOf(Object object) { //index return
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(object))
                return i;
        }
        return -1; // return -1 if no object is found
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i].equals(object))
                return i;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    } //checking if we have an element in list

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        System.arraycopy(elements, 0, arr, 0, size);
        return arr;
    }

    @Override
    public void clear() { //fully clear the list
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0; //return to the beginning
    }

    @Override
    public int size() { //get(ter of) the size
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>();
    }

    @Override
    public MyList.MyIterator<T> myIterator() {
        return new MyIterator<>();
    } // return new instance of MyIterator

    private void resize() {
        int newCapacity = elements.length * 2; //incremeneting capacity by twice
        Object[] newArray = new Object[newCapacity]; //creating new array
        System.arraycopy(elements, 0, newArray, 0, size); //copy all emenets
        elements = newArray;// our array will ссылаться to the new array whose capacity ncreased by twice
    }

    public class MyIterator<E> implements MyList.MyIterator<E> {
        private int cursor; // keep track of the current position

        @Override
        public boolean hasNext() {
            return cursor < size;
        } //checks for the cursor if it is less or more than the size

        @Override
        public E next() { //get the next element
            if (!hasNext())
                throw new IllegalStateException(); // Throw exception if there is no next element
            return (E) elements[cursor++];
        }
    }
}
