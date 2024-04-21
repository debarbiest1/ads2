package org.example;
import java.util.Iterator;


public class MyLinkedList<T> implements MyList<T> {

    private static class MyNode<E> {
        E data;
        MyNode<E> next;
        MyNode<E> prev;

        MyNode(E item) {
            this.data = item;
            this.next = null;
            this.prev = null;
        }
    }

    private MyNode<T> head; // reference to the first node in the list
    private MyNode<T> tail; // reference to the last node in the list
    private int size; // current size of the list

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void add(T item) {
        // create a new node with the given item
        MyNode<T> newNode = new MyNode<>(item);
        if (head == null) {
            // if the list is empty, set both head and tail to the new node
            head = newNode;
            tail = newNode;
        } else {
            // add the new node to the end of the list
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++; // increment the size of the list
    }

    @Override
    public void set(int index, T item) {
        // get the node at the desired index (using getNodeAtIndex)
        MyNode<T> node = getNodeAtIndex(index);
        // update the data of the node
        node.data = item;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        if (index == size) {
            add(item);
            return;
        }
        // create a new node with the given item
        MyNode<T> newNode = new MyNode<>(item);
        if (index == 0) {
            // add the new node to the beginning of the list
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else {
            // get the node at the desired index and the node before it
            MyNode<T> current = getNodeAtIndex(index);
            MyNode<T> prev = current.prev;
            // update the links between nodes to insert the new node
            newNode.next = current;
            newNode.prev = prev;
            prev.next = newNode;
            current.prev = newNode;
        }
        size++; // increment the size of the list
    }

    @Override
    public void addFirst(T item) {
        add(0, item); // add the item at index 0 (beginning)
    }

    @Override
    public void addLast(T item) {
        add(item); // same as calling add(), which adds to the end
    }

    @Override
    public T get(int index) {
        // get the node at the desired index (using getNodeAtIndex)
        MyNode<T> node = getNodeAtIndex(index);
        // return the data of the node
        return node.data;
    }

    @Override
    public T getFirst() {
        if (head == null)
            throw new IllegalStateException("list is empty");
        // return the data of the first node
        return head.data;
    }

    @Override
    public T getLast() {
        if (tail == null)
            throw new IllegalStateException("list is empty");
        // return the data of the last node
        return tail.data;
    }

    @Override
    public T remove(int index) {
        // get the node at the desired index (using getNodeAtIndex)
        MyNode<T> node = getNodeAtIndex(index);
        // remove the node from the list
        removeNode(node);
        return null; // this implementation doesn't return the removed item
    }

    @Override
    public void removeFirst() {
        // if the list is empty, throw an exception
        if (head == null)
            throw new IllegalStateException("List is empty");
        // remove the first node from the list
        removeNode(head);
    }

    @Override
    public void removeLast() {
        // if the list is empty, throw an exception
        if (tail == null)
            throw new IllegalStateException("List is empty");
        // remove the last node from the list
        removeNode(tail);
    }

    @Override
    public void sort() {
        // sorting a linked list is non-trivial so
        // can implement it if needed
        throw new UnsupportedOperationException("Sorting a linked list is not supported");
    }

    @Override
    public int indexOf(Object object) {
        // iterate through the list and search for the object
        MyNode<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(object))
                return index;
            current = current.next;
            index++;
        }
        // object not found, return -1
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        // iterate from the tail and search for the object
        MyNode<T> current = tail;
        int index = size - 1;
        while (current != null) {
            if (current.data.equals(object))
                return index;
            current = current.prev;
            index--;
        }
        // object not found, return -1
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        // check if the object exists in the list using indexOf
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        // create an array to store the list elements
        Object[] arr = new Object[size];
        // iterate through the list and copy elements to the array
        MyNode<T> current = head;
        int index = 0;
        while (current != null) {
            arr[index++] = current.data;
            current = current.next;
        }
        // return the array containing the list elements
        return arr;
    }

    @Override
    public void clear() {
        // set head and tail to null, and size to 0 to indicate an empty list
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        // return the current size of the list
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        // return a new MyIterator object to iterate through the list
        return new MyIterator<>();
    }

    @Override
    public MyList.MyIterator<T> myIterator() {
        // return a new MyIterator object to iterate through the list (same as iterator())
        return new MyIterator<>();
    }

    private MyNode<T> getNodeAtIndex(int index) {
        // check for invalid index and throw exception
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        // traverse the list to the certain index and return the node
        MyNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private void removeNode(MyNode<T> node) {
        // handle removing the head node
        if (node == head) {
            head = head.next;
            if (head != null)
                head.prev = null;
            // handle removing the tail node
        } else if (node == tail) {
            tail = tail.prev;
            if (tail != null)
                tail.next = null;
            // handle removing a node in the middle of the list
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        // decrement size to reflect the removal
        size--;
    }

    public class MyIterator<E> implements MyList.MyIterator<E> {
        private MyNode<T> cursor = head;

        @Override
        public boolean hasNext() {
            // check if there's a next node (cursor is not null)
            return cursor != null;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new IllegalStateException();
            E data = (E) cursor.data;
            cursor = cursor.next;
            return data;
        }
    }
}