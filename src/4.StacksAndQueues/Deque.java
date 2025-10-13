/*
 * Dequeue. A double-ended queue or deque (pronounced “deck”) is a 
 * generalization of a stack and a queue that supports adding and 
 * removing items from either the front or the back of the data structure.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    // private class
    @SuppressWarnings("hiding")
    private class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;
    }

    // private variables
    private Node<Item> first, last;
    private int size;

    // construct an empty deque
    public Deque() {
        // constructor
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.prev = first;
        }

        // Node newFirst = new Node();
        // newFirst.item = item;
        // if (isEmpty()) {
        //     last = newFirst;
        // }else{
        //     first.prev = newFirst;
        // }
        // newFirst.next = first;
        // newFirst.prev = null;
        // first = newFirst;

        size++;
    }

    // add the item to the back
    public void addLast(Item item){
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }

        // Node newLast = new Node();
        // newLast.item = item;
        // if (isEmpty()) {
        //     first = newLast;
        // }else{
        //     last.next = newLast;
        // }
        // newLast.prev = last;
        // newLast.next = null;
        // last = newLast;

        size++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Item item = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        } else {
            first.prev = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Item item = last.item;
        last = last.prev;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to return");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        // test cases must call each public method at least once
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addFirst(5);
        deque.addLast(6);
        deque.addFirst(7);

        System.out.println("Size: " + deque.size());
        System.out.println("Is Empty: " + deque.isEmpty());

        System.out.println("Remove First: " + deque.removeFirst());
        System.out.println("Remove Last: " + deque.removeLast());

        System.out.println("Size: " + deque.size());
        System.out.println("Is Empty: " + deque.isEmpty());

        System.out.println("Iterating over deque:");
        for (Integer i : deque) {
            System.out.println(i);
        }
    }

}